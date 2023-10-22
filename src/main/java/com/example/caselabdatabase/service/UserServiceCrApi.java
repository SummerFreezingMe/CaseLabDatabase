package com.example.caselabdatabase.service;

import com.example.caselabdatabase.dao.UserCriteriaApi;
import com.example.caselabdatabase.entity.User;
import com.example.caselabdatabase.entity.UserCriteria;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceCrApi {
    private final UserCriteriaApi userCriteriaApi;


    public UserServiceCrApi(UserCriteriaApi userCriteriaApi ) {
        this.userCriteriaApi = userCriteriaApi;
    }

    public List<User> findAll(UserCriteria criteria) {
        return userCriteriaApi.findAll(criteria);
    }

    public User findUserById(Long id) {
        return userCriteriaApi.findById(id);
    }

    public void save(User user) {
        userCriteriaApi.create(user);
    }

    public void update(Long id, User user) {
        userCriteriaApi.update(id, user);
    }

    public void delete(Long id) {
        userCriteriaApi.delete(id);
    }
}