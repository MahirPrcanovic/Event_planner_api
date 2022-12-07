package com.example.dogadjaji213.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Optional;

@Data
@AllArgsConstructor
public class UpdateCategoryReqDto {
    private Optional<String> name;
    private Optional<String> iconUrl;
}
