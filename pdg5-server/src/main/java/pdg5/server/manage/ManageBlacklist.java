package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pdg5.server.persistent.Blacklist;
import pdg5.server.persistent.User;

public class ManageBlacklist extends Manager {
	public Blacklist addBlacklist(User fromUser, User toUser) {
		return (Blacklist)commitToDB(new Blacklist(fromUser, toUser, new Date()));
	}
	
	public List<Blacklist> listBlacklist() {
		 Session session = getSession();
	      Transaction tx = null;
	      List<Blacklist> Blacklists = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Blacklists = session.createQuery("FROM Blacklist").list(); 
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	      return Blacklists;
	}
	
	public int updateBlacklist(Blacklist blacklist) {
		return updateToDB(blacklist);
	}
	
	public int deleteBlacklist(Blacklist blacklist) {
		return deleteToDB(blacklist);
	}
}
