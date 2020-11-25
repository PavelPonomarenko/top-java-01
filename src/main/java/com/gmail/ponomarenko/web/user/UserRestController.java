package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.LoggedUser;
import com.gmail.ponomarenko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserHelper helper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return helper.get(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        helper.delete(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        helper.update(user);
    }
}

//@Controller
//public class UserRestController {
//
//    private static final LoggerWrapper LOG = LoggerWrapper.get(UserRestController.class);
//
//    @Autowired
//    private UserService service;
//
//    public User get() {
//        int id = LoggedUser.id();
//        LOG.info("get", id);
//        return service.get(id);
//    }
//
//    public void delete() {
//        int id = LoggedUser.id();
//        LOG.info("delete {}", id);
//        service.delete(id);
//
//    }
//
//    public void update(User user) {
//        int id = LoggedUser.id();
//        LOG.info("update");
//        service.update(user);
//
//    }
//}
