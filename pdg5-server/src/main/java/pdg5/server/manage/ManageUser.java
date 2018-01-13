package pdg5.server.manage;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import pdg5.server.persistent.User;

import java.util.List;

public class ManageUser extends Manager {

    public User addUser(String email, String username, String pass) {
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setUsername(username.toLowerCase());
        user.setPass(BCrypt.hashpw(pass, BCrypt.gensalt()));

        //todo if the email/username is already taken ?
        return (User) addToDB(user);
    }

    /**
     * Get the User whose email is given from the DB
     *
     * @param email the email
     * @return a corresponding User instance
     */
    public User getUserByEmail(String email) {
        Session session = getSession();
        email = email.toLowerCase();
        session.beginTransaction();
        User u = session.createQuery("from User where email=:email", User.class)
                .setParameter("email", email).uniqueResult();
        session.getTransaction().commit();

        return u;
    }

    /**
     * Get the User whose username is given from the DB
     *
     * @param username the username
     * @return a corresponding User instance
     */
    public User getUserByUsername(String username) {
        Session session = getSession();
        username = username.toLowerCase();
        session.beginTransaction();
        User u = session.createQuery("from User where username=:username", User.class)
                .setParameter("username", username).uniqueResult();
        session.getTransaction().commit();

        return u;
    }

    public List<User> listUser() {
        return (List<User>) getListFromDB("FROM User");
    }

    /**
     * Check whether the given password is correct for the user with the given
     * username
     *
     * @param username the username
     * @param password the password
     * @return true if correct, false otherwise
     */
    public boolean isCorrectPassword(String username, String password) {
        User u = getUserByUsername(username);
        if (u == null) {
            return false;
        }
        return isExpectedPassword(password, u.getPass());
    }

    private boolean isExpectedPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }

    public void updateUser(User user) {
        updateToDB(user);
    }

    public void deleteUser(User user) {
        deleteToDB(user);
    }

    public User getUserById(int idPlayer) {
        Session session = new Manager().getSession();
        session.beginTransaction();
        User u = session.createQuery("from User where id=:id", User.class)
                .setParameter("id", idPlayer).uniqueResult();
        session.getTransaction().commit();

        return u;
    }

}
