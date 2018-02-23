package my.hibernate.entities;

import my.component.repository.RepositoryForeiginWords;
import my.component.repository.RepositoryUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@EntityScan("my.hibernate.entities")
@ComponentScan({ "my.*"})
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Resource
  private RepositoryForeiginWords repositoryForeiginWords;

  @Resource
  private RepositoryUser<User,Long> repositoryUser;

  private Long user_id;

  private Admin admin;

  @Before
  public void setUp() {
    admin =new Admin("fife@bigmir.net","123","Olex","Olex");
    repositoryUser.save(admin);
    user_id=1l;
  }

  @Test
  public void shouldUserCreated() {
    List<User> list= repositoryUser.getUsers();
    Assert.assertEquals(list.get(0),admin);
  }

  @Test
  public void shouldUserExist(){
    User user=  repositoryUser.getUser(user_id);
    Assert.assertNotNull(user);
  }

  @Test
  public void shouldUserExistForEmail() {
    User user= repositoryUser.getUser(admin.getEmail());
    Assert.assertEquals(user,admin);
  }
}
