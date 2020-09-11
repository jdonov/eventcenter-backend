package event_center.ec.service.impl;

import event_center.ec.model.binding.GuestCreateBindingDTO;
import event_center.ec.model.binding.GuestUpdateBindingDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.Guest;
import event_center.ec.model.service.GuestServiceDTO;
import event_center.ec.repository.GuestRepository;
import event_center.ec.service.GuestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    public GuestServiceImpl(GuestRepository guestRepository, ModelMapper modelMapper) {
        this.guestRepository = guestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GuestServiceDTO createGuest(GuestCreateBindingDTO guestCreateBindingDTO) {
        Guest guest = this.modelMapper.map(guestCreateBindingDTO, Guest.class);
        guest = this.guestRepository.saveAndFlush(guest);
        GuestServiceDTO guestServiceDTO = this.modelMapper.map(guest, GuestServiceDTO.class);
        return guestServiceDTO;
    }

    @Override
    public Guest getGuestById(String id) {
        return this.guestRepository.findById(id).orElse(null);
    }

    @Override
    public Guest setEventToGuest(Guest guest, Event event) {
        guest.setEvent(event);
        return this.guestRepository.saveAndFlush(guest);
    }

    @Override
    public void deleteGuest(Guest guest) {
        this.guestRepository.delete(guest);
    }

    @Override
    public GuestServiceDTO updateGuest(String id, GuestUpdateBindingDTO guestUpdateBindingDTO) {
        Guest guest = this.guestRepository.findById(id).orElse(null);
        guest.setPending(guestUpdateBindingDTO.isPending());
        guest.setAttending(guestUpdateBindingDTO.isAttending());
        guest = this.guestRepository.saveAndFlush(guest);
        return this.modelMapper.map(guest, GuestServiceDTO.class);

    }
}
