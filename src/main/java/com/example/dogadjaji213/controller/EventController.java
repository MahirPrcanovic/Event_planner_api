package com.example.dogadjaji213.controller;
import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService _eventService;
    @GetMapping
    public ResponseEntity<List<Event>> Get(){
        return ResponseEntity.ok().body(this._eventService.getAll());
    }
    @PostMapping
    public ResponseEntity<Event> Post(@RequestBody EventReqDto event){
        return ResponseEntity.ok().body(this._eventService.createNewEvent(event));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> Put(){
        return ResponseEntity.ok().body(this._eventService.updateEvent());
    }
    @GetMapping
    @RequestMapping("/event")
    public ResponseEntity<List<Event>> GetSearch(@RequestParam("search") String search,@RequestParam("location") String location,@RequestParam("category") String category) throws InterruptedException {
        return ResponseEntity.ok().body(this._eventService.search(search,location,category));
    }
}
