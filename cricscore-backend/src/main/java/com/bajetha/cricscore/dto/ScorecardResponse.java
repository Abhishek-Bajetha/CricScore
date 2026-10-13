package com.bajetha.cricscore.dto;

import lombok.Data;
import java.util.List;

@Data
public class ScorecardResponse {
    private Long matchId;
    private String teamAName;
    private String teamBName;
    private String status;
    private int totalRuns;
    private int totalWickets;
    private String totalOvers; // e.g. "14.2 / 20"
    private double currentRunRate;
    private List<BattingStatDto> batters;
    private List<BowlingStatDto> bowlers;
}