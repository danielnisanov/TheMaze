package Domain;

public interface IRepository<T> {
    public void Insert(T obj);
    public  void Update(T obj,int id, String string);
    public  void Delete(T obj);
    public  void Find(int id);
}
