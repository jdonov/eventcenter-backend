package event_center.ec.web;

import com.fasterxml.jackson.annotation.JsonView;
import event_center.ec.model.binding.EventCreateDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.User;
import event_center.ec.model.entity.Views;
import event_center.ec.model.service.*;
import event_center.ec.service.EventService;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventServiceDTO>> getPublicEvents(@RequestParam("isPublic") Boolean isPublic) {
        List<EventServiceDTO> publicEvents = this.eventService.getAllPublicEvents(isPublic);
        return ResponseEntity.ok(publicEvents);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventServiceDTO> getEventById(@PathVariable("id") String id) {
        EventServiceDTO event = this.eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event")
    public ResponseEntity<EventServiceDTO> createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        EventServiceDTO eventServiceDTO = this.eventService.createEvent(eventCreateDTO);
        URI location = MvcUriComponentsBuilder.fromMethodName(EventController.class, "createEvent", EventServiceDTO.class)
                .pathSegment("{id}").buildAndExpand(eventServiceDTO.getId()).toUri();
        return ResponseEntity.created(location).body(eventServiceDTO);
    }

    @GetMapping("/ownerId")
    public ResponseEntity<List<EventServiceDTO>> getEventsByOwner() {
        List<EventServiceDTO> events = this.eventService.getEventsByOwnerId();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<EventServiceDTO> updateEvent(@PathVariable("id") String id, @RequestBody EventCreateDTO eventCreateDTO) {
        EventServiceDTO event = this.eventService.updateEvent(id, eventCreateDTO);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") String id) {
        this.eventService.deleteEvent(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/guests/event/{id}")
    public ResponseEntity<EventServiceGuestsDTO> getEventWithGuests(@PathVariable("id") String id) {
        EventServiceGuestsDTO eventServiceGuestsDTO = this.eventService.getEventWithGuests(id);
        return ResponseEntity.ok(eventServiceGuestsDTO);
    }

    @PutMapping("/event/{eventId}/guest/{guestId}")
    public ResponseEntity<EventServiceDTO> updateEvent(@PathVariable("eventId") String eventId, @PathVariable("guestId") String guestId) {
        EventServiceDTO event = this.eventService.updateEventSetGuest(eventId, guestId);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/event/{eventId}/join/")
    public ResponseEntity<EventServiceDTO> updateEventJoin(@PathVariable("eventId") String eventId) {
        EventServiceDTO event = this.eventService.updateEventJoinUser(eventId);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/{id}/joinedUsers")
    public ResponseEntity<EventServiceJoinedUsersDTO> getEventWithJoinedUsers(@PathVariable("id") String id) {
        EventServiceJoinedUsersDTO eventServiceJoinedUsersDTO = this.eventService.getEventWithJoinedUsers(id);
        return ResponseEntity.ok(eventServiceJoinedUsersDTO);
    }
}
