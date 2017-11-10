package my.component;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public interface DaoService {
    SessionFactory getSessionFactory();
    UserTransaction getuserTransaction();
    void rollback();
    EntityManager createEntityManager(); 
}
