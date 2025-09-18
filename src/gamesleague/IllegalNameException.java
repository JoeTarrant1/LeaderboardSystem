package gamesleague;

/**
 * Exception that is most notably thrown when a name is duplicated (i.e. tried to use more than once).
 */
public class IllegalNameException extends RuntimeException {
    public IllegalNameException(String m) {
        super(m);
    }
}
