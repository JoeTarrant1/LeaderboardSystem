package gamesleague;

/**
 * Exception that is thrown when an operation is used in an illegal or invalid way.
 */
public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String m) {
        super(m);
    }
}
