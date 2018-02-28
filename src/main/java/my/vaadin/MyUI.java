package my.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import my.component.converte.Converter;
import my.component.repository.RepositoryForeiginWords;
import my.form.binders.entities.ForeignWordTable;
import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.User;


import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
//@Theme("valo")
@Theme("mytheme")
public class MyUI extends BaseUIWindow {

//    @Resource
//    private DaoService daoService;





    private LoginUI loginUI=new LoginUI(this);

    private RegistrationView registrationView = new RegistrationView(this);
    protected Navigator navigator;
    private Grid<ForeignWordTable> grid = new Grid(ForeignWordTable.class);
    private GridTranslateWords gridTranslateWords = new GridTranslateWords(this,grid);


    @Override
    protected void init(VaadinRequest request) {
         navigator = new Navigator(this, this);;

        navigator.addView(Views.LOGIN.name(),loginUI);
        navigator.addView(Views.REGISTRATION.name(),registrationView);
        navigator.addView(Views.TRANSLATION.name(), gridTranslateWords);
        if(!isUserLoginIn(getCookie())){
           //navigator.addView("Login",loginUI);
           navigator.navigateTo(Views.LOGIN.name());
            return;
        }else {
            updateGrid(null);
            navigator.navigateTo(Views.TRANSLATION.name());
        }

    }

//    private String getMessage() {
//        String message = "null";
//        Session session = null;
//        try {
//            UserTransaction tx = daoService.getuserTransaction();
//            tx.begin();
//            session = daoService.getSessionFactory().getCurrentSession();
//            List<Foreignwords> list = session.createCriteria(Foreignwords.class).list();
//            if (list.size() > 0) {
//                message = list.get(0).getForeignWord();
//            }
//            tx.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //daoService.rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return message;
//    }

//    private List getForeignWords() {
//        List<Foreignwords> list = Collections.EMPTY_LIST;
//        Session session = null;
//        try {
//            UserTransaction tx = daoService.getuserTransaction();
//            tx.begin();
//            session = daoService.getSessionFactory().getCurrentSession();
//            list = session.createCriteria(Foreignwords.class).list();
//            tx.commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //daoService.rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return list;
//    }

//    public List<Foreignwords> getList() {
//        UserTransaction tx = daoService.getuserTransaction();
//        EntityManager entityManager = daoService.createEntityManager();
//        List<Foreignwords> list = Collections.EMPTY_LIST;
//        try {
//            tx.begin();
//            list = entityManager.createQuery("select f from Foreignwords f").getResultList();
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            daoService.rollback();
//        } finally {
//            entityManager.close();
//        }
//
//        return list;
//    }

    public void updateGrid(String email) {
        User user = null;
        if (email == null) {
            user = getCurrentUser();
        } else {
            user = getCurrentUser(email);
        }

        if (user != null && user.getForeignwords() != null) {
            user = repositoryUser.getUser(user.getEmail());
            grid.setItems(
                (List) user.getForeignwords().stream().
                    map(t -> new ForeignWordTable((Foreignwords) t)).
                    collect(toList())
            );

        } else {
            grid.setItems(
                (List) repository.findAll().stream().
                    map(t -> new ForeignWordTable((Foreignwords) t)).
                    collect(toList())
            );
        }
    }

    public RepositoryForeiginWords getRepository() {
        return repository;
    }

    public void setRepository(RepositoryForeiginWords repository) {
        this.repository = repository;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void changView(Views views){
        navigator.navigateTo(views.name());
    }

    public void createUser(String email, String password, String name, String secondname) {
        User newUser = new User(email, password, name, secondname);
        repositoryUser.save(newUser);
    }

    public void editView(ForeignWordTable foreignWordTable) {
        if (foreignWordTable != null) {
            Long id = foreignWordTable.getID();
            Foreignwords foreignwords = repository.find(id);
            if (foreignwords != null) {
                navigator.removeView(Views.EDIT.name());
                EditDinamicView editView = new EditDinamicView(foreignwords, this);
                navigator.addView(Views.EDIT.name(), editView);
                changView(Views.EDIT);
            }
        }
    }
}
