package dao;

import dao.interfaces.IRoleDao;
import dao.interfaces.IUserDao;
import model.Role;
import model.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Assert;
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
public class Aspect {

    @Autowired()
    private IUserDao userDao;
    @Autowired()
    private IRoleDao roleDao;


    @Pointcut("execution(* TestUserDao.addUser())")
    public void addUser1(){}


     @AfterReturning("addUser1()")
    public void afterWork() {
      //  System.out.println("++++++++++++++++++++++++++++++++++++++++start aspect  - afterWork ");

        Role persistentRole = (Role) roleDao.select("test_role");
        roleDao.delete(persistentRole);
        Assert.assertNull((Role) roleDao.select("test_role"));

         User persistentUser = (User)userDao.select("Testname1@mail.ru");
         userDao.delete(persistentUser);
         Assert.assertNull((User) userDao.select("Testname1@mail.ru"));

    }

}
