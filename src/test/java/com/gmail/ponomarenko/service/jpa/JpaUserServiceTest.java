package com.gmail.ponomarenko.service.jpa;

import com.gmail.ponomarenko.service.OrmUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.gmail.ponomarenko.Profiles.JPA;
import static com.gmail.ponomarenko.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, JPA})
public class JpaUserServiceTest extends OrmUserServiceTest {

}