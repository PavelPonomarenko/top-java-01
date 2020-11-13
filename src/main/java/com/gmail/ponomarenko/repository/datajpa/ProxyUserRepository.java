package com.gmail.ponomarenko.repository.datajpa;

import com.gmail.ponomarenko.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(name = User.DELETE)
    int delete(@Param("id") int id);

//    @Query("DELETE FROM User u WHERE u.id=?1")
//    int delete(int id);

//    @Query("DELETE FROM User u WHERE u.id=:id")
//    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByEmail(String email);
}
