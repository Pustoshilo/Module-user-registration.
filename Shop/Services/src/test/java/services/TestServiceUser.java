package services;

import interfaces.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Component
@ContextConfiguration("/test-beans-services.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestServiceUser {


    @Autowired()
    private IUserService userService;



    @Test (expected = NullPointerException.class)
    public void testDeleteUser() {
       userService.delete(null);
    }



    @Test (expected = NullPointerException.class)
    public void testGetUser() {
        userService.selectAll(null);
    }



}
