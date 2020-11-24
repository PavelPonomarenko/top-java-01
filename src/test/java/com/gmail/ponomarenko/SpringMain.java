package com.gmail.ponomarenko;

import com.gmail.ponomarenko.web.meal.UserMealRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(Profiles.POSTGRES);
        ctx.load("classpath:spring/spring-app.xml", "classpath:spring/mock.xml","classpath:spring/spring-mvc.xml");
        ctx.refresh();
        System.out.println("\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n");
        UserMealRestController adminController = ctx.getBean(UserMealRestController.class);
        adminController.delete(7);
    }
}

