package com.gmail.ponomarenko;

import com.gmail.ponomarenko.web.meal.UserMealRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.getEnvironment().setActiveProfiles(Profiles.POSTGRES);
            ctx.load("spring/spring-app.xml", "spring/mock.xml");
            ctx.refresh();
            System.out.println("\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n");
            UserMealRestController adminController = ctx.getBean(UserMealRestController.class);
            adminController.delete(7);

        }
    }
}
