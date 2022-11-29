package com.example.dogadjaji213.service.location;

import com.example.dogadjaji213.dto.LocationDto;
import com.example.dogadjaji213.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILocation {
    List<Location> getAll();
    Location createNewLocation(LocationDto location);
}
