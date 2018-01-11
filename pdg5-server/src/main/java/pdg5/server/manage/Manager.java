package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pdg5.common.Protocol;
import pdg5.server.persistent.AbstractData;

import java.util.List;

public class Manager {
    private static SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public Manager() {
        transaction = null;
        session = null;
        getFactory();
    }

    private SessionFactory getFactory() {
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
    public synchronized Session getSession() {
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
            transaction = getSession().beginTransaction();
            id = (Integer) getSession().save(abstractData);
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
            transaction = getSession().beginTransaction();
            list = getSession().createQuery(query).list();

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
            transaction = getSession().beginTransaction();
            getSession().update(abstractData);
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
            transaction = getSession().beginTransaction();
            getSession().delete(abstractData);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            code = Protocol.ERROR;
            e.printStackTrace();
        }

        return code;
    }
}
