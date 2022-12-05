package com.example.dogadjaji213.service.event;
import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.dto.UpdateEventReqDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.repository.CategoryRepository;
import com.example.dogadjaji213.repository.EventRepository;
import com.example.dogadjaji213.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService implements IEventService {
    private final LocationRepository _locationRepository;
    private final CategoryRepository _categoryRepository;
    private final EventRepository _eventRepository;


    @Override
    public List<Event> getAll() {
        return this._eventRepository.findAll();
    }
    @Override
    public Event createNewEvent(EventReqDto event) {
        Location location = this._locationRepository.findById(event.getLocationID()).get();
        Category category= this._categoryRepository.findById(event.getCategoryID()).get();
        Event saveEvent = new Event(null,event.getName(),event.getDate(),event.getDescription(),event.getPicUrl(),location,category);
        return this._eventRepository.save(saveEvent);
    }

    @Override
    public Event updateEvent(UUID id, UpdateEventReqDto eventReqDto) {
        Optional<Event> event = this._eventRepository.findById(id);
        if(event.isPresent()){
            if(eventReqDto.getName().isPresent()){
                event.get().setName(eventReqDto.getName().get());
            }
            if(eventReqDto.getPictureUrl().isPresent()){
                event.get().setPictureUrl(eventReqDto.getPictureUrl().get());
            }
            if(eventReqDto.getDescription().isPresent()){
                event.get().setDescription(eventReqDto.getDescription().get());
            }
            if(eventReqDto.getDate().isPresent()){
                event.get().setDate(eventReqDto.getDate().get());
            }
            if(eventReqDto.getLocationID().isPresent()){
                Optional<Location> location = this._locationRepository.findById(eventReqDto.getLocationID().get());
                if(location.isPresent()){
                    event.get().setLocation(location.get());
                }
            }
            if(eventReqDto.getCategoryID().isPresent()){
                Optional<Category> category=this._categoryRepository.findById(eventReqDto.getCategoryID().get());
                if(category.isPresent()){
                    event.get().setCategory(category.get());
                }
            }
            return this._eventRepository.save(event.get());
        }
        return null;
    }

    @Override
    public List<Event> search(String search, UUID location, UUID category) {
        return this._eventRepository.findByDateGreaterThanEqualAndNameContainsOrLocation_IdOrCategory_Id(LocalDate.now(),search,location,category);
    }
}
