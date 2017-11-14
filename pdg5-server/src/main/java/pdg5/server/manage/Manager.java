package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pdg5.common.Protocol;
import pdg5.server.persistent.AbstractData;

public class Manager {

	private static SessionFactory factory = null;
	private static Session session = null;
	Transaction transaction = null;


	private static SessionFactory getFactory() {
		if(factory == null) {
			try {
				factory = new Configuration().configure().buildSessionFactory();
		      } catch (Throwable ex) {
		         System.err.println("Failed to create sessionFactory object. " + ex);
		         ex.printStackTrace();
		         throw new ExceptionInInitializerError(ex);
		      }
		}
		return factory;
	}
	/**
	 * Used to get a global session
	 * @return
	 */
	public static Session getSession() {
		if(session == null) {
			session = Manager.getFactory().openSession();
		}
		return session;
	}

	/**
	 * To call when we are done talking to the DB
	 */
	public static void closeConversation() {
		if(session != null) {
			session.close();
		}
	}

	public int commitToDB(AbstractData abstractData) {
		Integer id;
		int code = Protocol.OK;

		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(abstractData);
			abstractData.setId(id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction !=null) transaction.rollback();
			code = Protocol.ERROR;
			e.printStackTrace();
		}

		return code;
	}

	public int updateToDB(AbstractData abstractData) {
		int code = Protocol.OK;

		try {
			transaction = session.beginTransaction();
			session.update(abstractData);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			code = Protocol.ERROR;
			e.printStackTrace();
		}

		return code;
	}

	public int deleteToDB(AbstractData abstractData) {
		int code = Protocol.OK;

		try {
			transaction = session.beginTransaction();
			session.delete(abstractData);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			code = Protocol.ERROR;
			e.printStackTrace();
		}

		return code;
	}
}
