package event_center.ec.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConstants {

    public static String EMAIL;
    public static String PASSWORD;

    @Value("${EMAIL_USERNAME}")
    public void setEmail(String email) {
        EmailConstants.EMAIL = email;
    }

    @Value("${EMAIL_PASSWORD}")
    public void setPassword(String password) {
        EmailConstants.PASSWORD = password;
    }
}
