package my.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class RegistrationView extends VerticalLayout implements View {
  private TextField email=new TextField("Email");
  private TextField name=new TextField("Name");
  private TextField secondName = new TextField("Second name");
  private PasswordField password = new PasswordField("Password");
  private PasswordField confirmPassword=new PasswordField("Password confirmation");
  private Button save=new Button("Save");
  private MyUI myUI;
  public RegistrationView(MyUI myUI){
    this.myUI=myUI;
    VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.addComponentsAndExpand(email,name,secondName,password,confirmPassword,save);
    setClickEvent();
    addComponent(verticalLayout);
  }

  private void setClickEvent(){
    save.addClickListener(clickEvent -> {

     if(validate()){
       myUI.createUser(
           email.getValue(),
           password.getValue(),
           name.getValue(),
           secondName.getValue());
     };
      Notification.show("Registration ok");
    });
  }

  private boolean validate() {

    if (showMessage(email.getValue(), email.getCaption())) {
      email.focus();
    }

    if ( showMessage(password.getValue(), password.getCaption())) {
      password.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(name.getValue(), name.getCaption())) {
      name.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(secondName.getValue(), secondName.getCaption())) {
      secondName.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(confirmPassword.getValue(), confirmPassword.getCaption())) {
      confirmPassword.focus();
      return Boolean.FALSE;
    }

    return Boolean.TRUE;
  }

  private boolean showMessage(String value,String nameField) {
    if(value==null || value.isEmpty()) {
      Notification.show("Field "+nameField +"is empty");
    }
    return value==null || value.isEmpty();
  }
}
