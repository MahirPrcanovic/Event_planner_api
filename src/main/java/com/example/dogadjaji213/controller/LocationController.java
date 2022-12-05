package com.example.dogadjaji213.controller;


import com.example.dogadjaji213.dto.LocationReqDto;
import com.example.dogadjaji213.dto.UpdateLocationReqDto;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService _locationService;

    @GetMapping
    public ResponseEntity<List<Location>> Get(){
        return ResponseEntity.ok().body(this._locationService.getAll());
    }
    @PostMapping
    public ResponseEntity<Location> Post(@RequestBody LocationReqDto location){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/location").toUriString());
        return ResponseEntity.created(uri).body(this._locationService.createNewLocation(location));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Location> Patch(@PathVariable UUID id, @RequestBody UpdateLocationReqDto locationReqDto){
        return ResponseEntity.ok().body(this._locationService.updateLocation(id,locationReqDto));
    }
}
