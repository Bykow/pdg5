package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;
import pdg5.server.persistent.Matchlist;

public class ManageMatchlist {
	
	public Matchlist addMatchlist(Tournament tournament, User user) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		Matchlist Matchlist = new Matchlist(tournament, user);
		Integer mlID;
		
		try {
	         tx = session.beginTransaction();
	         mlID = (Integer) session.save(Matchlist); 
	         Matchlist.setId(mlID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return Matchlist;
	}
	
	public List<Matchlist> listMatchlist() {
		 Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Matchlist> Matchlists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Matchlists = session.createQuery("FROM Matchlist").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return Matchlists;
	}
	
	public void updateMatchlist(Matchlist Matchlist) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(Matchlist); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteMatchlist(Matchlist Matchlist) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(Matchlist); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}

}
