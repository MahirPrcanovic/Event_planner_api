package com.example.dogadjaji213.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResDto {
    private String name;
    private LocalDate date;
    private String description;
    private String pictureUrl;
}
