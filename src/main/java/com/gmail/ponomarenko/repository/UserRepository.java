package com.gmail.ponomarenko.repository;


import com.gmail.ponomarenko.model.User;

import java.util.List;


public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
