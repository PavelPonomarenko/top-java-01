package com.gmail.ponomarenko.web.mock;

import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import com.gmail.ponomarenko.web.user.AdminUserRestController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAdminSpringMockTest {
    @Autowired
    private  AdminUserRestController controller;


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
//    private static ConfigurableApplicationContext appContx;

//    @Before
//    public static void beforeClass() {
//
//        appContx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
//        System.out.println("\n" + Arrays.toString(appContx.getBeanDefinitionNames()) + "\n");
////        AdminUserRestController adminUserRestController = applicationContext.getBean(AdminUserRestController.class);
////        adminUserRestController.delete(1);
//        controller = appContx.getBean(AdminUserRestController.class);
//    }
//
//    @After
//    public void afterClass() {
//        appContx.close();
//    }

}