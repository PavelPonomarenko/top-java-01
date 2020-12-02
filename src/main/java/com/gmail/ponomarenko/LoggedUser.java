package com.gmail.ponomarenko;

import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class LoggedUser implements UserDetails {

    protected User user;

    public LoggedUser(User user) {
        this.user = user;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return BaseEntity.START_SEQ;
//        return get().user.getId();
    }

    @Override
    public Set<Role> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
//
////    protected int id = BaseEntity.START_SEQ;
//
//    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
//
//    protected boolean enabled = true;
//    public LoggedUser(User user) {
//        this.user = user;
//    }
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
//    public static Object safeGet() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return null;
//        }
//        Object user = auth.getPrincipal();
//        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
//    }
//
//
//
//
//    public Set<Role> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }

