package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class BowlingStatDto {
    private Long playerId;
    private String playerName;
    private double oversBowled; // e.g., 3.4 overs
    private int runsConceded;
    private int wickets;
    private double economy;
}

