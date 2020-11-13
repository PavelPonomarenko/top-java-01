package com.gmail.ponomarenko;

import com.gmail.ponomarenko.web.meal.UserMealRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml")) {
            System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
            UserMealRestController adminController = appCtx.getBean(UserMealRestController.class);
            adminController.delete(7);
        }
    }
}
