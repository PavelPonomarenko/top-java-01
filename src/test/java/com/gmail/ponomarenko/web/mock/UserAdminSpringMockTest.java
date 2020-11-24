package com.gmail.ponomarenko.web.mock;

import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import com.gmail.ponomarenko.web.user.AdminUserRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml",
        "classpath:spring/mock.xml", "classpath:spring/spring-mvc.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAdminSpringMockTest {
    @Autowired
    private AdminUserRestController controller;


    @Test
    public void testCreate() throws Exception {
        controller.create(new User(null, "Name", "email@gamil.com", "newPass", true, Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(7);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(0);
    }
}