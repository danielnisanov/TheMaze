package Dal;

public interface IDAO<T> {
    public void Insert(T obj);
    public  void Delete();
    public  void Find();
}
