package com.example.dogadjaji213.service.location;

import com.example.dogadjaji213.dto.LocationReqDto;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService implements ILocation{
    private final LocationRepository _locationRepo;

    @Override
    public List<Location> getAll() {
        return this._locationRepo.findAll();
    }

    @Override
    public Location createNewLocation(LocationReqDto location) {
        Location dbLocation = new Location(location.getName(),location.getDescription(),location.getPictureUrl());
        Location loc = this._locationRepo.save(dbLocation);
        return loc;
    }
}
