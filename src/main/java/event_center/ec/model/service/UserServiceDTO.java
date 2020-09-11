package event_center.ec.model.service;

public class UserServiceDTO extends BaseServiceModel{
    private String email;

    public UserServiceDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
