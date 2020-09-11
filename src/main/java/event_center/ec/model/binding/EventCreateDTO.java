package event_center.ec.model.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import event_center.ec.model.entity.Guest;
import event_center.ec.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public class EventCreateDTO {
    private String name;
    private String locationName;
    private String description;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String imageUrl;
    private int maxGuests;
    private boolean isPublic;

    public EventCreateDTO() {
    }

    public EventCreateDTO(String name, String locationName, String description, String address, String category, LocalDateTime dateTime, String imageUrl, int maxGuests, boolean isPublic) {
        this.name = name;
        this.locationName = locationName;
        this.description = description;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.maxGuests = maxGuests;
        this.isPublic = isPublic;
    }

    @NotEmpty(message = "Event name is required!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Location name is required!")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @NotEmpty(message = "Event description is required!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(message = "Address is required!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Future(message = "Date must be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    @JsonProperty("isPublic")
    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
