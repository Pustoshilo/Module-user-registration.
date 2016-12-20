package dao;


import dao.interfaces.IRoleDao;
import model.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleDao extends BaseDao<Role> implements IRoleDao {

    private static final Logger logger = LogManager.getLogger(RoleDao.class);

    public RoleDao()  {
        super(Role.class);
    }


@Override
    public Role select(String name)  {

             Criteria criteria =  getSession().createCriteria(Role.class).add(Restrictions.like("name", name));
            Role role = (Role)criteria.uniqueResult();
               return role;

    }


    @Override
    public Role saveOrUpdate(Object o) {
        return saveOrUpdate((Role)o);
    }

    @Override
    public void delete(Object o) {
        super.delete((Role)o);
    }

    @Override
    public Role save(Role role)  {
        return super.save(role);
    }

    @Override
    public void deleteAll()  {

    }

    @Override
    public List<Role> selectAll() {
        Criteria criteria =  getSession().createCriteria(getPersistentClass());
        List<Role> objects = criteria.list();
        logger.info("selectAll:" + objects);
        return objects;
    }


    @Override
    public Role select(long id)  {
        Criteria criteria =  getSession().createCriteria(Role.class).add(Restrictions.eq("id", id));
        Role role = (Role)criteria.uniqueResult();
        return role;
    }


}
