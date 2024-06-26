package Domain;
import java.util.List;

public interface IRepository<T> {
    void add(T entity) throws Exception;
//    void update(T entity);
    void remove(String id) throws Exception;
    T get(String id) throws Exception;
}