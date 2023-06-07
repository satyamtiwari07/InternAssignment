package Assignment.Assignment.exception;

public class AllExceptions extends RuntimeException{

    public AllExceptions() {
    }

    public AllExceptions(String message) {
        super(message);
    }

    public AllExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
