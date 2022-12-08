package com.example.dogadjaji213.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginReqDto {
    private String username;
    private String password;
}
