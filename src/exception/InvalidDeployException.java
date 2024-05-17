package exception;

public class InvalidDeployException extends Exception {
    String message;

    public InvalidDeployException(String message) {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}