package com.example.dogadjaji213.controller;


import com.example.dogadjaji213.dto.GlobalResponseDto;
import com.example.dogadjaji213.dto.location.LocationReqDto;
import com.example.dogadjaji213.dto.location.UpdateLocationReqDto;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.model.ResponseException;
import com.example.dogadjaji213.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LocationController {
    private final LocationService _locationService;

    @GetMapping
    public ResponseEntity<GlobalResponseDto> Get(){
        var response = new GlobalResponseDto();
        try {
            var locations = this._locationService.getAll();
            response.setData(Optional.ofNullable(locations));
            response.setMessage("Success.".describeConstable());
            response.setCount(Optional.of(locations.size()));
            response.setPage(Optional.of(1));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage("Items could not get retrieved.".describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping
    public ResponseEntity<GlobalResponseDto> Post(@RequestBody LocationReqDto location){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/location").toUriString());
        var response = new GlobalResponseDto();
        try{
            var loc = this._locationService.createNewLocation(location);
            if(loc == null) throw new Exception("Could not save location. Please check your sent information.");
            response.setItem(Optional.ofNullable(loc));
            response.setMessage("Successfully created location.".describeConstable());
            return ResponseEntity.created(uri).body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> Patch(@PathVariable UUID id, @RequestBody UpdateLocationReqDto locationReqDto){
        var response = new GlobalResponseDto();
        try{
            var loc = this._locationService.updateLocation(id,locationReqDto);
            if(loc == null) throw new Exception("Could not update location. Please check your sent information.");
            response.setItem(Optional.ofNullable(loc));
            response.setMessage("Successully updated location".describeConstable());
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.ok().body(response);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> GetSingle(@PathVariable UUID id){
        var response = new GlobalResponseDto();
        try{
            var location = this._locationService.getSingleLocation(id);
            if(location == null) throw new ResponseException("Item does not exist.");
            response.setMessage("Success".describeConstable());
            response.setItem(Optional.of(location));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            System.out.println(ex.getMessage());
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
