package my.form.binders.entities;

import my.hibernate.entities.Foreignwords;

import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ForeignWordTable {

    private Long ID;
    private String foreign;
    private String  translation;

    public ForeignWordTable() {
    }
    public ForeignWordTable(Foreignwords foreignwords) {
       ID= foreignwords.getID();
       foreign =foreignwords.getForeignWord();
       translation = foreignwords.getTranslations().stream().map(t->t.getTranslateWord()).collect(Collectors.joining(", "));
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
