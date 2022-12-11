package com.example.dogadjaji213.controller;
import com.example.dogadjaji213.dto.GlobalResponseDto;
import com.example.dogadjaji213.dto.event.EventReqDto;
import com.example.dogadjaji213.dto.event.UpdateEventReqDto;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@CrossOrigin("*")
public class EventController {
    private final EventService _eventService;
    /*@GetMapping
    public ResponseEntity<List<Event>> Get(){
        return ResponseEntity.ok().body(this._eventService.getAll());
    }*/
    @PostMapping
    public ResponseEntity<GlobalResponseDto> Post(@RequestBody EventReqDto event) throws Exception {
        var response = new GlobalResponseDto();
        try{
            var loc = this._eventService.createNewEvent(event);
            if(loc == null) throw new Exception("Creation not successfull.");
            response.setItem(Optional.ofNullable(loc));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> Patch(@PathVariable UUID id, @RequestBody UpdateEventReqDto eventReqDto){
        var response = new GlobalResponseDto();
        try{
            var event = this._eventService.updateEvent(id,eventReqDto);
            if (event == null) throw new Exception("Event is not updated. Please check your sent information.");
            response.setMessage("Success".describeConstable());
            return ResponseEntity.ok().body(response);
        }catch (Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.ok().body(response);
        }

    }
    @GetMapping
    public ResponseEntity<GlobalResponseDto> GetSearch(@RequestParam(value = "search",required = false,defaultValue = "") String search, @RequestParam(value = "location",required = false,defaultValue = "") String location, @RequestParam(value = "category",required = false,defaultValue = "") String category){
        var response = new GlobalResponseDto();
        try{
            var events = this._eventService.search(search.trim(), location.trim().isEmpty() ? UUID.fromString("00000000-0000-0000-0000-000000000000") : UUID.fromString(location), category.trim().isEmpty() ? UUID.fromString("00000000-0000-0000-0000-000000000000") : UUID.fromString(category));
            response.setData(Optional.ofNullable(events));
            response.setMessage("Success".describeConstable());
            return ResponseEntity.ok().body(response);
        }catch (Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
