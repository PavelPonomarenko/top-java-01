package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.service.UserService;
import com.gmail.ponomarenko.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController {

    @Autowired
    private UserHelper helper;
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return helper.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        helper.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(UserTo userTo) {
        service.save(userTo);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return helper.get(id);
    }
    @RequestMapping(value = "/{id}/enable", method = RequestMethod.POST)
    public void updateValue(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        helper.enable(id, enabled);
    }
}
