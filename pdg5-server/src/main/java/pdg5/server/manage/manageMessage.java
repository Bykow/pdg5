package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Message;

public class manageMessage {
	public Message addMessage(String content, int user, int chat) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Message message = new Message(content, new Date(), user, chat);
		Integer mID;
		
		try {
	         tx = session.beginTransaction();
	         mID = (Integer) session.save(message); 
	         message.setID(mID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return message;
	}
	
	public List<Message> listMessages() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Message> messages = null;
	      
	      try {
	         tx = session.beginTransaction();
	         messages = session.createQuery("FROM Message").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return messages;
	}
	
	public void updateMessage(Message message) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(message); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteMessage(Message message) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(message); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
