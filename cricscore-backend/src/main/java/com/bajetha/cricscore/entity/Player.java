package com.bajetha.cricscore.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Column(unique = true)
    private String email;

    private boolean isRegistered = false;

    private String passwordHash;

    @CreationTimestamp
    private LocalDateTime createdAt;
}