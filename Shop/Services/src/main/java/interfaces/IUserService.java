package interfaces;


import pagenator.Pagenator;

import java.util.List;

public interface IUserService <User> {
    List<User> selectAll();
    List<User> selectAll(long firstResult, long maxResult);
    public User saveOrUpdate(User t);
//    public User saveOrUpdate(String name, String password, String roleName);
    void delete(User t);
    String delete(Long id);
    public void   selectAll(Pagenator pagenatorUser);
    User select(long id);
    User getUser(String name);
    public User checkPassword (User userUI);
   }
