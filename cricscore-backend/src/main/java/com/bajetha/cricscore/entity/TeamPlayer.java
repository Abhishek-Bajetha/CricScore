package com.bajetha.cricscore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "team_players")
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private String role;
}
