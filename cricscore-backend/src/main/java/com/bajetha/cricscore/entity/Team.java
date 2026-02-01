package com.bajetha.cricscore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long teamId;

    @Column(nullable = false)
    String teamName;

    String location;

    Long createdBy;

}
