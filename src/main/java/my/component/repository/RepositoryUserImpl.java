package my.component.repository;

import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
@Transactional
public class RepositoryUserImpl implements RepositoryUser<User,Long>{

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public User getUser(Long id) {
    return entityManager.find(User.class,id);
  }

  @Override
  public User getUser(String email) {
    User user = null;
    try {
      user = (User) entityManager.createQuery("select u from User u where u.email =:email").setParameter("email", email).getSingleResult();
    } catch (NoResultException e) {
      e.printStackTrace();
    } catch (NonUniqueResultException e) {
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public List<User> getUsers() {
    return entityManager.createQuery("select u from User u").getResultList();
  }

  @Override
  public void save(User user) {
      entityManager.merge(user);
    }

}
