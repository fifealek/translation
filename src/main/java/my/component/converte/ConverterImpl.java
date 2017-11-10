package my.component.converte;

import my.form.binders.entities.ForeignWordData;
import my.hibernate.entities.Foreignwords;
import my.hibernate.entities.Translation;
import org.springframework.stereotype.Service;

@Service("converter")
public class ConverterImpl implements Converter<Foreignwords,ForeignWordData> {
    @Override
    public Foreignwords convert(Foreignwords foreignwords, ForeignWordData foreignWordData) {

        if(foreignwords.getForeignWord() == null){
            foreignwords.setForeignWord(foreignWordData.getForeignWord());
        };

        Translation translation=new Translation(foreignWordData.getTranslationWord(),foreignwords);
        foreignwords.getTranslations().add(translation);
        return foreignwords;
    }
}
