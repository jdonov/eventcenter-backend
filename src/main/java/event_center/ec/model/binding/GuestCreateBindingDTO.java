package event_center.ec.model.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import event_center.ec.model.entity.Event;

public class GuestCreateBindingDTO {
    private String email;
    private String name;
    private String status;
    private boolean isAttending;
    private boolean isPending;

    public GuestCreateBindingDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("isAttending")
    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean attending) {
        isAttending = attending;
    }

    @JsonProperty("isPending")
    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }
}
