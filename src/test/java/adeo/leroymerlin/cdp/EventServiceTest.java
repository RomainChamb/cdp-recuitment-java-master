package adeo.leroymerlin.cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Test
    public void shouldReturnEvent() {
        //given
        Member member1 = new Member();
        member1.setName("Jean");

        Member member2 = new Member();
        member2.setName("Paul");

        Member member3 = new Member();
        member3.setName("Odile");

        Set<Member> memberSet1 = new HashSet<>();
        memberSet1.add(member1);

        Set<Member> memberSet2 = new HashSet<>();
        memberSet2.add(member2);

        Set<Member> memberSet3 = new HashSet<>();
        memberSet3.add(member3);

        Band band1 = new Band();
        band1.setName("Band 1");
        band1.setMembers(memberSet1);

        Band band2 = new Band();
        band2.setName("Band 2");
        band2.setMembers(memberSet2);

        Band band3 = new Band();
        band3.setName("Band 1");
        band3.setMembers(memberSet3);

        Set<Band> bandSet1 = new HashSet<>();
        bandSet1.add(band1);

        Set<Band> bandSet2 = new HashSet<>();
        bandSet2.add(band2);

        Set<Band> bandSet3 = new HashSet<>();
        bandSet3.add(band3);

        Event event1 = new Event();
        event1.setId(1L);
        event1.setTitle("Event1");
        event1.setBands(bandSet1);

        Event event2 = new Event();
        event2.setId(2L);
        event2.setTitle("Event2");
        event2.setBands(bandSet2);

        Event event3 = new Event();
        event3.setId(3L);
        event3.setTitle("Event3");
        event3.setBands(bandSet3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);

        Mockito.when(eventRepositoryMock.findAllBy()).thenReturn(events);

        // when
        List<Event> filteredEvents = eventService.getFilteredEvents("Je");

        // then
        Assertions.assertEquals(1, filteredEvents.size());

    }

    @Test
    public void shouldNotReturnEvent() {
        //given
        Member member1 = new Member();
        member1.setName("Jean");

        Member member2 = new Member();
        member2.setName("Paul");

        Member member3 = new Member();
        member3.setName("Odile");

        Set<Member> memberSet1 = new HashSet<>();
        memberSet1.add(member1);

        Set<Member> memberSet2 = new HashSet<>();
        memberSet2.add(member2);

        Set<Member> memberSet3 = new HashSet<>();
        memberSet3.add(member3);

        Band band1 = new Band();
        band1.setName("Band 1");
        band1.setMembers(memberSet1);

        Band band2 = new Band();
        band2.setName("Band 2");
        band2.setMembers(memberSet2);

        Band band3 = new Band();
        band3.setName("Band 1");
        band3.setMembers(memberSet3);

        Set<Band> bandSet1 = new HashSet<>();
        bandSet1.add(band1);

        Set<Band> bandSet2 = new HashSet<>();
        bandSet2.add(band2);

        Set<Band> bandSet3 = new HashSet<>();
        bandSet3.add(band3);

        Event event1 = new Event();
        event1.setId(1L);
        event1.setTitle("Event1");
        event1.setBands(bandSet1);

        Event event2 = new Event();
        event2.setId(2L);
        event2.setTitle("Event2");
        event2.setBands(bandSet2);

        Event event3 = new Event();
        event3.setId(3L);
        event3.setTitle("Event3");
        event3.setBands(bandSet3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);

        Mockito.when(eventRepositoryMock.findAllBy()).thenReturn(events);

        // when
        List<Event> filteredEvents = eventService.getFilteredEvents("Gael");

        // then
        Assertions.assertEquals(0, filteredEvents.size());

    }
}