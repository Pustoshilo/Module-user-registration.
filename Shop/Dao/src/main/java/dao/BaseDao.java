package dao;


import dao.interfaces.IDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@SuppressWarnings("unchecked")
@Repository()
public abstract class BaseDao<T extends Serializable> implements IDao<T> {

    private final Class<T> persistentClass;
    private static final Logger logger = LogManager.getLogger(BaseDao.class);


   protected BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
     }

 //   public BaseDao(){
 //       this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
 //   }


    @Autowired
    private SessionFactory sessionFactory;


    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public T saveOrUpdate(T t) {
     //   Session session = HibernateUtil.getSession();
        getSession().saveOrUpdate(t);
        logger.info("saveOrUpdate: " + t);
        return t;
    }

@Override
    public T save(T t) {
    getSession().save(t);
    logger.info("save: " + t);
        return t;
    }


    @Override
    public void delete(T t) {
        getSession().delete(t);
        logger.info("delete: " + t);
    }




    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

}







