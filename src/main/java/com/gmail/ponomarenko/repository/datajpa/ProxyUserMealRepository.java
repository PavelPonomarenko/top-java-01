package com.gmail.ponomarenko.repository.datajpa;

import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.model.UserMeal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

//
//    @Override
//    UserMeal save(UserMeal userMeal);
//

    //var 1 - use Query
//    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
//    List<UserMeal> getAll(@Param("userId") int userId);
    //var 2 - use JpaRepository method
//    List<UserMeal> findAllByUserOrderByDateTimeDesc(User user);
    //var 3 - use JpaRepository + sort
    List<UserMeal> findAllByUser(User user, Sort sort);



    //var 1 - use Query
    @Query("SELECT m FROM UserMeal m WHERE m.id=:id and m.user.id=:userId")
    UserMeal getByIdAndUser(@Param("id") int id, @Param("userId") int userId);
    //var2 - use JpaRepository method
//      UserMeal getByIdAndUser(int id, User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal i WHERE i.user.id=:userId")
    void deleteAll(@Param("userId") int userId);


    @Query("SELECT m from UserMeal m WHERE m.user.id=:userId AND m.dateTime>=:after and m.dateTime<:before ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);
}
