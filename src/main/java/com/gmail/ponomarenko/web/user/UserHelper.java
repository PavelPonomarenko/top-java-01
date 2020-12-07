package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import com.gmail.ponomarenko.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHelper {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserHelper.class);

    @Autowired
    private UserService service;

    //    public List<User> getAll() {
//        LOG.info("getAll");
//        return service.getAll();
//    }
//
//    public User get(int id) {
//        LOG.info("get " + id);
//        return service.get(id);
//    }
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> all = service.getAll();
        all.forEach(u -> u.setPassword(null));
        return all;
    }

    public User get(int id) {
        LOG.info("get " + id);
        User user = service.get(id);
        user.setPassword(null);
        return user;
    }

    public User create(User user) {
        LOG.info("create " + user);
        return service.save(PasswordUtil.getEncoded(user));
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        LOG.info("update " + user);
        user.update(id);
        service.update(PasswordUtil.getEncoded(user));
    }

    public User getByMail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enable) {
        LOG.info("enable " + id);
        service.enable(id, enable);
    }
}

