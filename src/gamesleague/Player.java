package gamesleague;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

/**
 * Represents a single account with all the irrespective data such as email, phone number, account ID, and what leagues the player is in/owns/is invited to.
 */
public class Player implements java.io.Serializable {
    /**
     * The player's email.
     */
    private String email;
    
    /**
     * The player's display name.
     */
    private String displayName;

    /**
     * The player's name.
     */
    private String name;

    /**
     * The player's phone number.
     */
    private String phone;

    /**
     * The unique ID for this player.
     */
    private int id;
    private static int counter = 0;

    /**
     * Status of whether the player is deactivated or not.
     */
    private boolean deactivated = false;

    /**
     * List of leagues that the player is in.
     */
    private ArrayList<Integer> leagueIds;

    /**
     * List of leagues that the player owns.
     */
    private ArrayList<Integer> ownedLeagueIds;

    /**
     * List of leagues that the player has been invited to.
     */
    private ArrayList<Integer> leagueInviteIds;

    /**
     * Status of whether the account is currently active or not.
     */
    private boolean inactive = false;

    /**
     * Date the player was created.
     */
    private LocalDate dateCreated;

    /**
     * Number of rounds played by this player.
     */
    private int roundsPlayed;

    /**
     * Constructor for the player class
     * 
     * @param email Player's email
     * @param displayName Player's display name
     * @param name Player's name
     * @param phone Player's phone number
     */
    public Player (String email, String displayName, String name, String phone) {
        this.email = email;
        this.displayName = displayName;
        this.name = name;
        this.phone = phone;
        this.id = counter++;
        leagueIds = new ArrayList<>();
        ownedLeagueIds = new ArrayList<>();
        leagueInviteIds = new ArrayList<>();
        dateCreated = DateProvider.now();
        roundsPlayed = 0;
    }

    /**
     * Get the player's email.
     *
     * @return The email of the player.
     */
    public String getEmail () {
        return this.email;
    }

    /**
     * Set the player's email.
     *
     * @param email The email of the player.
     */
    public void setEmail (String email) {
        this.email = email;
    }

    /**
     * Get the player's display name.
     *
     * @return The display name of the player.
     */
    public String getDisplayName () {
        return this.displayName;
    }

    /**
     * Set the player's display name.
     *
     * @param displayName The display name of the player.
     */
    public void setDisplayName (String displayName) {
        this.displayName = displayName;
    }

    /**
     * Get the player's name.
     *
     * @return The name of the player.
     */
    public String getName () {
        return this.name;
    }

    /**
     * Set the player's name.
     *
     * @param name The name of the player.
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Get the player's phone.
     *
     * @return The contact phone of the player or an empty string.
     */
    public String getPhone () {
        return this.phone;
    }

    /**
     * Set the player's phone.
     *
     * @param phone The contact phone of the player or an empty string.
     */
    public void setPhone (String phone) {
        this.phone = phone;
    }

    /**
     * Get the player's ID.
     *
     * @return The ID of the player.
     */
    public int getId () {
        return this.id;
    }


    /**
     * Gets the status of whether the player is deactivated or not.
     *
     * @return The boolean value indicating whether the player's account is deactivated or not.
     */
    public boolean getDeactivateStatus () {
        return this.deactivated;
    }

    /**
     * Sets the status of whether the player is deactivated or not.
     *
     * @param deactivated The boolean value indicating whether the player's account is deactivated or not.
     */
    public void setDeactivatedStatus (boolean deactivated) {
        this.deactivated = deactivated;

        // Also sets the player's join date to null if they are deactivated.
        if (deactivated) {
            dateCreated = null;
        }
    }

    /**
     * Gets the status of whether the player is inactive or not.
     *
     * @return The boolean value indicating whether the player's account is inactive or not.
     */
    public boolean getInactiveStatus () {
        return this.inactive;
    }

    /**
     * Sets the status of whether the player is inactive or not.
     *
     * @param inactive The boolean value indicating whether the player's account is inactive or not.
     */
    public void setInactiveStatus (boolean inactive) {
        this.inactive = inactive;
    }


    /**
     * Gets the ArrayList containing the Ids of the leagues the player is in.
     * 
     * @return The ArrayList containing the Ids of the leagues the player is in.
     */
    public ArrayList<Integer> getLeagueIds(){
        return leagueIds;
    }

    /**
     * Appends the Id for a new league the player is in.
     * 
     * @param leagueId The Id of the new league the player is in.
     */
    public void appendLeagueIds(int leagueId){
        this.leagueIds.add(leagueId);
    }

    /**
     * Searches for and attempts to the specified league Id from the array.
     * 
     * @param leagueId The Id of the league to be removed.
     * @throws IllegalOperationException If Id to be removed is not present.
     */
    public void removeLeagueId(int leagueId) throws IllegalOperationException{

        // Check if the league ID is in the list
        boolean found = false;
        for (int i : leagueIds){
            if (i == leagueId){
                found = true;
            }
        }

        // Throw an exception if the ID does not exist in the list
        if (!found){
            throw new IllegalOperationException("Player cannot be removed from this league, since they aren't a part of it.");
        }

        // Remove the ID from the list
        leagueIds.remove(Integer.valueOf(leagueId));
    }

    /**
     * Gets the ArrayList containing the Ids of the leagues the player owns.
     * 
     * @return The ArrayList containing the Ids of the leagues the player owns.
     */
    public ArrayList<Integer> getOwnedLeagueIds(){
        return ownedLeagueIds;
    }

    /**
     * Appends the Id for a new league the player owns.
     * 
     * @param leagueId The Id of the new league the player owns.
     */
    public void appendOwnedLeagueIds(int leagueId){
        this.ownedLeagueIds.add(leagueId);
    }

    /**
     * Searches for and attempts to the specified league Id from the array.
     * 
     * @param leagueId The Id of the league to be removed.
     * @throws IllegalOperationException If Id to be removed is not present.
     */
    public void removeOwnedLeagueId(int leagueId)  throws IllegalOperationException{

        // Check ID exists in the list
        boolean found = false;
        for (int i : ownedLeagueIds){
            if (i == leagueId){
                found = true;
            }
        }

        // Throw an exception if the ID does not exist
        if (!found){
            throw new IllegalOperationException ("Player does not own this league, so cannot be removed as owner.");
        }

        // Remove the league ID from the list
        ownedLeagueIds.remove(Integer.valueOf(leagueId));
    }

    /**
     * Gets the ArrayList containing the Ids of the leagues the player has been invited to.
     * 
     * @return The ArrayList containing the Ids of the leagues the player has been invited to.
     */
    public ArrayList<Integer> getLeagueInviteIds(){
        return leagueInviteIds;
    }

    /**
     * Appends the Id for a new league the player has been invited to.
     * 
     * @param inviteId The Id of the new league the player has been invited to.
     */
    public void appendLeagueInviteIds(int inviteId){
        this.leagueInviteIds.add(inviteId);
    }

    /**
     * Searches for and attempts to the specified league Id from the array.
     * 
     * @param inviteId The Id of the league to be removed.
     * @throws IllegalOperationException If Id to be removed is not present.
     */
    public void removeLeagueInviteId(int inviteId)  throws IllegalOperationException{

        // Check if the ID is in the list
        boolean found = false;
        for (int i : leagueInviteIds){
            if (i == inviteId){
                found = true;
                break;
            }
        }

        // Throw an exception if the ID is not found
        if (!found){
            throw new IllegalOperationException("Error: Player hasn't been invited to this league, so invite cannot be removed.");
        }

        // Remove the ID from the list
        leagueInviteIds.remove(Integer.valueOf(inviteId));
    }

    /**
     * Gets the date the player was created (in epoch time).
     * 
     * @return The day the player was created (in epoch time).
     */
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /**
     * Gets the number of total rounds played by the player.
     * 
     * @return Total number of rounds played by the player.
     */
    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    /**
     * Increments the number of rounds played by the player.
     */
    public void incrementRoundsPlayed(){
        roundsPlayed++;
    }
}
