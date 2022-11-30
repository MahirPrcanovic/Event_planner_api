package com.example.dogadjaji213.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterReqDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
