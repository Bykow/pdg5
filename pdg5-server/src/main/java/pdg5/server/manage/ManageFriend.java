package pdg5.server.manage;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Friend;

public class ManageFriend {
	public Friend addFriend(int fromUser, int toUser) {
		Session session = Manager.getFactory().openSession();
		Transaction tx = null;
		Friend friend = new Friend(fromUser, toUser);
		Integer tntID;
		
		try {
	         tx = session.beginTransaction();
	         tntID = (Integer) session.save(friend); 
	         friend.setID(tntID);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		return friend;
	}
	
	public List<Friend> listFriend() {
		 Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      List<Friend> friends = null;
	      
	      try {
	         tx = session.beginTransaction();
	         friends = session.createQuery("FROM Friend").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return friends;
	}
	
	public void updateFriend(Friend friend) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
			 session.update(friend); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteFriend(Friend friend) {
		Session session = Manager.getFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(friend); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
}
