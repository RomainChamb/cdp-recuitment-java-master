package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adeo.leroymerlin.BandDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        events.removeIf(event -> event.getBands().stream()
        .allMatch(band -> band.getMembers().stream()
        .allMatch(member -> !member.getName().contains(query))));

        events.stream().distinct().forEach(event -> {
            event.setTitle(event.getTitle() + " [" + event.getBands().size() + "]");
            Set<Band> bandSet = event.getBands().stream().map(band -> {
                Band updateBand = new Band();
                updateBand.setMembers(band.getMembers());
                updateBand.setName(band.getName() + " [" + band.getMembers().size() + "]");
                return updateBand;
            }).collect(Collectors.toSet());
            event.setBands(bandSet);
        });

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
