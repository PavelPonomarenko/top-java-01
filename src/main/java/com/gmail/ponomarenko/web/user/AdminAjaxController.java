package com.gmail.ponomarenko.web.user;

import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return helper.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        helper.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("item_id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password) {
        User user = new User(null, name, email, password, true, Role.ROLE_USER);

//        if (id == 0) {
//            User user = new User(null, name, email, password, true, Role.ROLE_USER);
//            helper.create(user);
//        }
        if (id == 0) {
            helper.create(user);
        } else {
            helper.update(user, id);
        }

    }
}
