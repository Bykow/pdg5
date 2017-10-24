package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.MatchList;

public class manageMatchList {
	
	public MatchList addMatchList(int tournament, int user) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		MatchList matchList = new MatchList(tournament, user);
		Integer mlID;
		
		try {
	         tx = session.beginTransaction();
	         mlID = (Integer) session.save(matchList); 
	         matchList.setID(mlID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return matchList;
	}
	
	public List<MatchList> listMatchList() {
		 Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      List<MatchList> matchLists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         matchLists = session.createQuery("FROM MatchList").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return matchLists;
	}
	
	public void updateMatchList(MatchList matchList) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(matchList); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteMatchList(MatchList matchList) {
		Session session = manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(matchList); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}

}
