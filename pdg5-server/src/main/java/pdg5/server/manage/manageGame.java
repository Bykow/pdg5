package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

public class manageGame {
	public Game addGame(String title, User player1, User player2, Tournament tournament) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Game game = new Game();
		game.setTitle(title);
		game.setUserByPlayer1(player1);
		game.setUserByPlayer2(player2);
		game.setTournament(tournament);
		game.setCreated(new Date());
		Integer gID;
		
		try {
	         tx = session.beginTransaction();
	         gID = (Integer) session.save(game); 
	         game.setId(gID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return game;
	}
	
	public Game addGame(String title, User player1, User player2) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Game game = new Game();
		game.setTitle(title);
		game.setUserByPlayer1(player1);
		game.setUserByPlayer2(player2);
		game.setCreated(new Date());
		Integer gID;
		
		try {
	         tx = session.beginTransaction();
	         gID = (Integer) session.save(game); 
	         game.setId(gID);
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
