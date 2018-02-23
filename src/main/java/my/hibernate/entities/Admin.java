package my.hibernate.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AA")
public class Admin extends User {

  public Admin(){}
  public Admin(String email,String password,String name,String secondName){
    super(email,password,name,secondName);
  }
}
