package event_center.ec.service;

import event_center.ec.model.binding.EventCreateDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.Guest;
import event_center.ec.model.service.EventServiceDTO;
import event_center.ec.model.service.EventServiceGuestsDTO;
import event_center.ec.model.service.EventServiceJoinedUsersDTO;

import java.util.List;

public interface EventService {
    List<EventServiceDTO> getAllPublicEvents(boolean isPublic);

    EventServiceDTO getEventById(String id);

    Event getEvent(String id);

    EventServiceDTO createEvent(EventCreateDTO eventCreateDTO);

    List<EventServiceDTO> getEventsByOwnerId();

    EventServiceDTO updateEvent(String id, EventCreateDTO eventCreateDTO);

    void deleteEvent(String id);

    EventServiceGuestsDTO getEventWithGuests(String id);

    EventServiceDTO updateEventSetGuest(String eventId, String guestId);
    void updateEventRemoveGuest(String guestId);

    EventServiceDTO updateEventJoinUser(String eventId);

    EventServiceJoinedUsersDTO getEventWithJoinedUsers(String id);
}
