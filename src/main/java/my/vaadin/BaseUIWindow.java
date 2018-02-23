package my.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import my.component.converte.Converter;
import my.component.repository.RepositoryForeiginWords;
import my.component.repository.RepositoryUser;
import my.form.binders.entities.ForeignWordTable;
import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.List;

public class BaseUIWindow extends UI {

  public static String USER_KEY="ID";
  @Resource
  protected RepositoryForeiginWords repository;

  @Resource
  protected RepositoryUser<User,Long> repositoryUser;

  @Resource
  protected Converter converter;


  @Override
  protected void init(VaadinRequest vaadinRequest) {

  }

  public boolean isUserLoginIn(String key) {
    boolean b=Boolean.FALSE;
    if (key == null) key = "";
    VaadinSession session = getSession();
    if(session.getAttribute(key) != null) {
      b=Boolean.TRUE;
    }else{
      b = createSession(key);
    }
    return b;
  }

  public boolean createSession(String key) {
    boolean b=Boolean.FALSE;
    VaadinSession session = getSession();
    User user = repositoryUser.getUser(key);
    if (user != null) {
      setUserCookie(user);
      session.setAttribute(key, user);
      b=Boolean.TRUE;
    }
    return b;
  }

  public boolean createSession(User user) {
    boolean b=Boolean.FALSE;
    VaadinSession session = getSession();
    if (user != null) {
      setUserCookie(user);
      session.setAttribute(user.getEmail(), user);
      b=Boolean.TRUE;
    }
    return b;
  }

  private void setUserCookie(User user) {
    Cookie cookie = new Cookie(USER_KEY, user.getEmail());
    cookie.setMaxAge(-1);
    cookie.setPath("/");
    VaadinService.getCurrentResponse().addCookie(cookie);

  }

  public String getCookie() {
    Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
    for (Cookie c : cookies) {
      if (c.getName().equals(USER_KEY)) {
        return c.getValue();
      }
    }
    return null;
  }


   public boolean isUserExist(String email,String password){
     boolean b=Boolean.FALSE;
     User user = repositoryUser.getUser(email);

     if(user!=null && user.getPassword().equals(password)) {
       b=createSession(user);
     }else{
       String key = getCookie();
       b=isUserLoginIn(key);
     }

     return b && user != null ? user.getPassword().equals(password) :  Boolean.FALSE;
   }

  public User getCurrentUser() {
    User user = null;
    String key = getCookie();
    VaadinSession session = getSession();
    if (key != null) {

      user = (User) session.getAttribute(key);
    }

    return user;
  }

  public User getCurrentUser(String email) {
    VaadinSession session = getSession();
    return (User) session.getAttribute(email);
  }
}
