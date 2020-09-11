package event_center.ec.exception;

public class PasswordsNotMatchException extends RuntimeException{
    public PasswordsNotMatchException() {
    }

    public PasswordsNotMatchException(String message) {
        super(message);
    }
}
