package com.example.dogadjaji213.service.event;

import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.dto.UpdateEventReqDto;
import com.example.dogadjaji213.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IEventService {
    List<Event> getAll();
    Event createNewEvent(EventReqDto event);
    Event updateEvent(UUID id, UpdateEventReqDto eventReqDto);
    List<Event> search(String search, UUID location, UUID category) throws InterruptedException;
}
