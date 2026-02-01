package com.bajetha.cricscore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "team_players")
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    enum role {CAPTAIN,MEMBER};
}
