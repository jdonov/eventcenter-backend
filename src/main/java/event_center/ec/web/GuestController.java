package event_center.ec.web;

import event_center.ec.model.binding.GuestCreateBindingDTO;
import event_center.ec.model.binding.GuestUpdateBindingDTO;
import event_center.ec.model.entity.Event;
import event_center.ec.model.service.GuestServiceDTO;
import event_center.ec.model.service.UserServiceDTO;
import event_center.ec.service.EventService;
import event_center.ec.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;
    private final EventService eventService;

    public GuestController(GuestService guestService, EventService eventService) {
        this.guestService = guestService;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<GuestServiceDTO> createGuest(@RequestBody GuestCreateBindingDTO guestCreateBindingDTO) {
        GuestServiceDTO guestServiceDTO = this.guestService.createGuest(guestCreateBindingDTO);
        URI location = MvcUriComponentsBuilder.fromMethodName(GuestController.class, "createGuest", GuestServiceDTO.class)
                .pathSegment("{id}").buildAndExpand(guestServiceDTO.getId()).toUri();
        return ResponseEntity.created(location).body(guestServiceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") String id) {
        this.eventService.updateEventRemoveGuest(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GuestServiceDTO> updateGuestStatus(@PathVariable("id") String id, @RequestBody GuestUpdateBindingDTO guestUpdateBindingDTO) {
        GuestServiceDTO guestServiceDTO = this.guestService.updateGuest(id, guestUpdateBindingDTO);
        return ResponseEntity.ok(guestServiceDTO);
    }
}
