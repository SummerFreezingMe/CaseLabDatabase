package com.example.caselabdatabase.controller;

import com.example.caselabdatabase.entity.User;
import com.example.caselabdatabase.entity.UserCriteria;
import com.example.caselabdatabase.service.UserServiceCrApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/criteria/users")
public class CriteriaController {
    
    private final UserServiceCrApi userService;

    public CriteriaController(UserServiceCrApi userService) {
        this.userService = userService;
    }


    @PostMapping("/get")
    public List<User> getUsers(@RequestBody UserCriteria criteria) {
        return userService.findAll(criteria);
    }

    @GetMapping(value = "/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User userDTO) {
        try {
            userService.save(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{id}")
    public void updateUser(@RequestBody User userDTO,
                                               @PathVariable Long id) {

           userService.update(id, userDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}