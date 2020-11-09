package com.gmail.ponomarenko;

import com.gmail.ponomarenko.web.meal.UserMealRestController;
import com.gmail.ponomarenko.web.user.AdminUserRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

//        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "classpath:spring/mock.xml");
//        System.out.println("\n" + Arrays.toString(applicationContext.getBeanDefinitionNames()) + "\n");
//        AdminUserRestController adminUserRestController = applicationContext.getBean(AdminUserRestController.class);
//        adminUserRestController.delete(1);
//
//        applicationContext.close();

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.getEnvironment().setActiveProfiles("postgres");
            ctx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/mock.xml");
            ctx.refresh();
            System.out.println("\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n");
            UserMealRestController adminController = ctx.getBean(UserMealRestController.class);
            adminController.delete(7);
        }


    }
}
