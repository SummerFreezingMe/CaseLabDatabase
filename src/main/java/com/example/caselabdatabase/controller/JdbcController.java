package com.example.caselabdatabase.controller;

import com.example.caselabdatabase.entity.User;
import com.example.caselabdatabase.service.JdbcServiceImpl;
import com.example.caselabdatabase.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jdbc/users")
public class JdbcController {
    private final UserService usi;

    public JdbcController(JdbcServiceImpl usi) {
        this.usi = usi;
    }

    @PutMapping(value = "/update/{id}")
    public User updateUser(@RequestBody User user) {
        return usi.updateUser(user);
    }

    @GetMapping(value = "/get/{id}")
    public User getUser(@PathVariable Long id) {
        return usi.findOne(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping(value = "/add/{id}")
    public User addUser(@RequestBody User user) {
        return usi.save(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable String id,
                                   @Param("preserve") boolean preserve) {
        usi.deleteUser(id, preserve);
    }

    @GetMapping(value = "/get_all")
    public List<User> getAll() {
        return usi.findAll();
}}

