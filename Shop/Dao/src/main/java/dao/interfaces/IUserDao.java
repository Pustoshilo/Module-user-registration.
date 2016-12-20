package dao.interfaces;


import java.util.List;

public interface IUserDao<User>  {

    public User select(String name, String password);
    public User select(String name);
    public User saveOrUpdate(User user);
    void delete(User t);
    List<User> selectAll(long firstResult, long maxResult);
    List<User> selectAll();
    public long rowCount(User t);
    User select(long id);

}
