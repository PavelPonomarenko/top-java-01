package com.gmail.ponomarenko;

import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.Role;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class LoggedUser {
    protected int id = BaseEntity.START_SEQ;

    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = true;

    private static LoggedUser LOGGED_USER = new LoggedUser();

    public static LoggedUser get() {
        return LOGGED_USER;
    }

    public static int id() {
        return get().id;
    }

    public Set<Role> getAuthorities() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

//    protected int id;
//    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
//    protected boolean enabled = true;
//
//    private static LoggedUser LOGGED_USER = new LoggedUser();
//
//    public static LoggedUser get() {
//        return LOGGED_USER;
//    }
//
//    public static int id() {
//        return get().id;
//    }
//
//    public Set<Role> getAuthorities() {
//        return roles;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
}
