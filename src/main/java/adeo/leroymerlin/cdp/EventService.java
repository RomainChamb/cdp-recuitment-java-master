package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final Logger LOGGER = Logger.getLogger(EventService.class.getName());

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();
        // Filter the events list in pure JAVA here

        return events;
    }

    public void updateEvent(Long id, Event event) {
        Optional<Event> eventToUpdate = eventRepository.findById(id);
        eventToUpdate.ifPresentOrElse(theEvent -> {
            theEvent.setNbStars(event.getNbStars());
            theEvent.setComment(event.getComment());
            eventRepository.save(theEvent);}, () -> LOGGER.warning("Unkonwn event"));
    }
}
