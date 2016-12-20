package dao;

import dao.interfaces.IUserDao;
import model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDao extends BaseDao<User> implements IUserDao {

    private static final Logger logger = LogManager.getLogger(UserDao.class);



    public UserDao()  {
        super(User.class);
    }


    @Override
    public User select(String name, String password)  {
// устарело - вариант работы без соли, просто сравниваем пароль в базе и с UI

        //    String query = "select * from users where login='"+name+"' and password=PASSWORD('"+password+"')";
             Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.like("name", name));
            criteria.add(Restrictions.like("password", password));
            User user = (User) criteria.uniqueResult();
            return user;
      }

    @Override
    public User select(String name) {

            Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.like("name", name));
            User user = (User) criteria.uniqueResult();
            return user;
    }


    @Override
    public User saveOrUpdate(Object o) {
         return super.saveOrUpdate((User)o);
        }


    @Override
    public void delete(Object o) {
            super.delete((User)o);
      }


    @Override
    public List<User> selectAll() {
           Criteria criteria =  getSession().createCriteria(getPersistentClass());
            List<User> objects = criteria.list();
        logger.info("selectAll:" + objects);
            return objects;
          }


    @Override
    public List<User> selectAll(long firstResult, long maxResult) {
            Criteria criteria = getSession().createCriteria(getPersistentClass());
            criteria.setFirstResult((int)firstResult);
            criteria.setMaxResults((int) maxResult);
            criteria.addOrder(Order.asc("name"));
            List<User> objects = criteria.list();
        logger.info("selectAll:" + objects);
            return objects;
         }


    @Override
    public long rowCount(Object t) {
              List<Long> results = getSession().createCriteria(getPersistentClass())
                    .setProjection( Projections.rowCount() )
                    .list();

            if (results != null && results.size() > 0) {
                logger.info("rowCount: " + results.size());
                return results.get(0);
            } else {
                logger.info("rowCount: 0");
                return 0;
            }
        }


    @Override
    public User select(long id) {
            Criteria   criteria =  getSession().createCriteria(getPersistentClass()).add(Restrictions.eq("id",id));
            User user = (User)criteria.uniqueResult();
            logger.info("select: " + user);
            return user;
        }


    @Override
    public void deleteAll()  {

    }


}
