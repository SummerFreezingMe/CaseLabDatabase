package com.example.caselabdatabase.dao;

import com.example.caselabdatabase.entity.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

        public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE username=?",
                    BeanPropertyRowMapper.newInstance(User.class), username);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user (id, username, password, email, " +
                        "enabled, age) VALUES(?,?,?,?,?,?)",
                count()+1, user.getUsername(), user.getPassword(),
                user.getEmail(),user.isEnabled(),user.getAge());
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE from user");
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }


    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from user",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM user", Integer.class);

    }
}