package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.User;

public class ManageMessage {
	public Message addMessage(String content, User user, Chat chat) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		Message message = new Message(chat,user,content,new Date());
		Integer mID;
		
		try {
	         tx = session.beginTransaction();
	         mID = (Integer) session.save(message); 
	         message.setId(mID);
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
		 Session session = Manager.getFactory().openSession();
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
		Session session = Manager.getFactory().openSession();
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
		Session session = Manager.getFactory().openSession();
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
