package interfaces;

import java.io.Serializable;


public interface IService<T extends Serializable>  {
    T select(long id);
      void delete(T t);
      public T saveOrUpdate(T t);
    public T save(T t);

}