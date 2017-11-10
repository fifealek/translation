package my.component.repository;

import my.hibernate.entities.Foreignwords;

import java.util.List;

public interface RepositoryForeiginWords<ForeignWords,Long>  {
    List findAll();
    Foreignwords  find(String word);
    void save(ForeignWords word);
}
