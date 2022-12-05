package com.example.dogadjaji213.controller;
import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.dto.UpdateEventReqDto;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService _eventService;
    /*@GetMapping
    public ResponseEntity<List<Event>> Get(){
        return ResponseEntity.ok().body(this._eventService.getAll());
    }*/
    @PostMapping
    public ResponseEntity<Event> Post(@RequestBody EventReqDto event){
        return ResponseEntity.ok().body(this._eventService.createNewEvent(event));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Event> Patch(@PathVariable UUID id, @RequestBody UpdateEventReqDto eventReqDto){
        return ResponseEntity.ok().body(this._eventService.updateEvent(id,eventReqDto));
    }
    @GetMapping
    public ResponseEntity<List<Event>> GetSearch(@RequestParam(value = "search",required = false,defaultValue = "") String search, @RequestParam(value = "location",required = false,defaultValue = "") String location, @RequestParam(value = "category",required = false,defaultValue = "") String category){
        return ResponseEntity.ok().body(this._eventService.search(search.trim(), location.trim().isEmpty() ? UUID.fromString("00000000-0000-0000-0000-000000000000") : UUID.fromString(location), category.trim().isEmpty() ? UUID.fromString("00000000-0000-0000-0000-000000000000") : UUID.fromString(category)));
    }
}
