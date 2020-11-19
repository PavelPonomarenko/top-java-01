package com.gmail.ponomarenko.service.jdbc;

import com.gmail.ponomarenko.service.UserMealServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.gmail.ponomarenko.Profiles.JDBC;
import static com.gmail.ponomarenko.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, JDBC})
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}