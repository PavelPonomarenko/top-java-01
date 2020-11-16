package com.gmail.ponomarenko.service.datajpa;

import com.gmail.ponomarenko.service.UserMealServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.gmail.ponomarenko.Profiles.DATAJPA;
import static com.gmail.ponomarenko.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
}