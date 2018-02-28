package my.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import my.hibernate.entities.User;
import org.hibernate.exception.ConstraintViolationException;

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
    save.setStyleName(ValoTheme.BUTTON_SMALL);
    VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.addComponentsAndExpand(email,name,secondName,password,confirmPassword,save);
    setClickEvent();
    addComponent(verticalLayout);
  }

  private void setClickEvent(){
    save.addClickListener(clickEvent -> {

     if(validate()){
       try {
         myUI.createUser(
             email.getValue(),
             password.getValue(),
             name.getValue(),
             secondName.getValue());
         Notification.show("Registration ok");
       }catch (ConstraintViolationException e) {
         Notification.show("User exist","This email exist" ,Notification.Type.ERROR_MESSAGE);
       }
       myUI.changView(Views.LOGIN);
     };

    });
  }

  private boolean validate() {

    if (showMessage(email.getValue(), email.getCaption())) {
      email.focus();
    }else{

      User user = myUI.repositoryUser.getUser(email.getValue());
      if(user != null) {
      Notification.show("Error","This email exist",Notification.Type.ERROR_MESSAGE);
      email.focus();
      return Boolean.FALSE;
      }
    }

    if ( showMessage(password.getValue(), password.getCaption())) {
      Notification.show("Error","Please fill out your  password",Notification.Type.ERROR_MESSAGE);
      password.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(name.getValue(), name.getCaption())) {
      Notification.show("Error","Please fill out your name",Notification.Type.ERROR_MESSAGE);
      name.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(secondName.getValue(), secondName.getCaption())) {
      Notification.show("Error","Please fill out your second name",Notification.Type.ERROR_MESSAGE);
      secondName.focus();
      return Boolean.FALSE;
    }

    if ( showMessage(confirmPassword.getValue(), confirmPassword.getCaption())) {
      Notification.show("Error","Please confirm your password",Notification.Type.ERROR_MESSAGE);
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
