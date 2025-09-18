package gamesleague;

import java.util.ArrayList;

/**
 * Represents a single player in the league, and can store data such as whether the player is active in the league and GameDay classes.
 */
public class LeagueMember implements java.io.Serializable{
    /**
     * Unique ID associated with this player.
     */
    private int playerId;

    /**
     * Status of whether the user is active in the league or not.
     */
    private boolean active;

    /**
     * List of gameDays associated with this player.
     */
    private ArrayList<GameDay> gameDays;

    /**
     * Constructor for LeagueMember class
     * 
     * @param playerId The ID of the player that this LeagueMember relates to
     */
    public LeagueMember(int playerId){
        this.playerId = playerId;
        active = true;
        gameDays = new ArrayList<>();
    }

    /**
     * Gets the player ID
     * 
     * @return The player ID
     */
    public int getPlayerId(){
        return playerId;
    }

    /**
     * Checks if player is active in the league
     * 
     * @return The player's boolean active attribute
     */
    public boolean isActive(){
        return active;
    }

    /**
     * Sets the player's active attribute
     * 
     * @param active The new value for the active attribute
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * Gets the ArrayList of GameDays related to this LeagueMember
     * 
     * @return The memory address of the gameDays ArrayList
     */
    public ArrayList<GameDay> getGameDays(){
        return gameDays;
    }

    /**
     * Adds a new GameDay object to the gameDays ArrayList 
     * 
     * @param day The day that the GameDay refers to 
     * @param gameReport The game report for that GameDay
     */
    public void addGameDay(int day, String gameReport){
        gameDays.add(new GameDay(day, gameReport));
    }

    /**
     * Finds and returns the GameDay object that relates to a specific day
     * 
     * @param day The epoch day that is being searched for
     * @return The memory location of the GameDay object or null if it does not exist
     */
    public GameDay getGameDayByDay(int day){

        // Loops over the list of GameDays to find the correct one to return
        for(GameDay d : gameDays){
            if (d.getDay() == day){
                return d;
            }
        }

        // Returns null if the GameDay cannot be found
        return null;
    }

    /**
     * This method goes through every GameDay object related to this LeagueMember and
     * sets their game reports to an empty string
     * 
     */
    public void emptyGameDays() {

        // Loops over every game day and sets all the game reports to empty strings
        for(GameDay d : gameDays){
            d.setGameReport("");
        }
    }

    /**
     * This clears the ArrayList of GameDays
     * 
     */
    public void resetLeagueMember() {
        gameDays.clear();
    }
}