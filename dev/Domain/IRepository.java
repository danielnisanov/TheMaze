package Domain;

public interface IRepository<T> {
    public void Insert(T obj);
    public  void Update();
    public  void Delete();
    public  void Find();
}
