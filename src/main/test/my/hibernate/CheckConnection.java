package my.hibernate;

import my.hibernate.config.HibernateConfig;
import my.hibernate.entities.Foreignwords;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import javax.transaction.UserTransaction;
import java.util.List;


public class CheckConnection {

    @Test
    public void shouldConnectionAlive() throws Exception {

        UserTransaction tx=HibernateConfig.getUserTransaction();
        tx.begin();
        SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
        //EntityManagerFactory entityManagerFactory =new EntityManagerFactoryImpl(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        Assert.assertTrue(session.isConnected());
        tx.rollback();
    }

    @Test
    public void shouldSetMessage() throws Exception {


        try {
            UserTransaction tx=HibernateConfig.getUserTransaction();
        tx.begin();
        SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Foreignwords foreignwords = new Foreignwords();
        foreignwords.setForeignWord("hello");
        session.persist(foreignwords);
//        tx.commit();
        List<Foreignwords> list=session.createCriteria(Foreignwords.class).list();
        Assert.assertEquals(list.size(),1);
        list.get(0).setForeignWord("Hello world");
        tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //HibernateConfig.rollback();

        }

    }

}
