package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pdg5.common.Protocol;
import pdg5.server.persistent.AbstractData;

import java.util.List;

/**
 * Manager who gets from hibernate the session to allow us to do transactions.
 * All the transactions possible are implemented in this class.
 * The type of the data used to do transaction is AbstractData, 
 * it means that any data that wish to be in the database has to extends AbstractData.
 */
public class Manager {

   /**
    * class used by hibernate to give us a session
    */
    private static SessionFactory factory;
    
    /**
     * session configured with default value's of the SessionFactory of hibernate
     */
    private Session session;
    
    /**
     * used to do transaction with the session
     */
    private Transaction transaction;
    
    /**
     * Constructor
     */
    public Manager() {
        this(null);
    }
    
    /**
     * Constructor
     * 
     * @param session 
     */
    public Manager(Session session) {
        transaction = null;
        this.session = session;
        getFactory();
    }

    /**
     * get from hibernate the SessionFactory configured with default parameters
     * 
     * @return the initialized SessionFactory created by hibernate
     */
    private SessionFactory getFactory() {
        if (factory == null) {
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
     *
     * @return the global session
     */
    public synchronized Session getSession() {
        if (session == null) {
            session = getFactory().openSession();
        }
        return session;
    }

    /**
     * To call when we are done talking to the DB
     */
    public void closeConversation() {
        if (session != null) {
            session.close();
        }
    }

    /**
     * allow to add any data who extends from AbstractData to the database
     * 
     * @param abstractData the data we wish to add to the database
     * @return the new created data
     */
    public AbstractData addToDB(AbstractData abstractData) {
        Integer id;

        try {
            transaction = getSession().beginTransaction();
            id = (Integer) getSession().save(abstractData);
            abstractData.setId(id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return abstractData;
    }

    /**
     * allow to do a request to the database
     * 
     * @param query the request as a String
     * @return 
     */
    public List<? extends AbstractData> getListFromDB(String query) {
        List<AbstractData> list = null;

        try {
            transaction = getSession().beginTransaction();
            list = getSession().createQuery(query).list();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    /**
     * allow to update any data who extends from AbstractData to the database
     * 
     * @param abstractData the data we wish to update
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int updateToDB(AbstractData abstractData) {
        int code = Protocol.OK;

        try {
            transaction = getSession().beginTransaction();
            getSession().update(abstractData);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            code = Protocol.ERROR;
            e.printStackTrace();
        }

        return code;
    }

    /**
     * allow to delete any data who extends from AbstractData to the database
     * 
     * @param abstractData the data we wish to delete
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int deleteToDB(AbstractData abstractData) {
        int code = Protocol.OK;

        try {
            transaction = getSession().beginTransaction();
            getSession().delete(abstractData);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            code = Protocol.ERROR;
            e.printStackTrace();
        }

        return code;
    }
}
