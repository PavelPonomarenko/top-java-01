package com.gmail.ponomarenko.repository.jpa;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.repository.UserRepository;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import org.springframework.stereotype.Repository;

import java.beans.PersistenceDelegate;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }
}
