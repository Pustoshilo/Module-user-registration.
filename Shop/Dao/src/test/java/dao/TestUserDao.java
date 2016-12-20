package dao;

import dao.interfaces.IRoleDao;
import dao.interfaces.IUserDao;
import model.Role;
import model.User;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Component
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestUserDao {

    @Autowired()
    private IUserDao userDao;
    @Autowired()
    private IRoleDao roleDao;


    @Test
    public void testGetUser1() {
        User user = (User) userDao.select(null);
        Assert.assertNull(user);
    }

    @Test
    public void testGetUser2() {
        User user = (User) userDao.select("non_existent_user");
        Assert.assertNull(user);
    }


    @Test
    public void addUser() {
        User user = new User();
        user.setName("Testname1@mail.ru");
        user.setPassword("test_password");
        user.setSalt("test_salt");

        Role role = new Role();
        role.setName("test_role");
        Role persistentRole = (Role) roleDao.saveOrUpdate(role);
        Assert.assertNotNull(persistentRole.getId());

        user.setRole(persistentRole);


        User persistent = (User) userDao.saveOrUpdate(user);
        Assert.assertNotNull(persistent.getId());
        persistent = (User) userDao.select(persistent.getName());
        Assert.assertEquals("Person not persist", user, persistent);
    }



   /*

    @After
     public void deleteUser() {



        }
*/


}
