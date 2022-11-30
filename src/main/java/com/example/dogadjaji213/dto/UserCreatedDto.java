package com.example.dogadjaji213.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserCreatedDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
