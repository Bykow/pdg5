package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;

public class ManageChat extends Manager {
	public int addChatTournament(Tournament tournament) {
		Chat chat = new Chat();
		chat.setTournament(tournament);
		return commitToDB(tournament);
	}
	
	public int addChatGame(Game game) {
		Chat chat = new Chat();
		chat.setGame(game);
		return commitToDB(game);
	}
	
	public List<Chat> listChats() {
		 Session session = getSession();
	      Transaction tx = null;
	      List<Chat> chats = null;
	      
	      try {
	         tx = session.beginTransaction();
	         chats = session.createQuery("FROM Chat").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return chats;
	}
	
	public int updateChat(Chat chat) {
		return updateToDB(chat);
	}
	
	public int deleteChat(Chat chat) {
		return deleteToDB(chat);
	}
}
