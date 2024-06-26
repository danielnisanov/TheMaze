package DAO;

import java.util.List;

public interface IDAO<T> {
    void add(T entity)  throws Exception;

    void remove(String id) throws Exception;

    T get(String id) throws Exception;
}