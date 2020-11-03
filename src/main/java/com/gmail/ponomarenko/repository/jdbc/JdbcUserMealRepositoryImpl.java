package com.gmail.ponomarenko.repository.jdbc;

import com.gmail.ponomarenko.model.UserMeal;
import com.gmail.ponomarenko.repository.UserMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public UserMeal save(UserMeal userMeal) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("user_id", userMeal.getUserId())
                .addValue("datetime", userMeal.getDateTime())
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories());

        if (userMeal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            userMeal.setId(newKey.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE meals SET user_id=:user_id, datetime=:datetime, description=:description, calories=:calories" +
                            " WHERE id=:id AND user_id=:user_id", map) == 0) ;
            return null;
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM MEALS WHERE id=? and user_id=?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> userMeal = jdbcTemplate.query("SELECT id, datetime, description, calories FROM MEALS WHERE id=?" +
                " AND user_id=?", ROW_MAPPER, id, userId);
        return CollectionUtils.isEmpty(userMeal) ? null : DataAccessUtils.requiredSingleResult(userMeal);

    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT id, user_id ,datetime, description, calories FROM meals WHERE user_id=? ORDER BY datetime desc", ROW_MAPPER, userId);
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM MEALS WHERE user_id=?", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Timestamp sD = Timestamp.valueOf(startDate);
        Timestamp eD = Timestamp.valueOf(endDate);
        return jdbcTemplate.query("SELECT id,user_id,datetime,description,calories FROM meals " +
                "WHERE datetime<? and datetime>? and user_id=? ORDER BY datetime desc", ROW_MAPPER, eD, sD, userId);
    }
}
