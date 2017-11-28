package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pdg5.common.Protocol;
import pdg5.server.persistent.AbstractData;

import java.io.File;
import java.util.List;

public class Manager {
	private static SessionFactory factory;
	private static Session session;
	private static Transaction transaction;

	public Manager() {
		factory = null;
		session = null;
		transaction = null;
	}

	private SessionFactory getFactory() {
		if(factory == null) {
			try {
				factory = new Configuration().configure(new File("/hibernate.cfg.xml")).buildSessionFactory();
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
	public Session getSession() {
		if(session == null) {
			session = getFactory().openSession();
		}
		return session;
	}

	/**
	 * To call when we are done talking to the DB
	 */
	public void closeConversation() {
		if(session != null) {
			session.close();
		}
	}

	public AbstractData addToDB(AbstractData abstractData) {
		Integer id;

		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(abstractData);
			abstractData.setId(id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction !=null) transaction.rollback();
			e.printStackTrace();
		}

		return abstractData;
	}

	public List<? extends AbstractData> getListFromDB(String query) {
		List<AbstractData> list = null;

		try {
			transaction = session.beginTransaction();
			list = session.createQuery(query).list();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace();
		}
		return list;
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
