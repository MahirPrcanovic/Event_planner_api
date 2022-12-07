package com.example.dogadjaji213.dto.event;

import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.model.Location;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateEventReqDto {
    private Optional<String> name;
    private Optional<LocalDate> date;
    private Optional<String> description;
    private Optional<String> pictureUrl;
    private Optional<UUID> locationID;
    private Optional<UUID> categoryID;
}
