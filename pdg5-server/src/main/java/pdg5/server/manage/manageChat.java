package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Chat;

public class manageChat {
	public Chat addChatTournament(int tournament) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		
		Chat chat = new Chat();
		chat.setTournament(tournament);
		Integer cid;
		
		try {
	         tx = session.beginTransaction();
	         cid = (Integer) session.save(chat); 
	         chat.setID(cid);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return chat;
	}
	
	public Chat addChatGame(int game) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Chat chat = new Chat();
		chat.setGame(game);
		Integer cid;
		
		try {
	         tx = session.beginTransaction();
	         cid = (Integer) session.save(chat); 
	         chat.setID(cid);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return chat;
	}
	
	public List<Chat> listChats() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Chat> chats = null;
	      
	      try {
	         tx = session.beginTransaction();
	         chats = session.createQuery("FROM Chat").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return chats;
	}
	
	public void updateChat(Chat chat) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(chat); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteChat(Chat chat) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(chat); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
