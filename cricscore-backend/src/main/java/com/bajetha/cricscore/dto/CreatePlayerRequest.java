package com.bajetha.cricscore.dto;

import lombok.Data;

@Data
public class CreatePlayerRequest {

    String name;

    String mobileNumber;

    String email;

    boolean isRegistered;

}
