package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.exception.NotFoundException;


import java.util.List;

public interface UserService {


    public User save(User user);

    public void delete(int id) ;

    public User get(int id) throws NotFoundException;

    public User getByEmail(String email)throws NotFoundException;

    public List<User> getAll();

    public void update(User user) throws NotFoundException;
    public void evictCache();




}