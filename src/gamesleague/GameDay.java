package gamesleague;

/**
 * Represents a single day in the league for a player, and is used to hold information on the day such as game reports and points.
 */
public class GameDay implements java.io.Serializable {
    /**
     * Epoch day of this GameDay.
     */
    private int day;

    /**
     * String containing the game report for this day.
     */
    private String gameReport;

    /**
     * The status the day (closed, in-progress or pending).
     */
    private Status status;

    /**
     * Number of league points obtained from the score on this day.
     */
    private int points;

    /**
     * Outcome of the game played on this day.
     */
    private int score;

    /**
     * Constructor for the GameDay class
     * 
     * @param day The epoch day that this GameDay instance is referring to
     * @param gameReport A string report that details the gameplay that has occurred on this day
     */
    public GameDay(int day, String gameReport){
        this.day = day;
        this.gameReport = gameReport;
        status = Status.PENDING;
        points = 0;
        score = 0;
    }

    /**
     * Gets the epoch day that this object is referring to
     * 
     * @return epoch day
     */
    public int getDay(){
        return day;
    }

    /**
     * Gets the game report for this day
     * 
     * @return The game report string variable
     */
    public String getGameReport(){
        return gameReport;
    }

    /**
     * Sets the game report to a new value
     * 
     * @param gameReport The new string value that should replace game report
     */
    public void setGameReport(String gameReport){
        this.gameReport = gameReport;
    }

    /**
     * Gets the status of the game day
     * 
     * @return The status of the day
     */
    public Status getStatus(){
        return status;
    }

    /**
     * Sets the status of the day
     * 
     * @param status The new status of the day
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * Gets the points associated with this day
     * 
     * @return The points associated with this day
     */
    public int getPoints(){
        return points;
    }

    /**
     * Sets the points
     * 
     * @param points The new value of the points
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Gets the score 
     * 
     * @return The score
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets the score 
     * 
     * @param score The new score to be set
     */
    public void setScore(int score){
        this.score = score;
    }
}