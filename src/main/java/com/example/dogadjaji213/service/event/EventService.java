package com.example.dogadjaji213.service.event;
import com.example.dogadjaji213.dto.event.EventReqDto;
import com.example.dogadjaji213.dto.event.EventResDto;
import com.example.dogadjaji213.dto.event.UpdateEventReqDto;
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
import java.util.*;

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
    public Event createNewEvent(EventReqDto event) throws Exception {
        Location location = this._locationRepository.findById(event.getLocationID()).get();
        Category category= this._categoryRepository.findById(event.getCategoryID()).get();
        if(location==null || category==null) throw new Exception("Category or location not found.");
        Event saveEvent = new Event(null,event.getName(),event.getDate(),event.getDescription(),event.getPicUrl(),location,category,null);
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
    public Event getSingleEvent(UUID id){
        return this._eventRepository.findById(id).get();
    }
    @Override
    public List<Event> search(String search, UUID location, UUID category) {
        System.out.println(search);
        System.out.println(location);
        System.out.println(category);

        var events = new HashSet<Event>();
        boolean equals = location.toString().trim().equals("00000000-0000-0000-0000-000000000000");
        boolean equals1 = category.toString().trim().equals("00000000-0000-0000-0000-000000000000");
        if(!search.trim().equals("")){
            System.out.println("Usao u search");
            events.addAll(this._eventRepository.findByDateGreaterThanEqualAndNameContains(LocalDate.now(),search));
        }
        if(!equals){
            System.out.println("Usao u location");
            events.addAll(this._eventRepository.findByDateGreaterThanEqualAndLocation_Id(LocalDate.now(),location));
        }
        if(!equals1){
            System.out.println("Usao u category");
            events.addAll(this._eventRepository.findByDateGreaterThanEqualAndCategory_Id(LocalDate.now(),category));
        }
        if(equals1 && equals){
            System.out.println("Usao u equals i equals2 ");
            events.clear();
            events.addAll(this._eventRepository.findByDateGreaterThanEqualAndNameContains(LocalDate.now(),search));
        }
       /* if(search.trim() == "" && equals && equals1){
            events.addAll(this._eventRepository.findByDateGreaterThanEqualAndNameContains(LocalDate.now(),search));
        }*/
        return events.stream().toList();
/*
        return this._eventRepository.findByDateGreaterThanEqualAndNameContainsOrLocation_IdOrCategory_Id(LocalDate.now(),search,location,category);
*/
    }
}
