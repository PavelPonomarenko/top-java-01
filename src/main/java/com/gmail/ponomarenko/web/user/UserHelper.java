package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class UserHelper {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserHelper.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user) {
        LOG.info("update " + user);
        service.update(user);
    }

    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }


//
//    @Autowired
//    private UserService service;
//
//    public List<User> getAll(int id) {
//        LOG.info("getAll");
//        return service.getAll();
//    }
//
//    public void get(int id) {
//        LOG.info("get" + id);
//        service.get(id);
//    }
//
//    public User create(User user) {
//        LOG.info("create" + user);
//        return service.save(user);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete" + id);
//        service.delete(id);
//    }
//
//    public void update(User user) {
//        LOG.info("update" + user);
//        service.update(user);
//    }
//
//    public User getByEmail(String email) {
//        LOG.info("getByEmail" + email);
//        return service.getByEmail(email);
//    }
}
