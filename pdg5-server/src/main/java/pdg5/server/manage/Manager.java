package pdg5.server.manage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Manager {
	
	private static SessionFactory factory = null;
	private static Session session = null;
	
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
}
