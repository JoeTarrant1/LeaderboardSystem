package gamesleague;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a single game league where players can be invited, games can be run, and scores/points can be stored.
 */
public class League implements java.io.Serializable {
    /**
     * Original owner of the league.
     */
    private int owner;

    /**
     * Name of this league.
     */
    private String name;

    /**
     * What type of games this league plays.
     */
    private GameType gameType;
    private static int counter = 0;

    /**
     * Unique id associated with this league.
     */
    private int id;

    /**
     * List of players currently in the league.
     */
    private ArrayList<LeagueMember> players;

    /**
     * List of players currently invited to the league.
     */
    private ArrayList<String> playerInvites;

    /**
     * List of current owners of the league.
     */
    private ArrayList<Integer> owners;

    /**
     * ArrayList containing all the LeagueDays created for this league.
     */
    private ArrayList<LeagueDay> days;

    /**
     * The epoch date that the league starts.
     */
    private int startDate;

    /**
     * The epoch date that the league closes.
     */
    private int endDate;

    /**
     * Total number of rounds played by the league.
     */
    private int totalRounds;

    /**
     * Constructor for the League class
     * 
     * @param owner The player ID of the original owner of the league
     * @param name The name of the league
     * @param gameType The GameType that the league is set up for
     */
    public League(int owner, String name, GameType gameType){
        this.owner = owner;
        this.name = name;
        this.gameType = gameType;
        startDate = -1;
        endDate = -1;
        id = counter++;
        players = new ArrayList<>();
        playerInvites = new ArrayList<>();
        owners = new ArrayList<>();
        owners.add(owner);
        days = new ArrayList<>();
        totalRounds = 0;
    }

    /**
     * This new values that are required to be changed when cloning a league
     * 
     * @param players The list of players in the league
     * @param owners The list of owners of the league
     * @param days The list of days that have been played in the league
     * @param totalRounds The total rounds played in the league
     */
    public void cloneLeagueSetup(ArrayList<LeagueMember> players, ArrayList<Integer> owners, 
    ArrayList<LeagueDay> days, int totalRounds) {
        this.owners = owners;
        this.players.clear();

        for (int ownerId : owners) {
            for (LeagueMember lm : players) {
                if (lm.getPlayerId() == ownerId) {
                    this.players.add(lm);
                }
            }
        }

        this.days = days;
        this.totalRounds = totalRounds;
    }

    /**
     * Gets the total rounds played in the league
     * 
     * @return The total rounds played in the league
     */
    public int getRounds() {
        return totalRounds;
    }

    /**
     * This increases the number of rounds played in the league by 1
     * 
     */
    public void incrementTotalRounds() {
        totalRounds++;
    }

    /**
     * This gets the status of the whole league
     * 
     * @return The enum status of the league
     */
    public Status getStatus() {

        // Calculate the current epoch day
        int currentDate = (int) DateProvider.now().toEpochDay();

        // If the current date is before the start date or the start date is undefined then the league is pending
        if (currentDate < startDate || startDate == -1){
            return Status.PENDING;
        }
        // If the current date is after the start date and less than the end date or the end date is undefined then the status is in progress
        else if (currentDate >= startDate && (currentDate < endDate || endDate == -1)){
            return Status.IN_PROGRESS;
        }
        // Otherwise the status is closed
        else {
            return Status.CLOSED;
        }
    }


    /**
     * Gets the player ID of the original owner
     * 
     * @return The player ID of the original
     */
    public int getOwner(){
        return owner;
    }

    /**
     * Sets the player ID of the owner
     * 
     * @param owner The new player ID for the owner
     */
    public void setOwner(int owner){
        this.owner = owner;
    }

    /**
     * Gets the name of the league
     * 
     * @return The name of the league
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the league
     * 
     * @param name The new name of the league
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the GameType of the league
     * 
     * @return The enum GameType of the league
     */
    public GameType getGameType(){
        return gameType;
    }

    /**
     * Gets the ID of the league
     * 
     * @return The integer ID of the league
     */
    public int getId(){
        return id;
    }

    /**
     * Gets the list of members in the league
     * 
     * @return The memory address of the LeagueMembers list
     */
    public ArrayList<LeagueMember> getPlayers(){
        return players;
    }

    /**
     * If the player isn't already a LeagueMember, they are added as a LeagueMember.
     * 
     * @param player The player to be added.
     */
    public void addPlayer(Player player){

        // Check if player is already in league
        boolean found = false;
        for (LeagueMember p : players) {
            if (p.getPlayerId() == player.getId()) {
                found = true;
            }
        }

        // If player is already in league, print a message
        if (found) {
            System.out.println("Player is already in this league");
        }
        // If the player is not in the league then add them to the league 
        else if (!found) {
            players.add(new LeagueMember(player.getId()));
        }
    }

    /**
     * If the player is a LeagueMember, they are removed from the league.
     * 
     * @param player The player to be removed.
     */
    public void removePlayer(Player player){

        // Check if the player is in the league
        boolean found = false;
        LeagueMember removeLeagueMember = null;
        for (LeagueMember p : players) {
            if (p.getPlayerId() == player.getId()) {
                found = true;
                removeLeagueMember = p;
            }
        }

        // If they are not in the league then print an error message
        if (!found) {
            System.out.println("Player not found in league");
        } 
        // If they are in the league then remove them
        else if (found) {
            players.remove(removeLeagueMember);
        }
    }

    /**
     * This checks if the player with the inputted email is currently invited to the league
     * 
     * @param email The email of the player to be checked
     * @return True if the player is invited to the league, False if not
     */
    public boolean doesInviteExist(String email){
        
        // Loops through list of invited emails
        for (String e : playerInvites){
            // If the email is a match return true
            if (e.equals(email)){
                return true;
            }
        }
        return false;
    }

    /**
     * This adds a new player email to the invite list
     * 
     * @param email The email of the player to be invited
     * @throws InvalidEmailException If the player email is already in the invite list
     */
    public void addPlayerInvite(String email) throws InvalidEmailException{

        // Checks if invite already exists
        if (doesInviteExist(email)){
            throw new InvalidEmailException("Cannot invite player since they are already invited.");
        }

        // Adds new invite
        playerInvites.add(email);
    }

    /**
     * Removes a player email from the invite list
     * 
     * @param email The email to be removed from the list
     * @throws InvalidEmailException If the player email is not in the invite list
     */
    public void removePlayerInvite(String email) throws InvalidEmailException{

        // Checks if invite exists
        if (!doesInviteExist(email)){
            throw new InvalidEmailException("Cannot remove invite that doesn't exist.");
        }

        // Removes invite
        playerInvites.remove(email);
    }

    /**
     * Gets the array of new player invites emails.
     * 
     * @return The array of new player invite emails.
     */
    public ArrayList<String> getPlayerInvites() {
        return playerInvites;
    }

    /**
     * Gets the list of owners of the league
     * 
     * @return The list of player IDs belonging to the owners
     */
    public ArrayList<Integer> getOwners(){
        return owners;
    }

    /**
     * Adds a new owner to the list of owners
     * 
     * @param playerId The ID of the player to be added
     * @throws IDInvalidException If the player is already an owner of the league
     */
    public void addOwner(int playerId) throws IDInvalidException{

        // Check if player is already an owner
        for (int i : owners){
            if (i == playerId){
                throw new IDInvalidException("This player cannot be added as an owner, as they already are an owner.");
            }
        }

        // Adds new owner
        owners.add(playerId);
    }

    /**
     * Removes an owner from the league
     * 
     * @param playerId The ID of the player to be removed
     * @throws IDInvalidException If the player is not an owner of the league
     */
    public void removeOwner(int playerId) throws IDInvalidException{

        // Checks if player is an owner
        boolean found = false;
        for (int i : owners){
            if (i == playerId){
                found = true;
            }
        }

        // If the player is not an owner then throw an exception
        if (!found){
            throw new IDInvalidException("Cannot remove this player as the owner, since they aren't an owner.");
        }

        // Remove the owner from the list of owners
        owners.remove(Integer.valueOf(playerId));
    }

    /**
     * Gets the list of inactive players in the league.
     * 
     * @return List of inactive player Ids.
     */
    public ArrayList<Integer> getInactivePlayers(){

        ArrayList<Integer> inactivePlayers = new ArrayList<>();

        // Loops through the list of players and adds any inactive ones to the list
        for (LeagueMember p : players) {
            if (!p.isActive()) {
                inactivePlayers.add(p.getPlayerId());
            }
        }
        return inactivePlayers;
    }

    /**
     * Gets the list of active players in the league.
     * 
     * @return List of active player Ids.
     */
    public ArrayList<Integer> getActivePlayers(){

        ArrayList<Integer> activePlayers = new ArrayList<>();

        // Loops through the list of players and adds any active ones to the list
        for (LeagueMember p : players) {
            if (p.isActive()) {
                activePlayers.add(p.getPlayerId());
            }
        }
        return activePlayers;
    }

    /**
     * Changes the player to inactive in the league.
     * 
     * @param playerId Id of the player to be set as inactive.
     */
    public void setInactivePlayer(int playerId) {

        // Loops through the list of players to find the correct player and sets them to inactive
        for (LeagueMember p : players) {
            if (p.getPlayerId() == playerId) {
                p.setActive(false);
            }
        }
    }

    /**
     * Changes the player to be active in the league.
     * 
     * @param playerId Id of the player to be set as inactive.
     */
    public void setActivePlayer(int playerId) {

        // Loops through the list of players to find the correct one and sets them to active
        for (LeagueMember p : players) {
            if (p.getPlayerId() == playerId) {
                p.setActive(true);
            }
        }
    }

    /**
     * Attempts to empty all the game reports for the specified user.
     * 
     * @param playerId The ID of the player.
     */
    public void emptyGameReports(int playerId) {

        // Loops through the list of players and empties their game reports
        for (LeagueMember m : players) {
            if (m.getPlayerId() == playerId) {
                m.emptyGameDays();
            }
        }
    }

    /**
     * Resets all relevant attributes needed when resetting the league
     * 
     */
    public void resetLeague() {
        totalRounds = 0;
        days.clear();
        for (LeagueMember m : players) {
            m.resetLeagueMember();
        }
    }

    /**
     * Gets the start date of the league
     * 
     * @return The epoch start day of the league
     */
    public int getStartDate(){
        return startDate;
    }

    /**
     * Sets the start date of the league
     * 
     * @param startDate The new epoch start day of the league
     */
    public void setStartDate(int startDate){
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the league
     * 
     * @return The epoch end date of the league
     */
    public int getEndDate(){
        return endDate;
    }

    /**
     * Sets the end date of the league
     * 
     * @param endDate The new epoch end day of the league
     */
    public void setEndDate(int endDate){
        this.endDate = endDate;
    }

    /**
     * Gets the list of days in the league
     * 
     * @return The memory address of the ArrayList of LeagueDays
     */
    public ArrayList<LeagueDay> getDays(){
        return days;
    }

    /**
     * Adds a new LeagueDay to the league
     * 
     * @param day The epoch day that needs to be added
     */
    public void addDay(int day){
        days.add(new LeagueDay(day));
    }

    /**
     * This finds a player from the list of LeagueMembers using the player's ID
     * 
     * @param playerId The ID of the player to be found
     * @return The memory address of the player or null if the player does not exist in the league
     */
    public LeagueMember getPlayerById(int playerId){

        // Loops through the list of players to find the correct one to return
        for (LeagueMember l : players){
            if (l.getPlayerId() == playerId){
                return l;
            }
        }

        // Returns null if player is not found
        return null;
    }

    /**
     * Finds a specific LeagueDay from the list of LeagueDays using the epoch day
     * 
     * @param day The epoch day of the League day to be found
     * @return The memory address of the league object or null if the League day does not exist
     */
    public LeagueDay getDayByDay(int day){

        // Loops over all the days in the league to find the right one to return
        for (LeagueDay d : days){
            if (d.getDay() == day){
                return d;
            }
        }

        // Adds day if no league day already exists
        addDay(day);

        // Call the method again to return correct LeagueDay
        return getDayByDay(day);
    }

    /**
     * Checks if a given day is within the league start and end dates
     * 
     * @param day The epoch day that needs to be checked
     * @return True if the day is within the league, False if not
     */
    public boolean isDayInLeague(int day){ // ???? 
        if (day >= startDate && (day <= endDate ||  endDate == -1)){
            return true;
        }
        return false;
    }

    /**
     * This totals up all the players' points between two dates and returns them in a consistent order with getLeaguePlayers()
     * 
     * @param firstDay The epoch day of lower bound of the total
     * @param lastDay The epoch day of the upper bound of the total
     * @return An array of total points for each player that is consistent with the order of getLeaguePlayers()
     */
    public int[] getTotalPoints(int firstDay, int lastDay){

        int[] totals = new int[players.size()];

        // Loops over the totals array
        for (int i = 0; i < totals.length; i++){
            int sum = 0;
            // Loops over every day from the start day to the end day and adds the points for each day to the total
            for (int j = firstDay; j <= lastDay; j++){
                if(players.get(i).getGameDayByDay(j) != null){
                    sum += players.get(i).getGameDayByDay(j).getPoints();
                }
                else{
                    sum += 0;
                }
            }
            totals[i] = sum;
        }

        return totals;
    }

    /**
     * This totals up all the players' scores between two dates and returns them in a consistent order with getLeaguePlayers()
     * 
     * @param firstDay The epoch day of lower bound of the total
     * @param lastDay The epoch day of the upper bound of the total
     * @return An array of total scores for each player that is consistent with the order of getLeaguePlayers()
     */
    public int[] getTotalScore(int firstDay, int lastDay){

        int[] totals = new int[players.size()];

        // Loops over the totals array
        for (int i = 0; i < totals.length; i++){
            int sum = 0;
            // Loops over every day from the start to the end day and adds the scores for each day to the total
            for (int j = firstDay; j <= lastDay; j++){
                if(players.get(i).getGameDayByDay(lastDay) != null){
                    sum += players.get(i).getGameDayByDay(j).getScore();
                }
                else{
                    sum += 0;
                }
            }
            totals[i] = sum;
        }
        
        return totals;
    }
}