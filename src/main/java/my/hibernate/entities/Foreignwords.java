package my.hibernate.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name ="FOREIGNWORDS")
public class Foreignwords {

    @Id
    @GeneratedValue(generator = Generators.ID_GENERATOR)
    private Long ID;

    @NotNull
    private String foreignWord;

    @OneToMany(mappedBy = "foreignwords",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Translation> translations=new HashSet();

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
}
