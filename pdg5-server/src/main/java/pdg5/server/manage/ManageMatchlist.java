package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;
import pdg5.server.persistent.Matchlist;

public class ManageMatchlist extends Manager {
	
	public Matchlist addMatchlist(Tournament tournament, User user) {
		return (Matchlist)commitToDB(new Matchlist(tournament, user));
	}
	
	public List<Matchlist> listMatchlist() {
		 Session session = getSession();
	      Transaction tx = null;
	      List<Matchlist> Matchlists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Matchlists = session.createQuery("FROM Matchlist").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return Matchlists;
	}
	
	public int updateMatchlist(Matchlist matchlist) {
		return updateToDB(matchlist);
	}
	
	public int deleteMatchlist(Matchlist matchlist) {
		return deleteMatchlist(matchlist);
	}

}
