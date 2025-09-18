package gamesleague;

/**
 * Exception that is thrown when an invalid email is used.
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String m) {
        super(m);
    }
}