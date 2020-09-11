package event_center.ec.model.binding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestUpdateBindingDTO {
    private boolean isPending;
    private boolean isAttending;

    public GuestUpdateBindingDTO() {
    }

    @JsonProperty("isPending")
    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    @JsonProperty("isAttending")
    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean attending) {
        isAttending = attending;
    }
}
