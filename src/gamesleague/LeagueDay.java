package gamesleague;

/**
 * Represents a single day in the league, and is used to hold information such as the status of the day.
 */
public class LeagueDay implements java.io.Serializable{
    /**
     * Epoch day of this LeagueDay.
     */
    private int day;

    /**
     * The status the day (closed, in-progress or pending).
     */
    private Status status;

    /**
     * Constructor for the LeagueDay class
     * 
     * @param day The epoch day that this LeagueDay refers to 
     */
    public LeagueDay(int day){
        this.day = day;
        status = Status.PENDING;
    }

    /**
     * Gets the day that this LeagueDay refers to 
     * 
     * @return The epoch day that this LeagueDay refers to 
     */
    public int getDay(){
        return day;
    }

    /**
     * Gets the status of the LeagueDay
     * 
     * @return The status of the LeagueDay
     */
    public Status getStatus(){
        return status;
    }

    /**
     * Sets the status of the LeagueDay
     * 
     * @param status The new status of the LeagueDay
     */
    public void setStatus(Status status){
        this.status = status;
    }
}