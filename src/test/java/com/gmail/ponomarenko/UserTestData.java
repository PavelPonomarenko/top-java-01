package com.gmail.ponomarenko;

import com.gmail.ponomarenko.matcher.ModelMatcher;
import com.gmail.ponomarenko.model.BaseEntity;
import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Function;

public class UserTestData {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserTestData.class);

    public static final TestUser USER = new TestUser(BaseEntity.START_SEQ, "User", "user@gamil.com", "password");
    public static final TestUser ADMIN = new TestUser(BaseEntity.START_SEQ + 1, "Admin", "admin@gamil.com", "admin");

    public static class TestUser extends User {

        public TestUser(Integer startSeq, String user, String email, String password) {
            super(startSeq, user,email,password, Role.ROLE_USER);
        }
        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles());
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Date registered, Set<Role> roles) {

        }

//        public TestUser(String name, String email, String password, Role role, Role... roles) {
//            this(null, name, email, password, true, EnumSet.of(role, roles));
//        }


//        public TestUser(Integer id, String name, String email, String password, boolean enabled, Role role, Role... roles1) {
//            this(id, name, email, password, enabled, EnumSet.of(role, roles1));
//        }

//        public TestUser(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
//            super(id, name, email, password, enabled, roles);
//        }


        public User asUser() {
            return new User(this);
        }

        @Override
        public String toString() {
            return "TestUser{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", enabled=" + enabled +
                    ", registered=" + registered +
                    ", roles=" + roles +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    public static final ModelMatcher<User, TestUser> MATCHER =
            new ModelMatcher<>(user -> (user instanceof TestUser) ? (TestUser) user : new TestUser(user));


}
