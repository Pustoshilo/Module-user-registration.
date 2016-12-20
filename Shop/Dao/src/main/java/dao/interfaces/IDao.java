package dao.interfaces;

import java.io.Serializable;


public interface IDao<T extends Serializable>  {
    T select(long id);
    void delete(T t);
    void deleteAll();
    public T saveOrUpdate(T t);
    public T save(T t);

   }