package com.example.caselabdatabase.service;

import com.example.caselabdatabase.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void deleteUser(String login, boolean preserve);

    List<User> findAll();

    Optional<User> findOne(Long id);

    User save(User user);

    User updateUser(User userDTO);

    Page<User> findAllPaged(Integer pageLength);

    List<User> findAllSorted();
}
