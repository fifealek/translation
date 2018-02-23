package my.hibernate.entities;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name ="FOREIGNWORDS")
public class Foreignwords {

    //@Id
    //@GeneratedValue(generator = Generators.ID_GENERATOR)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    private String foreignWord;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    @CreationTimestamp
    private Date timestamp;

    @OneToMany(mappedBy = "foreignwords", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Translation> translations = new HashSet();



//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = true)
//    private User user;

    //@ManyToMany(fetch = FetchType.EAGER, mappedBy = "foreignwords")
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
        name ="user_foreignwords",
        joinColumns = @JoinColumn(name="foregnwords_id"),
        inverseJoinColumns = @JoinColumn(name="users_id")

    )
    private Set<User> users=new HashSet<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foreignwords that = (Foreignwords) o;

        if (ID != null ? !ID.equals(that.ID) : that.ID != null) return false;
        if (foreignWord != null ? !foreignWord.equals(that.foreignWord) : that.foreignWord != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (foreignWord != null ? foreignWord.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
