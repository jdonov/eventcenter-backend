package event_center.ec.model.service;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceGuestsDTO extends BaseServiceModel{
    private LocalDateTime dateTime;
    private String name;
    private List<GuestServiceDTO> guests;

    public EventServiceGuestsDTO() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GuestServiceDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestServiceDTO> guests) {
        this.guests = guests;
    }
}
