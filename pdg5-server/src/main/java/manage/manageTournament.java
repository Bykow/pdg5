package manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistent.Tournament;

public class manageTournament {
	
	public Tournament addTournament(String title) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		Tournament tournament = new Tournament(title);
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(tournament); 
	         tournament.setID(tntID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return tournament;
	}
	
	public List<Tournament> listTournaments() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Tournament> tournaments = null;
	      
	      try {
	         tx = session.beginTransaction();
	         tournaments = session.createQuery("FROM Tournament").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return tournaments;
	}
	
	public void updateTournament(Tournament tournament) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(tournament); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteTournament(Tournament tournament) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(tournament); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}

}
