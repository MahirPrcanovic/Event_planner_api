package com.example.dogadjaji213.repository;

import com.example.dogadjaji213.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
   List<Event> findByName(String name);
}
