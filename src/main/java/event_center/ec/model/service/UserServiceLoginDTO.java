package event_center.ec.model.service;

public class UserServiceLoginDTO extends BaseServiceModel{
    private String username;
    private String password;

    public UserServiceLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
