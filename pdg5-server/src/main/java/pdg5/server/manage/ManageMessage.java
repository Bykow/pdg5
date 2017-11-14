package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.User;

public class ManageMessage extends Manager {
	public Message addMessage(String content, User user, Chat chat) {
		return (Message)commitToDB(new Message(chat,user,content,new Date()));
	}
	
	public List<Message> listMessages() {
		 Session session = getSession();
	      Transaction tx = null;
	      List<Message> messages = null;
	      
	      try {
	         tx = session.beginTransaction();
	         messages = session.createQuery("FROM Message").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return messages;
	}
	
	public int updateMessage(Message message) {
		return updateToDB(message);
	}
	
	public int deleteMessage(Message message) {
		return deleteToDB(message);
	}
}
