package event_center.ec.model.service;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceJoinedUsersDTO extends BaseServiceModel{
    private LocalDateTime dateTime;
    private String name;
    private List<UserServiceJoinedDTO> users;

    public EventServiceJoinedUsersDTO() {
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

    public List<UserServiceJoinedDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserServiceJoinedDTO> users) {
        this.users = users;
    }
}
