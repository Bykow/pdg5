package manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistent.BlackList;

public class manageBlackList {
	public BlackList addBlackList(int fromUser, int toUser) {
		Session session = manager.getFactory().openSession();
		Transaction tx = null;
		BlackList blacklist = new BlackList(fromUser, toUser);
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(blacklist); 
	         blacklist.setID(tntID);
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
		 Session session = manager.getFactory().openSession();
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
		Session session = manager.getFactory().openSession();
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
		Session session = manager.getFactory().openSession();
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
