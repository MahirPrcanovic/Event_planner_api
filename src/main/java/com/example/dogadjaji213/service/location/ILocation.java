package com.example.dogadjaji213.service.location;

import com.example.dogadjaji213.dto.LocationReqDto;
import com.example.dogadjaji213.dto.UpdateLocationReqDto;
import com.example.dogadjaji213.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface ILocation {
    List<Location> getAll();
    Location createNewLocation(LocationReqDto location);
    Location updateLocation(UUID id, UpdateLocationReqDto locationReqDto);
}
