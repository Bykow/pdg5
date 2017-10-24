package pdg5.server.manage;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class manager {
	
	private static SessionFactory factory = null;
	
	public static SessionFactory getFactory() {
		if(factory == null) {
			try {
		         factory = new Configuration().configure().buildSessionFactory();
		      } catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         ex.printStackTrace();
		         throw new ExceptionInInitializerError(ex); 
		      }
		}
		return factory;
	}
	
	// TODO if we can simplify things
	public void updateObject() {
		
	}
	
	public void deleteObject() {
		
	}

}
