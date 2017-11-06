package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Blacklist;
import pdg5.server.persistent.User;

public class ManageBlacklist {
	public Blacklist addBlacklist(User fromUser, User toUser) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		Blacklist Blacklist = new Blacklist(fromUser, toUser, new Date());
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(Blacklist); 
	         Blacklist.setId(tntID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return Blacklist;
	}
	
	public List<Blacklist> listBlacklist() {
		 Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Blacklist> Blacklists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Blacklists = session.createQuery("FROM Blacklist").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return Blacklists;
	}
	
	public void updateBlacklist(Blacklist Blacklist) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(Blacklist); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteBlacklist(Blacklist Blacklist) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(Blacklist); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
