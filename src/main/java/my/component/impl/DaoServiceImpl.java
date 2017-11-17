package my.component.impl;

import my.component.DaoService;
import my.hibernate.config.HibernateConfig;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

import static my.hibernate.config.HibernateConfig.createSessionFactory;
import static my.hibernate.config.HibernateConfig.getUserTransaction;

//@Service("dao")
public class DaoServiceImpl implements DaoService {
    @Override
    public SessionFactory getSessionFactory() {
        return createSessionFactory();
    }

    @Override
    public UserTransaction getuserTransaction() {
        return getUserTransaction();
    }

    @Override
    public void rollback() {
        try {
            rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public EntityManager createEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("123456QWER");
        return factory.createEntityManager();
    }
}
