package Domain;
import java.util.List;

public interface IRepository<T> {
    void add(T entity);
    void update(T entity);
    void remove(String id);
    T get(String id);
    List<T> getAll();
}