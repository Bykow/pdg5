package manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistent.User;

//TODO test it
public class manageUser {
	
	public User addUser(String email, String pass) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		User user = new User(email, pass);
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
