package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Tournament;

public class ManageTournament {
	
	public Tournament addTournament(String title) {
		Session session = Manager.getSession();
		Transaction tx = null;
		Tournament tournament = new Tournament(title, new Date());
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(tournament); 
	         tournament.setId(tntID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
		
		return tournament;
	}
	
	public List<Tournament> listTournaments() {
		 Session session = Manager.getSession();
	      Transaction tx = null;
	      List<Tournament> tournaments = null;
	      
	      try {
	         tx = session.beginTransaction();
	         tournaments = session.createQuery("FROM Tournament").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return tournaments;
	}
	
	public void updateTournament(Tournament tournament) {
		Session session = Manager.getSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(tournament); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}
	
	public void deleteTournament(Tournament tournament) {
		Session session = Manager.getSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(tournament); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}

}
