package com.bajetha.cricscore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ballLogs")
public class BallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ballId;

    private int inningNumber;

    private int overNumber;

    private int ballNumber;

    private int runsScored;

    private int extras;

    @Column(nullable = true)
    private String wicketType;


    @JoinColumn(name = "match_id")
    @ManyToOne
    private Match match;

    @ManyToOne
    @JoinColumn(name = "striker_id")
    private Player striker;

    @ManyToOne
    @JoinColumn(name = "non_striker_id")
    private Player nonStriker;

    @ManyToOne
    @JoinColumn(name = "bowler_id")
    private Player bowler;
}
