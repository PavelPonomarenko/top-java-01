package com.gmail.ponomarenko;

import com.gmail.ponomarenko.web.user.AdminUserRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "classpath:spring/mock.xml");
        System.out.println("\n" + Arrays.toString(applicationContext.getBeanDefinitionNames()) + "\n");
        AdminUserRestController adminUserRestController = applicationContext.getBean(AdminUserRestController.class);
        adminUserRestController.delete(1);

        applicationContext.close();

    }
}
