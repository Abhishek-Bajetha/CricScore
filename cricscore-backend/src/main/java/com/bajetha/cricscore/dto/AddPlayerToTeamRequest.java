package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class AddPlayerToTeamRequest {

    private Long teamId;          // Target team
    private String name;          // Player name
    private String mobileNumber;  // Optional
    private boolean isShadow;     // Shadow toggle
    private String role; // Default role
}