package pdg5.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.persistent.Game;

public class manageGame {
	public Game addGame(String title, int player1, int player2, int tournament) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Game game = new Game(title, player1, player2, tournament);
		Integer gID;
		
		try {
	         tx = session.beginTransaction();
	         gID = (Integer) session.save(game); 
	         game.setID(gID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return game;
	}
	
	public Game addGame(String title, int player1, int player2) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Game game = new Game(title, player1, player2);
		Integer gID;
		
		try {
	         tx = session.beginTransaction();
	         gID = (Integer) session.save(game); 
	         game.setID(gID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return game;
	}
	
	public List<Game> listGame() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Game> games = null;
	      
	      try {
	         tx = session.beginTransaction();
	         games = session.createQuery("FROM Game").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return games;
	}
	
	public void updateGame(Game game) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(game); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteGame(Game game) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(game); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
