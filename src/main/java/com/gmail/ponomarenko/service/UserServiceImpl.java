package com.gmail.ponomarenko.service;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.repository.UserRepository;
import com.gmail.ponomarenko.to.UserTo;
import com.gmail.ponomarenko.util.exception.ExceptionUtil;
import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UserRepository repository;

    public UserServiceImpl() {
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
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
    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(user), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    public void evictCache() {
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void save(UserTo userTo) {
        if (userTo.getId() == 0) {
            save(userTo.asNewUser());
        } else {
            User user = get(userTo.getId());
            userTo.updateUser(user);
            save(user);
        }
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void enable(int id, boolean enable) {
        repository.enable(id, enable);
    }
}
