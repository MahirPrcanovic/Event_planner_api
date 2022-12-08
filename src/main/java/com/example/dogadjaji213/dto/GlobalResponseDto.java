package com.example.dogadjaji213.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponseDto {
    private boolean success=true;

    private Optional<String> message;

    private Optional<List<?>> data;
    private Optional<?> item;

    private Optional<Integer> page;
    private Optional<Integer> count;
}
