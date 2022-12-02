package com.example.dogadjaji213.service.event;
import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.model.Location;
import com.example.dogadjaji213.repository.CategoryRepository;
import com.example.dogadjaji213.repository.EventRepository;
import com.example.dogadjaji213.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Event updateEvent() {
        return null;
    }

    @Override
    public List<Event> search(String search,String location,String category) {
        return this._eventRepository.findByName(search);
    }
}
