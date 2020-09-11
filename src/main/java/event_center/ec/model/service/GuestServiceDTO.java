package event_center.ec.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestServiceDTO extends BaseServiceModel{
    private String email;
    private boolean isAttending;
    private boolean isPending;
    private String name;
    private String state;
    private String status;

    public GuestServiceDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
