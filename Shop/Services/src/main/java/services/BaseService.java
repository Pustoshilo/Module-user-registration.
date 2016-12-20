package services;

import dao.BaseDao;
import interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public abstract class BaseService<T extends Serializable> implements IService<T> {

    @Autowired(required = false)
private BaseDao<T> baseDao;


    @Override
    public T select(long id) {
        return baseDao.select(id);
    }


    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public T saveOrUpdate(T t) {
        return baseDao.saveOrUpdate(t);
    }

    @Override
    public T save(T t) {
        return baseDao.save(t);
    }
}
