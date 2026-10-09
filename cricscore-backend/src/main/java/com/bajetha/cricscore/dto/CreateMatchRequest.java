package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class CreateMatchRequest {
    Long teamAId;

    Long teamBId;

    int totalOvers;

    String venue;
}
