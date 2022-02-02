package adeo.leroymerlin.cdp;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Autowired
    private static EventService eventService;

    @Mock
    private static EventRepository eventRepositoryMock;
                                

    @BeforeEach
    public void initService() {
        eventService = new EventService(eventRepositoryMock);
    }

    @Test
    public void shouldUpdateEventComment() {
        // given
        Event event = new Event();
        event.setId(1L);
        event.setComment("Comment before update");

        Event eventToUpdate = new Event();
        eventToUpdate.setId(1L);
        eventToUpdate.setNbStars(5);
        eventToUpdate.setComment("Comment after update");

        Mockito.when(eventRepositoryMock.findById(1L)).thenReturn(Optional.of(event));

        //when
        eventService.updateEvent(1L, eventToUpdate);

        //then
        Assertions.assertEquals(eventToUpdate.getComment(), event.getComment());
    }

    @Test
    public void shouldUpdateEventNbStars() {
        // given
        Event event = new Event();
        event.setId(1L);
        event.setNbStars(1);

        Event eventToUpdate = new Event();
        eventToUpdate.setId(1L);
        eventToUpdate.setNbStars(5);

        Mockito.when(eventRepositoryMock.findById(1L)).thenReturn(Optional.of(event));

        //when
        eventService.updateEvent(1L, eventToUpdate);

        //then
        Assertions.assertEquals(eventToUpdate.getNbStars(), event.getNbStars());
    }
}