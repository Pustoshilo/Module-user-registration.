package services;

import dao.RoleDao;
import dao.interfaces.IRoleDao;
import dao.interfaces.IUserDao;
import interfaces.IUserService;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pagenator.Pagenator;
import utils.LocaleSalt;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED,
        rollbackFor = Exception.class)
public class UserService extends BaseService<User> implements IUserService {


    private static Logger log = Logger.getLogger(UserService.class);

    @Autowired()
    IUserDao userDao;
    @Autowired()
    IRoleDao roleDao;

    @Autowired
    LocaleSalt salt;



    @Override
    public Object saveOrUpdate(Object user) {

        String roleName = ((User) user).getRole().getName();
        Role role = (Role) roleDao.select(roleName);

        if (role == null) {
                 role = (Role) roleDao.saveOrUpdate(new Role(roleName));    // create new role
        }

        ((User) user).setRole(role);

         salt.createHash( ((User)user).getPassword() );
        ((User) user).setPassword(salt.getHashPassword());
        ((User) user).setSalt(salt.getRandomSalt());

        User userPersistent = (User) userDao.saveOrUpdate( (User)user );
        return userPersistent;

    }


    @Override
    public Object checkPassword(Object userUI) {

          User userDatabase = (User)userDao.select(((User) userUI).getName());
        if (userDatabase == null) {
            return null;
        }

        //  salt.checkPassword(candidate_password, randomSalt, stored_hash)
if (salt.checkPassword(((User)userUI).getPassword(), userDatabase.getSalt(), userDatabase.getPassword())) {
            return userDatabase;  // check = true
        } else {
            return null;         // check = false
        }

    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public User getUser(String name) {

        User user = (User) userDao.select(name);
        return user;

    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public List selectAll(long firstResult, long maxResult) {

        List<User> users = userDao.selectAll(firstResult, maxResult);
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public List<User> selectAll() {
        List<User> users = (List<User>) userDao.selectAll();
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void selectAll(Pagenator pagenatorUser) {


        long count = userDao.rowCount(new User());   // total rows

        long firstResult = (pagenatorUser.getNextPage() - 1) * pagenatorUser.getRowsOnPage() - 1;
        if (firstResult < 0 || firstResult > count) {
            firstResult = 0;
        }

        long maxResult = pagenatorUser.getRowsOnPage();
        List<User> users = userDao.selectAll(firstResult, maxResult);

        pagenatorUser.setObjects(users);

        // total pages
        if (count % pagenatorUser.getRowsOnPage() == 0) {
            pagenatorUser.setCountPages(count / pagenatorUser.getRowsOnPage());
        } else {
            pagenatorUser.setCountPages(count / pagenatorUser.getRowsOnPage() + 1);
        }

    }


    @Override
    public String delete(Long id) {
        String name = "";
        User user = (User) userDao.select(id);
        if (user != null) {
            name = user.getName();
            userDao.delete(user);
        }

        return name;
    }


    @Override
    public void delete(Object t) {
        User userForDeletion = (User) userDao.select(((User) t).getName());
        if (userForDeletion != null) {
            userDao.delete(userForDeletion);
        }
    }


    public void deleteRole(String name) {
        Role roleForDeletion = (Role) roleDao.select(name);
        if (roleForDeletion != null) {
            new RoleDao().delete(roleForDeletion);
        }

    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public User select(long id) {

        User user = (User) userDao.select(id);
        return user;
    }


}
