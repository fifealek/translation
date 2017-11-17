package my.vaadin;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import my.component.DaoService;
import my.component.converte.Converter;
import my.component.repository.RepositoryForeiginWords;
import my.form.binders.entities.ForeignWordTable;
import my.hibernate.entities.Foreignwords;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


import java.util.Collections;
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
@Theme("valo")
public class MyUI extends UI {

//    @Resource
//    private DaoService daoService;

    @Resource
    private RepositoryForeiginWords repository;

    @Resource
    private Converter converter;

    private Grid<ForeignWordTable> grid = new Grid(ForeignWordTable.class);


    private ForeignWordForm foreignWordForm = new ForeignWordForm(this);

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();
        grid.setColumns("ID", "foreign","translation");
        grid.setSizeUndefined();
        HorizontalLayout main = new HorizontalLayout();
        main.addComponents(grid, foreignWordForm);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);
        if (repository != null) {
            updateGrid();
        }
        //String m = getMessage();
//        setContent(new Button("Click me", e -> Notification.show(m + " Hello Spring+Vaadin user!")));
        verticalLayout.addComponent(main);
        setContent(verticalLayout);
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

    public void updateGrid()
    {
        grid.setItems(
                (List) repository.findAll().stream().
                        map( t-> new ForeignWordTable((Foreignwords)t)).
                        collect(toList())
        );
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
}
