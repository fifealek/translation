package my.hibernate.entities;

import javax.persistence.*;

@Entity
public class Translation {

  //  @Id
  //  @GeneratedValue(generator = Generators.ID_GENERATOR)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;

    private String translateWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreignwords_id",nullable = false)
    private Foreignwords foreignwords;

    @Lob
    private byte[] translateWordBytes;

    @Lob
    private String translateWordLong;

    public Translation() {
    }

    public Translation(String translateWord) {
        this.translateWord = translateWord;
    }

    public Translation(String translateWord, Foreignwords foreignwords,byte[] translateWordBytes) {
        this.translateWord = translateWord;
        this.foreignwords = foreignwords;
        this.translateWordBytes=translateWordBytes;
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

    public byte[] getTranslateWordBytes() {
        return translateWordBytes;
    }

    public void setTranslateWordBytes(byte[] translateWordBytes) {
        this.translateWordBytes = translateWordBytes;
    }

    public String getTranslateWordLong() {
        return translateWordLong;
    }

    public void setTranslateWordLong(String translateWordLong) {
        this.translateWordLong = translateWordLong;
    }
}
