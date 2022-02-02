package adeo.leroymerlin.cdp;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional()
public interface EventRepository extends Repository<Event, Long> {

    void deleteById(Long eventId);

    List<Event> findAllBy();

    Event save(Event event);

    Optional<Event> findById(Long id);
}
