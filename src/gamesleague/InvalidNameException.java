package gamesleague;

/**
 * Exception that is thrown when an invalid name is used.
 */
public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String m) {
        super(m);
    }
}
