package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class BattingStatDto {
    private Long playerId;
    private String playerName;
    private int runs;
    private int ballsFaced;
    private int fours;
    private int sixes;
    private double strikeRate;
}