package com.example.dogadjaji213.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UpdateLocationReqDto {
    private Optional<String>  name;
    private Optional<String>description;
    private Optional<String> pictureUrl;
}
