package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.repository.UserRepository;
import com.gmail.ponomarenko.util.exception.ExceptionUtil;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository repository;


    public UserServiceImpl() {
    }
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.check(repository.getByEmail(email), "email= " + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(user), user.getId());
    }
}
