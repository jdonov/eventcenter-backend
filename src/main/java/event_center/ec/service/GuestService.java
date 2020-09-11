package event_center.ec.service;

import event_center.ec.model.binding.GuestCreateBindingDTO;
import event_center.ec.model.binding.GuestUpdateBindingDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.Guest;
import event_center.ec.model.service.GuestServiceDTO;

public interface GuestService {
    GuestServiceDTO createGuest(GuestCreateBindingDTO guestCreateBindingDTO);

    Guest getGuestById(String id);

    Guest setEventToGuest(Guest guest, Event event);

    void deleteGuest(Guest guest);

    GuestServiceDTO updateGuest(String id, GuestUpdateBindingDTO guestUpdateBindingDTO);
}
