package gamesleague;

/**
 * An exception that is typically thrown when an invalid ID is used in the games league.
 */
public class IDInvalidException extends RuntimeException {
    public IDInvalidException(String m) {
        super(m);
    }
}

