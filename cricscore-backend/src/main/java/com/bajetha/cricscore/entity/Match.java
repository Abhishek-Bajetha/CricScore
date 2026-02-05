package com.bajetha.cricscore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    private int totalOvers;

    private String venue;

    private Date date;

    private String status;

    @JoinColumn(name = "teamA_id")
    @ManyToOne
    private Team teamA;

    @JoinColumn(name = "teamB_id")
    @ManyToOne
    private Team teamB;

    @JoinColumn(nullable = true)
    @ManyToOne
    private Team tossWinner;

    @JoinColumn(nullable = true)
    @ManyToOne
    private Team battingFirst;
}
