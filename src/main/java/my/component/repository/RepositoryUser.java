package my.component.repository;

import java.util.List;

public interface RepositoryUser<T,U> {
  T getUser(U id);
  List<T> getUsers();
  T getUser(String email);
  void save(T t);
}
