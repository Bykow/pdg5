package pdg5.server.manage;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import pdg5.server.persistent.User;

import java.util.List;

/**
 * Manager to stock and load users from/to the database
 */
public class ManageUser extends Manager {

    /**
     * Constructor
     */
    public ManageUser() {
        super();
    }
    
   /**
    * Constructor
    * 
    * @param session the session used by the manager to do transactions
    */
    public ManageUser(Session session) {
        super(session);
    }
    
    /**
     * add a user to the database
     * 
     * @param email of the user
     * @param username of the user
     * @param pass the password of the user
     * @return the new created user
     */
    public User addUser(String email, String username, String pass) {
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setUsername(username.toLowerCase());
        user.setPass(BCrypt.hashpw(pass, BCrypt.gensalt()));

        return (User) addToDB(user);
    }

    /**
     * Get the User, whose email is given, from the DB
     *
     * @param email the user's email
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
     * Get the User, whose username is given, from the DB
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

    /**
     * Get the User whose unique id is given from the DB
     * 
     * @param idPlayer unique id of the user
     * @return the found user
     */
    public User getUserById(int idPlayer) {
        Session session = getSession();
        session.beginTransaction();
        User u = session.createQuery("from User where id=:id", User.class)
            .setParameter("id", idPlayer).uniqueResult();
        session.getTransaction().commit();

        return u;
    }
    
    /**
     * return a list of all user contained in the database
     * 
     * @return a list of all user contained in the database
     */
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

    /**
     * method who check if the password once hashed
     * is the hashed password
     * 
     * @param candidate the password tried hashed
     * @param hashed the password real password hashed
     * @return true if they are equals, false otherwise
     */
    private boolean isExpectedPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }

    /**
     * update informations of a specified user
     * 
     * @param user the new informations of the user
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int updateUser(User user) {
        return updateToDB(user);
    }

    /**
     * delete from the database a specified user
     * 
     * @param user the user we wish to delete from the database
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int deleteUser(User user) {
        return deleteToDB(user);
    }
}
