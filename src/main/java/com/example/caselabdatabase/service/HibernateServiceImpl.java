package com.example.caselabdatabase.service;


import com.example.caselabdatabase.dao.HibernateRepository;
import com.example.caselabdatabase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class HibernateServiceImpl implements UserService {
    private final HibernateRepository userRepository;

    public HibernateServiceImpl(HibernateRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void deleteUser(String login, boolean preserve) {
        userRepository.delete(userRepository
                .findByUsername(login));
    }


    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }


    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User userDTO) {
        AtomicReference<User> updated = new AtomicReference<>();
        User user = userRepository.findByUsername(userDTO.getUsername());

                    if (userDTO.getUsername() != null) {
                        user.setUsername(userDTO.getUsername());
                    }
                    if (userDTO.getAge() != null) {
                        user.setAge(userDTO.getAge());
                    }
                    if (userDTO.getEmail() != null) {
                        user.setEmail(userDTO.getEmail());
                    }
                    if (userDTO.getPassword() != null) {
                        user.setPassword(userDTO.getPassword());
                    }

        return updated.get();
    }

    @Override
    public Page<User> findAllPaged(Integer pageLength) {
        return userRepository.findAll(Pageable.ofSize(pageLength));
    }

    @Override
    public List<User> findAllSorted() {
        return userRepository.findAll(Sort.by("username").ascending());
    }

}

