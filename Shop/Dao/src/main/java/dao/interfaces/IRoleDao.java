package dao.interfaces;


import java.util.List;

public interface IRoleDao<Role>  {

    public Role select(String name);
    public Role saveOrUpdate(Role role);
    void delete(Role t);
    List<Role> selectAll();
}