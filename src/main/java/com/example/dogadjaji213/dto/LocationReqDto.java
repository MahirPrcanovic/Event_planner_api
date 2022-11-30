package com.example.dogadjaji213.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationReqDto {
    private String name;
    private String description;
    private String pictureUrl;
}
