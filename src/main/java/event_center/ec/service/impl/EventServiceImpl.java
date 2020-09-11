package event_center.ec.service.impl;

import event_center.ec.model.binding.EventCreateDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.Guest;
import event_center.ec.model.entity.User;
import event_center.ec.model.service.EventServiceDTO;
import event_center.ec.model.service.EventServiceGuestsDTO;
import event_center.ec.model.service.EventServiceJoinedUsersDTO;
import event_center.ec.model.service.UserServiceJoinedDTO;
import event_center.ec.repository.EventRepository;
import event_center.ec.service.EventService;
import event_center.ec.service.GuestService;
import event_center.ec.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final GuestService guestService;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserService userService, GuestService guestService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.guestService = guestService;
    }

    @Override
    public Event getEvent(String id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<EventServiceDTO> getAllPublicEvents(boolean isPublic) {
        return this.eventRepository.findAllByPublic(isPublic).stream()
                .map(this::mapEventToServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventServiceDTO getEventById(String id) {
        Event event = this.eventRepository.findById(id).orElse(null);
        return mapEventToServiceDTO(event);
    }

    @Override
    public EventServiceDTO createEvent(EventCreateDTO eventCreateDTO) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(username);
        Event event = this.modelMapper.map(eventCreateDTO, Event.class);
        event.setCreator(user);
        event = this.eventRepository.saveAndFlush(event);
        return mapEventToServiceDTO(event);
    }

    @Override
    public List<EventServiceDTO> getEventsByOwnerId() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(username);
        List<Event> events = this.eventRepository.findAllByCreator(user);
        return events.stream().map(this::mapEventToServiceDTO).collect(Collectors.toList());
    }

    @Override
    public EventServiceDTO updateEvent(String id, EventCreateDTO eventCreateDTO) {
        Event event = this.eventRepository.findById(id).orElse(null);
        if (eventCreateDTO.getName() != null) {
            event.setName(eventCreateDTO.getName());
        }
        if (eventCreateDTO.getLocationName() != null) {
            event.setLocationName(eventCreateDTO.getLocationName());
        }
        if (eventCreateDTO.getDescription() != null) {
            event.setDescription(eventCreateDTO.getDescription());
        }
        if (eventCreateDTO.getAddress() != null) {
            event.setAddress(eventCreateDTO.getAddress());
        }
        if (eventCreateDTO.getCategory() != null) {
            event.setCategory(eventCreateDTO.getCategory());
        }
        if (eventCreateDTO.getDateTime() != null) {
            event.setDateTime(eventCreateDTO.getDateTime());
        }
        if (eventCreateDTO.getImageUrl() != null) {
            event.setImageUrl(eventCreateDTO.getImageUrl());
        }
        event.setMaxGuests(eventCreateDTO.getMaxGuests());
        event = this.eventRepository.saveAndFlush(event);
        return mapEventToServiceDTO(event);
    }

    @Override
    public void deleteEvent(String id) {
        Event event = this.eventRepository.findById(id).orElse(null);
        this.eventRepository.delete(event);
    }

    @Override
    public EventServiceGuestsDTO getEventWithGuests(String id) {
        Event event = this.eventRepository.findById(id).orElse(null);
        if (event.getGuests() == null) {
            event.setGuests(new HashSet<>());
        }
        return this.modelMapper.map(event, EventServiceGuestsDTO.class);
    }

    @Override
    public EventServiceJoinedUsersDTO getEventWithJoinedUsers(String id) {
        Event event = this.eventRepository.findById(id).orElse(null);
        if (event.getUsers() == null) {
            event.setUsers(new HashSet<>());
        }
        EventServiceJoinedUsersDTO eventServiceJoinedUsersDTO = this.modelMapper.map(event, EventServiceJoinedUsersDTO.class);
        eventServiceJoinedUsersDTO.setUsers(new ArrayList<>());
        event.getUsers().forEach(u -> {
            UserServiceJoinedDTO userServiceJoinedDTO = this.modelMapper.map(u, UserServiceJoinedDTO.class);
            userServiceJoinedDTO.setEmail(u.getUsername());
            eventServiceJoinedUsersDTO.getUsers().add(userServiceJoinedDTO);
        });
        return eventServiceJoinedUsersDTO;
    }

    @Override
    public EventServiceDTO updateEventSetGuest(String eventId, String guestId) {
        Event event = this.eventRepository.findById(eventId).orElse(null);
        Guest guest = this.guestService.getGuestById(guestId);
        guest = this.guestService.setEventToGuest(guest, event);
        event.getGuests().add(guest);
        event = this.eventRepository.saveAndFlush(event);
        return this.modelMapper.map(event, EventServiceDTO.class);
    }

    @Override
    public EventServiceDTO updateEventJoinUser(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElse(null);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(username);
        event.getUsers().add(user);
        event = this.eventRepository.saveAndFlush(event);
        return this.modelMapper.map(event, EventServiceDTO.class);
    }

    @Override
    public void updateEventRemoveGuest(String guestId) {
        Guest guest = this.guestService.getGuestById(guestId);
        Event event = guest.getEvent();
        event.getGuests().remove(guest);
        this.eventRepository.saveAndFlush(event);
        this.guestService.deleteGuest(guest);
    }

    private EventServiceDTO mapEventToServiceDTO(Event event) {
        EventServiceDTO eventServiceDTO = this.modelMapper.map(event, EventServiceDTO.class);
        eventServiceDTO.setOwnerId(event.getCreator().getId());
        List<String> usersId = new ArrayList<>();
        if (event.getUsers() != null && !event.getUsers().isEmpty()) {
            usersId = event.getUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
        }
        eventServiceDTO.setUsersId(usersId);
        return eventServiceDTO;
    }
}
