package pdg5.server.util;

import org.hibernate.Session;
import pdg5.server.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Used for user management
 *
 * In part from : http://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
 */
public class UserManager {
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    /**
     * Add a user to the DB
     *
     * @param username the username to use
     * @param password the password to use
     * @return true if everything went ok, false otherwise
     */
    public boolean addUser(String username, char[] password) {
        username = username.toUpperCase();
        // Is user already in the db ?
        User u = getUser(username);

        if (u != null) return false;

        u = new User();
        u.setUsername(username);
        u.setSalt(getNextSalt());
        u.setPasswordHash(hashPassword(password, u.getSalt()));
        u.setPrivilege(Privilege.USER.getValue());

        session.beginTransaction();
        session.save(u);
        session.getTransaction().commit();

        return true;
    }

    /**
     * Get the User whose username is given from the DB
     *
     * @param username the username
     * @return a corresponding User instance
     */
    public User getUser(String username) {
        username = username.toUpperCase();
        session.beginTransaction();
        User u = session.createQuery("from User where username=:username", User.class)
                .setParameter("username", username).uniqueResult();
        session.getTransaction().commit();

        return u;
    }

    /**
     * List the users in the DB
     * @return an array of User instances
     */
    public User[] listUsers() {
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<User> result = session.createQuery("from User").list();
        session.getTransaction().commit();

        return result.toArray(new User[]{});
    }

    /**
     * Delete the user with the given username from the DB
     *
     * @param username the username
     */
    public void deleteUser(String username) {
        User u = getUser(username);
        session.beginTransaction();
        if (u != null) {
            session.remove(u);
        }
        session.getTransaction().commit();
    }

    /**
     * Promote the user with the given username to admin in the DB
     *
     * @param username the username
     */
    public void promoteUser(String username) {
        User e = getUser(username);
        session.beginTransaction();
        e.setPrivilege(Privilege.ADMIN.getValue());
        session.update(e);
        session.getTransaction().commit();
    }

    /**
     * Demote the user with the given username to lowly peasant
     * in the DB
     *
     * @param username the username
     */
    public void demoteUser(String username) {
        User e = getUser(username);
        session.beginTransaction();
        e.setPrivilege(Privilege.USER.getValue());
        session.update(e);
        session.getTransaction().commit();
    }

    /**
     * Check whether the given password is correct for the user with
     * the given username
     *
     * @param username the username
     * @param password the password
     * @return true if correct, false otherwise
     */
    public boolean isCorrectPassword(String username, char[] password) {
        User u = getUser(username);
        if (u == null) return false;
        return isExpectedPassword(password, u.getSalt(), u.getPasswordHash());
    }

    /**
     * Checks whether the user with the given username is an admin
     *
     * @param username the username
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin(String username) {
        User u = getUser(username);
        return (u.getPrivilege() == Privilege.ADMIN.getValue());
    }

    /**
     * Hash the given password with the given salt
     *
     * @param password the password
     * @param salt the salt
     * @return an array of bytes that contains the hashed password
     */
    private byte[] hashPassword(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Generate the next salt in the sequence
     * @return an array of bytes containing the salt
     */
    private byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Checks whether the given password + salt combination hashes
     * to the expected hash
     *
     * @param password the password
     * @param salt the salt
     * @param expectedHash the expectation
     * @return true if same hash, false otherwise
     */
    private boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] hash = hashPassword(password, salt);

        // Erase password
        Arrays.fill(password, Character.MIN_VALUE);

        if (hash.length != expectedHash.length) return false;

        // compare hash (same time for either result)
        byte result = 0;
        for (int i = 0; i < hash.length; i++) {
            result |= hash[i] ^ expectedHash[i];
        }
        return (result == 0);
    }

    /**
     * Privilege represents someone being an admin
     * or a lowly peasant crawling in mud
     */
    private enum Privilege {
        ADMIN(0), USER(1);

        private final int value;

        Privilege(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
