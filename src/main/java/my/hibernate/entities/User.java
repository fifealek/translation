package my.hibernate.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="USERS",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  @NotNull
  @Column (nullable = false)
  private String email;
  @NotNull
  @Column (nullable = false)
  private String password;
  @Column (nullable = false)
  private String name;
  @Column (nullable = false)
  private String secondName;

//  @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
//  private List<Foreignwords> foreignwords;


//  @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//  @JoinTable(
//      name ="user_foreignwords",
//      joinColumns = @JoinColumn(name="users_id"),
//      inverseJoinColumns = @JoinColumn(name="foregnwords_id")
//
//  )
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
  private Set<Foreignwords> foreignwords =new HashSet<>();

  //@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//  @ElementCollection(fetch = FetchType.EAGER)
//  @JoinColumn(name = "user_id")
//  private List<Foreignwords> list =new ArrayList<>();

  public User(){}

  public User(String email,String password,String name,String secondName){
    this.email=email;
    this.name=name;
    this.password=password;
    this.secondName=secondName;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (!email.equals(user.email)) return false;
    if (!password.equals(user.password)) return false;
    if (!name.equals(user.name)) return false;
    return secondName.equals(user.secondName);
  }

  @Override
  public int hashCode() {
    int result = email.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + secondName.hashCode();
    return result;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getSecondName() {
    return secondName;
  }

//  public List<Foreignwords> getForeignwords() {
//    return foreignwords;
//  }
//
//  public void setForeignwordsSet(List<Foreignwords> foreignwords) {
//    this.foreignwords = foreignwords;
//  }


//  public List<Foreignwords> getList() {
//    return list;
//  }
//
//  public void setList(List<Foreignwords> list) {
//    this.list = list;
//  }

  public void setForeignwords(Set<Foreignwords> foreignwords) {
    this.foreignwords = foreignwords;
  }

  public Set<Foreignwords> getForeignwords() {
    return foreignwords;
  }
}
