package pdg5.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import pdg5.persistent.User;

public class manageUser {
	
	public User addUser(String email, String pass) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		email = email.toLowerCase();
		User user = new User(email, hashedPass);
		Integer usrID;
		
		try {
	         tx = session.beginTransaction();
	         usrID = (Integer) session.save(user); 
	         user.setID(usrID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return user;
	}
	
	/**
     * Get the User whose username is given from the DB
     *
     * @param username the username
     * @return a corresponding User instance
     */
    public User getUser(String email) {
    	Session session = manager.getFactory().openSession();
        email = email.toLowerCase();
        session.beginTransaction();
        User u = session.createQuery("from User where email=:email", User.class)
                .setParameter("email", email).uniqueResult();
        session.getTransaction().commit();

        return u;
    }
	
	public List<User> listUser() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<User> users = null;
	      
	      try {
	         tx = session.beginTransaction();
	         users = session.createQuery("FROM User").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
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
    public boolean isCorrectPassword(String email, String password) {
        User u = getUser(email);
        if (u == null) return false;
        return isExpectedPassword(password, u.getPass());
    }
    
    private boolean isExpectedPassword(String candidate, String hashed) {
    	return BCrypt.checkpw(candidate, hashed);
    }
	
	public void updateUser(User user) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(user); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteUser(User user) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(user); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}

}
