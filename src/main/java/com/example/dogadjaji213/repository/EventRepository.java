package com.example.dogadjaji213.repository;
import com.example.dogadjaji213.model.Event;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
   List<Event> findByDateGreaterThanEqualAndNameContains(LocalDate date,String name);
   List<Event> findByDateGreaterThanEqualAndLocation_Id(LocalDate date,UUID location);
   List<Event> findByDateGreaterThanEqualAndCategory_Id(LocalDate date,UUID category);
   List<Event> findByDateGreaterThanEqualAndNameContainsOrLocation_IdOrCategory_Id(LocalDate date,String name, UUID location, UUID category);
}
