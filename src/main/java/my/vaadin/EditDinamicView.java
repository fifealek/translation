package my.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.Translation;

import java.util.Set;

public class EditDinamicView extends VerticalLayout implements View {
  private Button save = new Button("Save");
  private Button cancel = new Button("Cancel");
  private MyUI myUI;
  private TextField textForeignWords = new TextField("Foreign word");
  private TextField textTranslatesArray[];

  public EditDinamicView(Foreignwords foreignwords, MyUI myUI) {
    this.myUI = myUI;
    textForeignWords.setValue(foreignwords.getForeignWord());
    textForeignWords.setId(String.valueOf(foreignwords.getID()));
    Set<Translation> translations = foreignwords.getTranslations();
    textTranslatesArray = new TextField[translations.size()];

    int index = -1;

    for (Translation translation : translations) {
      index++;
      textTranslatesArray[index] = new TextField("Translation");
      textTranslatesArray[index].setId(String.valueOf(translation.getID()));
      textTranslatesArray[index].setValue(translation.getTranslateWord());
    }
    addComponent(textForeignWords);
    addComponents(textTranslatesArray);
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    horizontalLayout.addComponents(cancel, save);
    addComponent(horizontalLayout);
    setActions();
  }

  public void setActions() {
    save.addClickListener(event -> {
      prepareDataForSaved();
      cancel();
    });

    cancel.addClickListener(event -> {
      this.cancel();
    });
  }

  public void prepareDataForSaved() {
    Long id = Long.parseLong(textForeignWords.getId());
    Foreignwords foreignwords = myUI.getRepository().find(id);
    if (foreignwords != null) {

      if (!foreignwords.getForeignWord().equals(textForeignWords.getValue())) {
        foreignwords.setForeignWord(textForeignWords.getValue());
      }

      Set<Translation> translations = foreignwords.getTranslations();
      for (TextField textField : textTranslatesArray) {
        Long idtranslate = Long.parseLong(textField.getId());
        String translateWord = textField.getValue();
        for (Translation translation : translations) {
          if (idtranslate == translation.getID() && !translation.getTranslateWord().equals(translateWord)) {
            translation.setTranslateWord(translateWord);
            translation.setTranslateWordLong(translateWord);
          }
          translation.setForeignwords(foreignwords);
        }
      }
      myUI.getRepository().save(foreignwords);
    }

  }

  private void cancel() {
    myUI.updateGrid(null);
    myUI.changView(Views.TRANSLATION);
  }

}
