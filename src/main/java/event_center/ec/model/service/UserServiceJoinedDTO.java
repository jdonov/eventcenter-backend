package event_center.ec.model.service;

public class UserServiceJoinedDTO extends BaseServiceModel{
    private String name;
    private String email;

    public UserServiceJoinedDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
