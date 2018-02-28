package my.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import my.form.binders.entities.ForeignWordData;
import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.User;

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
        buttonSave.setStyleName(ValoTheme.BUTTON_SMALL);
        buttonCancel.setStyleName(ValoTheme.BUTTON_SMALL);
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
        User user = myUI.getCurrentUser();
        Foreignwords foreignwords = myUI.getRepository().find(foreignWordData.getForeignWord());

        if (foreignwords == null) {
            foreignwords = new Foreignwords();
        }

        foreignwords = (Foreignwords) myUI.getConverter().convert(foreignwords, foreignWordData);
        foreignwords.getUsers().add(user);
        user.getForeignwords().add(foreignwords);
        myUI.getRepository().save(foreignwords);
        myUI.updateGrid(null);
        System.out.println(foreignWordData);
        clean();
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

    private void clean(){
        foreignWord.setValue("");
        translationWord.setValue("");
    }
}
