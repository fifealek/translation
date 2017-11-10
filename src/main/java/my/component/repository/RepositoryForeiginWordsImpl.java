package my.component.repository;

import my.hibernate.entities.Foreignwords;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RepositoryForeiginWordsImpl implements RepositoryForeiginWords {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Foreignwords> findAll() {
        return entityManager.createQuery("select f from Foreignwords f").getResultList();
    }

    @Override
    public Foreignwords find(String s) {
        Foreignwords foreignwords = null;
        try {
            foreignwords = (Foreignwords) entityManager.createQuery("select f from Foreignwords f where f.foreignWord =:foreignWord").setParameter("foreignWord", s).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
        }
        return foreignwords;
    }

    @Override
    public void save(Object word) {
        //entityManager.persist(word);
        entityManager.merge(word);
    }
}
