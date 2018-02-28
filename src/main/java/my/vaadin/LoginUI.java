package my.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

//@SpringUI
//@Theme("valo1")
public class LoginUI extends VerticalLayout  implements View{
  private TextField login=new TextField("Login");
  private PasswordField password=new PasswordField("Password");
  private Button send =new Button("Login in");
  private Button registration =new Button("Registration");

  private MyUI myUI;

  public LoginUI(MyUI myUI) {
    this.myUI=myUI;
    init();
  }

  protected void init() {
    setClickEvent();
    //setStyleName(ValoTheme.BUTTON_FRIENDLY);
    send.setStyleName(ValoTheme.BUTTON_SMALL);
    registration.setStyleName(ValoTheme.BUTTON_SMALL);
    HorizontalLayout horizontalLayout=new HorizontalLayout();
    VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.addComponent(login);
    verticalLayout.addComponent(password);
    //verticalLayout.addComponent(send);
    //registration.setWidth("100");
    //send.setWidth("100");
    //verticalLayout.addComponentsAndExpand(registration,send);
    horizontalLayout.setWidth("21%");
    horizontalLayout.addComponentsAndExpand(registration,send);
    verticalLayout.addComponent(horizontalLayout);

    addComponent(verticalLayout);
  }

  public void setClickEvent(){
    send.addClickListener(clickEvent -> {
      boolean isExistUser = myUI.isUserExist(login.getValue(),password.getValue());
      if(isExistUser) {
        myUI.updateGrid(login.getValue());
        myUI.navigator.navigateTo(Views.TRANSLATION.name());
      }else{
        Notification.show("User do not exist");
        myUI.navigator.navigateTo(Views.LOGIN.name());
      }
    });
    registration.addClickListener(clickEvent -> {
      myUI.changView(Views.REGISTRATION);
    });
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    Notification.show("Welcome to the Animal Farm");
  }
}
