package com.example.caselabdatabase.dao;

import com.example.caselabdatabase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
