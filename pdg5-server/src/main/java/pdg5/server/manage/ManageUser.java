package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import pdg5.common.Protocol;
import pdg5.server.persistent.User;

public class ManageUser extends Manager {
	
	public int addUser(String email,String username, String pass) {
		Session session = Manager.getSession();
		Transaction tx = null;
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		email = email.toLowerCase();
		username = username.toLowerCase();
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPass(hashedPass);
		Integer usrID;
		int exitCode;

		//todo if the email/username is already taken ?
		
		exitCode = commitToDB(user);
		
		return exitCode;
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
		 Session session = getSession();
	      Transaction tx = null;
	      List<User> users = null;
	      
	      try {
	         tx = session.beginTransaction();
	         users = session.createQuery("FROM User").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return users;
	}
	
	/**
     * Check whether the given password is correct for the user with
     * the given username
     *
     * @param username the username
     * @param password the password
     * @return true if correct, false otherwise
     */
    public boolean isCorrectPassword(String username, String password) {
		User u = getUserByUsername(username);
        if (u == null) return false;
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

}
