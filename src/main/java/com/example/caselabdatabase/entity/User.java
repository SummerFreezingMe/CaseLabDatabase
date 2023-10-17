package com.example.caselabdatabase.entity;


import jakarta.persistence.*;
import lombok.*;

/**
 * A user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;


    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;


    @Column(name = "email", length = 254, unique = true)
    private String email;


    @Column(name = "age")
    private Integer age;



    @Column(name = "enabled")
    private boolean enabled;
}
