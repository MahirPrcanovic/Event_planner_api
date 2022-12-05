package com.example.dogadjaji213.service.location;

import com.example.dogadjaji213.dto.LocationReqDto;
import com.example.dogadjaji213.dto.UpdateLocationReqDto;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Location updateLocation(UUID id, UpdateLocationReqDto locationReqDto) {
        Optional<Location> location = this._locationRepo.findById(id);
        if(location == null) throw new IllegalStateException("Location not existing.");
        if(location.isPresent()){
            if(locationReqDto.getName().isPresent()){
                location.get().setName(locationReqDto.getName().get());
            }
            if(locationReqDto.getDescription().isPresent()){
                location.get().setDescription(locationReqDto.getDescription().get());
            }
            if(locationReqDto.getPictureUrl().isPresent()){
                location.get().setPictureUrl(locationReqDto.getPictureUrl().get());
            }
            return this._locationRepo.save(location.get());
        }
        return null;
    }
}
