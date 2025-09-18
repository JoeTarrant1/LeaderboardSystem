package gamesleague;

/**
 * Enum representing the three states that the class (league, day, etc.) can be in.
 *
 */
public enum Status {
    /**
     * - If the class has start date is in the future (or no start date specified)
     *   the status should be PENDING
     */
    PENDING, 
    /**
     *  - If the class has a specified start date in the past and 
     *    a specified end date in the future (or no specified end date) then 
     *    the status should be IN_PROGRESS.
     */
    IN_PROGRESS, 
    /**
     *  - If the class has a specified end date in the past then
     *    the status should be CLOSED.
     */
    CLOSED;
}