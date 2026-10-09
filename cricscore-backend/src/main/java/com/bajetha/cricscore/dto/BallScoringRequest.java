package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class BallScoringRequest {

    Long matchId;

    Long strikerId;

    Long nonStrikerId;

    Long bowlerId;

    int runsScored;

    int extras;

    String extraType;

    String wicketType;

    Long wicketPlayerId;

    int inningNumber;

    int overNumber;

    int ballNumber;

}
