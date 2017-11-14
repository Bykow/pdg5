package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Tournament;

public class ManageTournament extends Manager {
	
	public Tournament addTournament(String title) {
		return (Tournament)commitToDB(new Tournament(title, new Date()));
	}
	
	public List<Tournament> listTournaments() {
		 Session session = getSession();
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
		updateToDB(tournament);
	}
	
	public void deleteTournament(Tournament tournament) {
		deleteToDB(tournament);
	}

}
