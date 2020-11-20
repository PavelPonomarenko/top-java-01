package com.gmail.ponomarenko.service.MackitoTest;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import com.gmail.ponomarenko.service.UserServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockitoTest {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServiceTest.class);

    @Mock
    protected UserService mockUserService;
    @Mock
    protected User mockUser;

    @Before
    public void setUp() throws Exception {
        LOG.info("| ----- Test in UserServiceMockitoTest.class is started -----|");

        MockitoAnnotations.openMocks(mockUserService);
        MockitoAnnotations.openMocks(mockUser);
    }

    @After
    public void after() {
        LOG.info("| ----- Test in UserServiceMockitoTest.class is finished -----|");
    }

    @Test
    public void save() throws Exception {
        LOG.info("| ----- test method save() -----|");

        mockUserService.save(mockUser);
        verify(mockUserService, atLeastOnce()).save(mockUser);
        verify(mockUserService, times(1)).save(mockUser);
        verify(mockUserService, atLeast(1)).save(mockUser);
        verify(mockUserService, atMost(2)).save(mockUser);
    }

    @Test
    public void delete() {
        LOG.info("| ----- test method delete() -----|");

        mockUserService.delete(BaseEntity.START_SEQ);
        verify(mockUserService, atLeastOnce()).delete(BaseEntity.START_SEQ);
    }

    @Test
    public void get() {
        LOG.info("| ----- test method get() -----|");

        mockUserService.get(BaseEntity.START_SEQ);
        verify(mockUserService, atLeastOnce()).get(BaseEntity.START_SEQ);
        verify(mockUserService, times(1)).get(BaseEntity.START_SEQ);
    }

    @Test
    public void getByEmail() {
        LOG.info("| ----- test method getByEmail() -----|");

        mockUserService.getByEmail(mockUser.getEmail());
        verify(mockUserService, times(1)).getByEmail(mockUser.getEmail());
    }

    @Test
    public void getAll() {
        LOG.info("| ----- test method getAll() -----|");

        mockUserService.getAll();
        verify(mockUserService, times(1)).getAll();
    }

    public void update() {
        LOG.info("| ----- test method update() -----|");

        mockUserService.update(mockUser);
        verify(mockUserService, atLeast(10)).update(mockUser);
    }
}
