package com.gmail.ponomarenko.repository.mock;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Repository
public class MockUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserRepositoryImpl.class);


    @PostConstruct
    public void postConstructor() {
        LOG.info("PostConstructor");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("preDestroy");

    }

    @Override
    public User save(User user) {
        LOG.info("save" + user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete" + id);
        return id != 0;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("email" + email);
        return null;
    }

    @Override
    public List<User> getAll() {
        LOG.info("get All");
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }
}
