package gamesleague;

/**
 * Exception that is thrown when an illegal email (i.e. an email that already exists) is used in used in games league.
 */
public class IllegalEmailException extends RuntimeException {
    public IllegalEmailException(String m) {
        super(m);
    }
}
