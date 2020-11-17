package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.LoggedUser;
import com.gmail.ponomarenko.LoggerWrapper;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserRestController.class);

    @Autowired
    private UserService service;

    public UserRestController() {
    }

    public User get() {
        int id = LoggedUser.id();
        LOG.info("get", id);
        return service.get(id);
    }

    public void delete() {
        int id = LoggedUser.id();
        LOG.info("delete {}", id);
        service.delete(id);

    }

    public void update(User user) {
        int id = LoggedUser.id();
        LOG.info("update");
        service.update(user);

    }
}
