package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Friend;
import pdg5.server.persistent.User;

public class ManageFriend extends Manager {
	public int addFriend(User fromUser, User toUser) {
		return commitToDB(new Friend(fromUser, toUser, new Date()));
	}
	
	public List<Friend> listFriend() {
		 Session session = getSession();
	      Transaction tx = null;
	      List<Friend> friends = null;
	      
	      try {
	         tx = session.beginTransaction();
	         friends = session.createQuery("FROM Friend").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return friends;
	}
	
	public int updateFriend(Friend friend) {
		return updateToDB(friend);
	}
	
	public int deleteFriend(Friend friend) {
		return deleteFriend(friend);
	}
}
