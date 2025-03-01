package com.chuwa.learn.account_service.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private Long userId;
    private String email;
    private String username;
    private String message;

    private String token;
}
