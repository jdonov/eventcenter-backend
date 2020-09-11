package event_center.ec.model.binding;

public class UserLoginBindingDTO {
    private String username;
    private String password;

    public UserLoginBindingDTO() {
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
