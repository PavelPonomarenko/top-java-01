package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.repository.JpaUtil;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class HelperJpaUtilUserServiceTest extends UserServiceTest {

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
