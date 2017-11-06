package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;

public class ManageChat {
	public Chat addChatTournament(Tournament tournament) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		
		Chat chat = new Chat();
		chat.setTournament(tournament);
		Integer cid;
		
		try {
	         tx = session.beginTransaction();
	         cid = (Integer) session.save(chat); 
	         chat.setId(cid);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return chat;
	}
	
	public Chat addChatGame(Game game) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		Chat chat = new Chat();
		chat.setGame(game);
		Integer cid;
		
		try {
	         tx = session.beginTransaction();
	         cid = (Integer) session.save(chat); 
	         chat.setId(cid);
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
		 Session session = Manager.getFactory().openSession();
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
		Session session = Manager.getFactory().openSession();
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
		Session session = Manager.getFactory().openSession();
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
