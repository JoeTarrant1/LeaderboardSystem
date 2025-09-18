package gamesleague;

/**
 * Exception that is thrown when an invalid date is used.
 */
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String m) {
        super(m);
    }
}
