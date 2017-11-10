package my.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import my.component.converte.Converter;
import my.component.repository.RepositoryForeiginWords;
import my.form.binders.entities.ForeignWordData;
import my.hibernate.entities.Foreignwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


public class ForeignWordForm extends FormLayout {


    private TextField foreignWord = new TextField("Foreign word");
    private TextField translationWord = new TextField("Translation");
    private NativeSelect<String> translationLanguage = new NativeSelect();

    private Button buttonSave = new Button("Save");
    private Button buttonCancel = new Button("Cancel");
    private MyUI myUI;
    private ForeignWordData foreignWordData;
    private Binder<ForeignWordData> binder = new Binder<>(ForeignWordData.class);


    public ForeignWordForm(MyUI myUI) {
        this.myUI = myUI;
        Collection<String> languages = getAllLanguages();
        setSizeUndefined();
        buttonSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        buttonCancel.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        translationLanguage.setItems(languages.toArray(new String[languages.size()]));
        HorizontalLayout horizontalLayoutButtons = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        horizontalLayoutButtons.addComponents(buttonCancel, buttonSave);
        addComponents(foreignWord, translationWord, translationLanguage, horizontalLayoutButtons);

        binder.bindInstanceFields(this);
        buttonSave.addClickListener(e -> save());
        buttonCancel.addClickListener(e -> cancel());
        setForeignWordData(new ForeignWordData());
    }

//    public void setForeignWordData(ForeignWordData foreignWordData) {
//        this.foreignWordData=foreignWordData;
//        binder.setBean(foreignWordData);
//        setVisible(true);
//    }


    public void setForeignWordData(ForeignWordData foreignWordData) {
        this.foreignWordData = foreignWordData;
        binder.setBean(foreignWordData);
        setVisible(true);

    }

    public void save() {
        Foreignwords foreignwords = myUI.getRepository().find(foreignWordData.getForeignWord());
        if (foreignwords != null) {
            Foreignwords foreignwordsNew = (Foreignwords) myUI.getConverter().convert(foreignwords, foreignWordData);
            myUI.getRepository().save(foreignwordsNew);
        } else {
            myUI.getRepository().save(myUI.getConverter().convert(new Foreignwords(), foreignWordData));
        }

        myUI.updateGrid();
        System.out.println(foreignWordData);
    }

    public void cancel() {
        setVisible(false);
    }

    private Collection<String> getAllLanguages() {
        Set<String> languages = Arrays.stream(Locale.getISOLanguages())
                .map(Locale::new)
                .map(Locale::getDisplayLanguage)
                .collect(Collectors.toCollection(TreeSet::new));
        return languages;
    }
}
