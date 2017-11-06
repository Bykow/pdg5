package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.BlackList;
import pdg5.server.persistent.User;

public class ManageBlackList {
	public BlackList addBlackList(User fromUser, User toUser) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		BlackList blacklist = new BlackList(fromUser, toUser, new Date());
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(blacklist); 
	         blacklist.setId(tntID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return blacklist;
	}
	
	public List<BlackList> listBlackList() {
		 Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      List<BlackList> blackLists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         blackLists = session.createQuery("FROM BlackList").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return blackLists;
	}
	
	public void updateBlackList(BlackList blackList) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(blackList); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteBlackList(BlackList blacklist) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(blacklist); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
