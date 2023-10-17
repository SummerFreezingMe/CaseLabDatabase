package com.example.caselabdatabase.service;


import com.example.caselabdatabase.dao.HibernateRepository;
import com.example.caselabdatabase.dao.JdbcRepository;
import com.example.caselabdatabase.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JdbcServiceImpl implements UserService {
    private final JdbcRepository userRepository;

    public JdbcServiceImpl(JdbcRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void deleteUser(String login, boolean preserve) {
        userRepository.deleteById(userRepository
                .findByUsername(login).getId());
    }


    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }


    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
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

}

