package exception;

public class InvalidInventoryException extends Exception {
    String message;

    public InvalidInventoryException(String message) {
        super(message);
    }

    public String getMessage() {
        return message;

    }
}