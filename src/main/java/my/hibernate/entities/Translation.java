package my.hibernate.entities;

import javax.persistence.*;

@Entity
public class Translation {

    @Id
    @GeneratedValue(generator = Generators.ID_GENERATOR)
    private Long ID;

    private String translateWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreignwords_id",nullable = false)
    private Foreignwords foreignwords;

    public Translation() {
    }

    public Translation(String translateWord) {
        this.translateWord = translateWord;
    }

    public Translation(String translateWord, Foreignwords foreignwords) {
        this.translateWord = translateWord;
        this.foreignwords = foreignwords;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }


    public String getTranslateWord() {
        return translateWord;
    }

    public void setTranslateWord(String translateWord) {
        this.translateWord = translateWord;
    }

    public Foreignwords getForeignwords() {
        return foreignwords;
    }

    public void setForeignwords(Foreignwords foreignwords) {
        this.foreignwords = foreignwords;
    }
}
