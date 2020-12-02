package com.gmail.ponomarenko.repository;

import com.gmail.ponomarenko.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User getByEmail(String email);

    List<User> getAll();

    User get(int id);

//    void enable(int id, boolean enable);
    default void enable(int id, boolean enable) {
        throw new UnsupportedOperationException("Enable for this profile is not supported");
    }
}
