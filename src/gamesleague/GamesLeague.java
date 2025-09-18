package gamesleague;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


// javac -d bin -cp bin src/gamesleague/*.java

/**
 * GamesLeague Class Template
 * You can use this template and add the correct method code.
 * At present it is a skeleton template with placeholder methods 
 * so that it can compile and implements all methods required by GamesLeagueInterface
 *
 * @author Philip Lewis
 * @version 0.3.6
 */

public class GamesLeague implements GamesLeagueInterface {

    /**
     * List of all player in the system
     */
    private ArrayList<Player> players = new ArrayList<>();
    /**
     * List of all leagues in the system
     */
    private ArrayList<League> leagues = new ArrayList<>();

    /**
     * This checks if the player exists in a given list
     * 
     * @param players Lists of players to be checked
     * @param id The ID of the player to be checked
     * @return True if the player is found in list, False if not
     */
    public boolean doesPlayerExist(ArrayList<Player> players, int id){
        boolean found = false;
        // Loop through each player in the list of players
        for (Player p : players){
            // If the player ID of the current player matches then the player exists
            if (p.getId() == id){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Checks if the player exists in a given list
     * 
     * @param leagues The list of leagues to be checked
     * @param id The ID of the league to check
     * @return True if the league is found in the list, False if not
     */
    public boolean doesLeagueExist(ArrayList<League> leagues, int id){
        boolean found = false;
        // Loop through each league in the list of leagues
        for (League l : leagues){
            // If the current league matches the ID then the league has been found
            if (l.getId() == id){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Returns a player from a list of players
     * 
     * @param players The list of players to be checked
     * @param id The ID of the player to find
     * @return The memory address of the player or null if the player is not found
     */
    public Player getPlayerIfExist(ArrayList<Player> players, int id){
        Player tempPlayer = null;
        // Loop through each player in the list of players
        for (Player p : players){
            //If the ID matches then set the temporary variable to the memory address of the player object
            if (p.getId() == id){
                tempPlayer = p;
                break;
            }
        }
        return tempPlayer;
    }

    /**
     * Returns a league from a list of leagues
     * 
     * @param leagues The list of leagues to be checked
     * @param id The ID of the league to be found
     * @return The memory address of the league or null if the league is not found
     */
    public League getLeagueIfExist(ArrayList<League> leagues, int id){
        League tempLeague = null;
        // Loop through each league in the list of leagues
        for (League l : leagues){
            // If the league ID matches then save the memory address in the temporary variable;
            if (l.getId() == id){
                tempLeague = l;
                break;
            }
        }
        return tempLeague;
    }

    /**
     * Checks if a player is in a league
     * 
     * @param leagueId The ID of the league to be checked
     * @param playerId The ID of the player to be found
     * @return True if the player is in the league, False if not
     * @throws IDInvalidException If the league does not exist or the player does not exist
     */
    public boolean isPlayerInLeague(int leagueId, int playerId) throws IDInvalidException{
        // Find the league that is being queried
        League l = getLeagueIfExist(leagues, leagueId);

        // Check if the league and the player exist
        if (l == null || !doesPlayerExist(players, playerId)){
            throw new IDInvalidException("Error: Player does not exist with this id.");
        }

        boolean found = false;
        // Loop through each LeagueMember (Player) in the league
        for (LeagueMember p : l.getPlayers()){
            // If the ID matches then the player has been found
            if (p.getPlayerId() == playerId){
                found = true;
                break;
            }
        }

        return found;
    }

    /**
     * This converts an Integer ArrayList to a primitive int array
     * 
     * @param toConvert The ArrayList to convert
     * @return The converted int array
     */
    public int[] convertArrayListIntegerToArrayInt(ArrayList<Integer> toConvert) {
        // Create a new array which is the same size as the ArrayList to convert
        int[] newArray = new int[toConvert.size()];

        // Loop through the ArrayList and add each element to the int array
        int counter = 0;
        for (Integer n : toConvert) {
            newArray[counter++] = n;
        }

        return newArray;
    }

    // Players

    /**
     * Get the players currently created in the platform.
     *
     * @return An array of player IDs in the system or an empty array if none exists.
     */
    public int[] getPlayerIds(){
        // Create an array the same size as the player list to store IDs
        int[] playerIds = new int[players.size()];

        // Loop through the list of players and add each of their IDs to the array
        int counter = 0;
        for (Player p : players){
            playerIds[counter++] = p.getId();
        }
        
        return playerIds;
    };


    /**
     * Creates a player entry.
     *
     * @param email The email of the player (unique).
     * @param displayName The name of the player.
     * @param name The name of the player.
     * @param phone The contact phone number of the player or empty string.
     * @return The ID of the rider in the system.
     * @throws InvalidNameException If the displayName/name is null or starts/ends with whitespace, 
     *                              or if the name is less than 5 char / more than 50 char.
     *                              or if displayName is less than 1 char/more than 20 char.
     * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character,
     * @throws IllegalEmailException if it duplicates an existing email of a player
     */
    public int createPlayer(String email, String displayName, String name, String phone) 
        throws  InvalidEmailException,   
                IllegalEmailException,
                InvalidNameException {
        
            // Check that the email is valid
            if (email == null || email.equals("") || !email.contains("@")){
                throw new InvalidEmailException("Error: Email does not meet requirements");
            }

            // Check if the name is valid
            if (name == null || name.length() < 5 || name.length() > 50 || name.charAt(0) == ' ' || name.charAt(name.length()-1) == ' '){
                throw new InvalidNameException("Error: Name does not meet requirements");
            }
            
            // Check that the display name is valid
            if (displayName == null || displayName.length() < 1 || displayName.length() > 20 || displayName.charAt(0) == ' ' || displayName.charAt(displayName.length()-1) == ' '){
                throw new InvalidNameException("Error: Display name does not meet requirements");
            }

            // Check if the player email is already in use
            for (Player p : players){
                if (p.getEmail().equals(email)){
                    throw new IllegalEmailException("Error: This email is already in the system");
                }
            }
            // Create the new player and add it to the list of players
            Player temp = new Player(email, displayName, name, phone);
            players.add(temp);

            // If the player has any invitations to leagues already, add it to their list of invites.
            for (League inviteLeague : leagues) {
                for (int inviteId : this.getLeaguePlayerInvites(inviteLeague.getId())) {
                    if (temp.getId() == inviteId) {
                        temp.appendLeagueInviteIds(inviteLeague.getId());
                    }
                }
            }

            return temp.getId();
        }


    /**
     * Permanently deactivates player account.
     * <p>
     * Note to preserve the integrity of the league tables the removal process should follow:
     * i) all personal player anonymised as below:
     *     - name &amp; displayName to "anonymousplayerX" where X is playerId
     *     - email is set to "" and phone is set to ""
     * ii) all player gameplay reports are set to empty strings
     * iii) player is set as inactive in all their league memberships
     *
     * @param playerId The ID of the player to be deactivated.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws IllegalOperationException If the player is the sole owner of a league.
     */
    public void deactivatePlayer(int playerId) 
        throws IDInvalidException, IllegalOperationException {
        // Does this player exist?
        Player anonymPlayer = getPlayerIfExist(players, playerId);

        // If they don't throw an IDInvalidException.
        if (anonymPlayer == null) {
            throw new IDInvalidException("ID is not valid");
        }

        // If the player exists, are they the sole owner in any leagues?
        boolean sole_owner = false;
        for (Integer id : anonymPlayer.getOwnedLeagueIds()) {
            for (League l : leagues) {
                if (id == l.getId()) {
                    if (l.getOwners().size() == 1) {
                        sole_owner = true;
                    }
                }
            }
        }
        // If they are, throw an IllegalOperationException.
        if (sole_owner) {
            throw new IllegalOperationException("Error: This player cannot be deactivated as they are the sole owner of a league.");
        }

        // If they aren't the sole owner in any league, then anonymise the player's personal info.
        String new_name = String.format("anonymousplayer%d", playerId);
        anonymPlayer.setDisplayName(new_name);
        anonymPlayer.setName(new_name);
        anonymPlayer.setEmail("");
        anonymPlayer.setPhone("");


        // Set all player reports to empty strings, and set player as inactive in all their leagues.
        anonymPlayer.setInactiveStatus(true);
        anonymPlayer.setDeactivatedStatus(true);

        for (int leagueId : anonymPlayer.getLeagueIds()) {
            League l = getLeagueIfExist(leagues, leagueId);
            if (l != null) {
                l.setInactivePlayer(playerId);
                l.emptyGameReports(playerId);
            }
        }
    };


    /**
     * Check if a player has been deactivated.
     * 
     * @param playerId The ID of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     * @return true if player has been deactivated or false otherwise 
     */
    public boolean isDeactivatedPlayer(int playerId) 
        throws IDInvalidException{
        // Find the player that is being queried
        Player deactivePlayer = getPlayerIfExist(players, playerId);
        // Check if the player exists
        if (deactivePlayer == null) {
            throw new IDInvalidException("ID is not valid");
        }

        return deactivePlayer.getDeactivateStatus();
    };


    /**
     * Updates the player's display name.
     * 
     * @param playerId The ID of the player to be updated.
     * @param displayName The new display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     */
    public void updatePlayerDisplayName(int playerId, String displayName) 
        throws  IDInvalidException, InvalidNameException {
        // Check display name is valid
        if (displayName == null || displayName.length() < 1 || displayName.length() > 20 || displayName.charAt(0) == ' ' || displayName.charAt(displayName.length()-1) == ' '){
            throw new InvalidNameException("Error: Name does not meet requirements");
        }
        // Find the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check if the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        // Set the new display name
        tempPlayer.setDisplayName(displayName);
    };

    /**
     * Get the player id from the email.
     *
     * @param email The email of the player.
     * @return The ID of the player in the system or -1 if the player does not exist.
     */
    public int getPlayerId(String email){
        int id = -1;
        // Loop through each player in the ArrayList of players
        for (Player p : players){
            // If the email matches then get the ID of that player
            if (p.getEmail().equals(email)){
                id = p.getId();
                break;
            }
        }
        
        return id;
    }


    /**
     * Get the player's display name.
     * 
     * @param playerId The ID of the player being queried.
     * @return The display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     */
    public String getPlayerDisplayName(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check that the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempPlayer.getDisplayName();
    };


    /**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     */
    public String getPlayerEmail(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check that the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempPlayer.getEmail();
    };


    /**
     * Get the in progress leagues a player is currently a member.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player is in or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public int[] getPlayerLeagues(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check that the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        ArrayList<Integer> activeIds = new ArrayList<>();

        // This finds all the leagues that the player is a part of that are currently active
        for (int i : tempPlayer.getLeagueIds()){
            if (getLeagueIfExist(leagues, i).getStatus() == Status.IN_PROGRESS){
                activeIds.add(i);
            }
        }

        // Convert the Integer ArrayList to an int array before returning
        return convertArrayListIntegerToArrayInt(activeIds);
    };


    /** 
     * Get the leagues a player owns.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player owns or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public int[] getPlayerOwnedLeagues(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check that the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        // Convert the Integer ArrayList to an int array before returning
        return convertArrayListIntegerToArrayInt(tempPlayer.getOwnedLeagueIds());
    };

    /**
     * Get the user's invites.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player has invites to or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public int[] getPlayerInvites(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check that the player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        // Convert the Integer ArrayList to an int array before returning
        return convertArrayListIntegerToArrayInt(tempPlayer.getLeagueInviteIds());
    };


    /**
     * Get the user's rounds played game stat across all leagues
     * (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return number of rounds played by the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public int getPlayerRoundsPlayed(int playerId) throws IDInvalidException{

        // Gets the player being queried
        Player playerRounds = getPlayerIfExist(players, playerId);

        // Checks if the player exists
        if (playerRounds == null) {
            throw new IDInvalidException("No player exists with this ID anywhere in the system.");
        }

        return playerRounds.getRoundsPlayed();
    };

    /**
     * Increments the variable roundsPlayed for the specified player class by one.
     * 
     * @param playerID The ID of the player being queried.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public void incrementRoundPlayed(int playerID) throws IDInvalidException{

        // Gets the player being queried
        Player playerIncrement = getPlayerIfExist(players, playerID);

        // Checks if player exists
        if (playerIncrement == null) {
            throw new IDInvalidException("No player exists with this ID anywhere in the system.");
        }

        playerIncrement.incrementRoundsPlayed();
    }


    /**
     * Get the user's round participation percentage stat
     *  i.e. if they play all games in all their leagues every day this will be 100
     *  (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return percentage of rounds (0-100) played by the player across all leagues.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public double getPlayerRoundsPercentage(int playerId) throws IDInvalidException{
        // Does player exist?
        Player playerPercent = getPlayerIfExist(players, playerId);

        // If not throw IDInvalidException.
        if (playerPercent == null) {
            throw new IDInvalidException("This ID does not match to any player in the system.");
        }

        // If so, loop through and count the total rounds played across all of those player's leagues.
        int totalRoundsPlayed = 0;
        for (int leagueId : playerPercent.getLeagueIds()) {
            totalRoundsPlayed = totalRoundsPlayed + getLeagueIfExist(leagues, leagueId).getRounds();
        }

        // Divide player's rounds played against total rounds played and find the percentage.
        return (playerPercent.getRoundsPlayed()/totalRoundsPlayed) * 100;
    };

    /**
     * Get the date that the user joined the site
     * 
     * @param playerId The ID of the player being queried.
     * @return LocalDate that stores the date the player created their account.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    public LocalDate getPlayerJoinDate(int playerId) throws IDInvalidException{

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check if player exists
        if (tempPlayer == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempPlayer.getDateCreated();
    };

    // Leagues

    /**
     * Get the leagues currently created in the platform.
     *
     * @return An array of leagues IDs in the system or an empty array if none exists.
     */
    public int[] getLeagueIds(){

        int[] leagueIds = new int[leagues.size()];

        // Loop through each league in the leagues ArrayList and add their IDs to the array
        int counter = 0;
        for (League l : leagues){
            leagueIds[counter++] = l.getId();
        }
        
        return leagueIds;
    };

    /**
     * Creates a league.
     * 
     *
     * @param owner PlayerId of the league owner.
     * @param name The name of the league.
     * @param gameType The game for which the league is set up.
     * @return The ID of the league in the system.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name
     */
    public int createLeague(int owner, String name, GameType gameType) 
        throws  IDInvalidException, 
                InvalidNameException, 
                IllegalNameException{
        
        // Gets the player that matches the owner ID
        Player ownerPlayer = getPlayerIfExist(players, owner);

        // Checks if the owner exists
        if (ownerPlayer == null){
            throw new IDInvalidException("Error: ID of Owner does not match to an existing player");
        }

        // Check if the name is valid
        if (name == null || name.length() < 1 || name.length() > 20 || name.charAt(0) == ' ' || name.charAt(name.length()-1) == ' '){
            throw new InvalidNameException("Error: Name does not meet requirements");
        }

        // Check if the league name is already in use
        for (League l : leagues){
            if (l.getName().equals(name)){
                throw new IllegalNameException("Error: Name does not meet requirements");
            }
        }

        // Create the new league
        League temp = new League(owner, name, gameType);

        // Add the owner to the league
        temp.addPlayer(ownerPlayer);

        // Add the new league to the list of leagues
        leagues.add(temp);

        // Add the new league ID to the player's list of owned leagues
        ownerPlayer.appendOwnedLeagueIds(temp.getId());

        // Add the new league ID to the player's list of leagues
        ownerPlayer.appendLeagueIds(temp.getId());
        
        return temp.getId();
    };

    /**
     * Removes a league and all associated game data from the system.
     * 
     *
     * @param leagueId The ID of the league to be removed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public void removeLeague(int leagueId) throws IDInvalidException{
        
        // Get the league that is being removed
        League toRemove = getLeagueIfExist(leagues, leagueId);

        //Check if the league exists
        if (toRemove == null){
            throw new IDInvalidException("Error: No league exists with this ID.");
        }

        Player tempPlayer = null;
        // Remove all the players, owners and invitations
        for (int ownerId : toRemove.getOwners()) {
            tempPlayer = getPlayerIfExist(players, ownerId);
            if (tempPlayer != null) {
                tempPlayer.removeOwnedLeagueId(leagueId);
            }
            tempPlayer = null;
        }
        for (LeagueMember leaguePlayer : toRemove.getPlayers()) {
            tempPlayer = getPlayerIfExist(players, leaguePlayer.getPlayerId());
            if (tempPlayer != null) {
                tempPlayer.removeLeagueId(leagueId);
            }
            tempPlayer = null;
        }
        for (String inviteEmail : toRemove.getPlayerInvites()) {
            for (Player p : players) {
                if (p.getEmail().equals(inviteEmail)){
                    tempPlayer = p;
                    break;
                }
            }
            if (tempPlayer != null) {
                tempPlayer.removeLeagueInviteId(leagueId);
            }
            tempPlayer = null;
        }

        // Remove the league from the leagues ArrayList
        leagues.remove(toRemove);
    };

    /**
     * Get name of league
     * 
     * @param leagueId The ID of the league being queried.
     * @return The name of the league.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public String getLeagueName(int leagueId) throws IDInvalidException{

        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempLeague.getName();
    };

    /**
     * Update the name of a league
     * 
     * @param leagueId The ID of the league to be updated.
     * @param newName The new name of the league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name.
     * 
     */
    public void updateLeagueName(int leagueId, String newName) 
        throws IDInvalidException, 
                InvalidNameException, 
                IllegalNameException{

        // Get league being queried
        League toUpdate = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (toUpdate == null){
            throw new IDInvalidException("ID is not valid");
        }

        // Check if new name is valid
        if (newName == null || newName.length() < 1 || newName.length() > 20 || newName.charAt(0) == ' ' || newName.charAt(newName.length()-1) == ' '){
            throw new InvalidNameException("Error: Name does not meet requirements");
        }

        // Check if new name is already in use
        for (League l : leagues){
            if (l.getName().equals(newName)){
                throw new IllegalNameException("Error: Name is a duplicate of another league's name");
            }
        }

        // Update the name
        toUpdate.setName(newName);
    };


    /**
     * Invites a potential player (may not yet be site member) to a league.
     * 
     * @param leagueId The ID of the league to invite the player to.
     * @param email The email of the player to be invited.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character.
     */
    public void invitePlayerToLeague(int leagueId, String email) 
        throws IDInvalidException, InvalidEmailException{

        // Get league being queried
        League inviteLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (inviteLeague == null) {
            throw new IDInvalidException("League ID is not valid");
        }

        // Check if email is valid
        if (email == null || email.equals("") || !email.contains("@")){
            throw new InvalidEmailException("Error: Email does not meet requirements");
        }

        // Check if player exists
        boolean exists = false;
        for (Player p : players) {
            if (p.getEmail().equals(email)){
                exists = true;
                // Add the league invite to the player attribute
                p.appendLeagueInviteIds(leagueId);
                // Add the player invite to the league attribute
                inviteLeague.addPlayerInvite(email);
            }
        }

        // If the player does not exist throw an exception
        if (!exists) {
            inviteLeague.addPlayerInvite(email);
        }
    };

    /**
     * Accepts an invite to a league.
     *
     * @param leagueId The ID of the league to accept the invite to.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the player email does not have an active invitation.
     */
    public void acceptInviteToLeague(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

        // Get league that is being queried
        League invitedLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (invitedLeague == null){
            throw new IDInvalidException("leagueID does not match to a corresponding league.");
        }

        // Get player that is being invited
        Player toAccept = getPlayerIfExist(players, playerId);

        // Check player exists
        if (toAccept == null){
            throw new IDInvalidException("playerID does not match to a corresponding player.");
        }

        // Check if the player is invited to the league
        if (!invitedLeague.doesInviteExist(toAccept.getEmail())) {
            throw new IllegalOperationException("Cannot accept invite that doesn't exist.");
        }

        // Move the league ID from the invitation list to the current league list in the player object
        toAccept.removeLeagueInviteId(leagueId);
        toAccept.appendLeagueIds(leagueId);

        // Move the player from the invitation list to the players list in the league object
        invitedLeague.removePlayerInvite(toAccept.getEmail());
        invitedLeague.addPlayer(toAccept);
    };

    /**
     * Removes invite from league.
     * 
     * @param leagueId The ID of the league to remove the invite from.
     * @param email The email of the player to remove the invite from.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalEmailException If the email does not have an active invitation.
     */
    public void removeInviteFromLeague(int leagueId, String email)
        throws IDInvalidException, IllegalEmailException{

        // Does the league exist?
        League invitedLeague = getLeagueIfExist(leagues, leagueId);
        if (invitedLeague == null){
            throw new IDInvalidException("leagueID does not match an existing league.");
        }

        // If the league exists, does the player exist?
        Player toRemove = null;
        boolean found = false;
        for (Player p : players){
            if (p.getEmail().equals(email)) {
                found = true;
                toRemove = p;
                break;
            }
        }
        if (!found){
            throw new IDInvalidException("This email does not having a matching player.");
        }

        // If the player exists, does the invite exist? If it does remove it.
        boolean availableInvite = invitedLeague.doesInviteExist(email);
        
        // If the invite doesn't exist, then throw an IllegalEmailException.
        if (!availableInvite){
            throw new IllegalEmailException("This email does not have an active invite for this league.");
        }

        // If the invite exists then remove it.
        invitedLeague.removePlayerInvite(email);
        toRemove.removeLeagueInviteId(leagueId);
    };


    /**
     * Get league invitations for non-existing players.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of email addresses of players with pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public String[] getLeagueEmailInvites(int leagueId) throws IDInvalidException{

        // Get league being queried
        League leagueEmail = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (leagueEmail == null) {
            throw new IDInvalidException("Error: Cannot get league email invites, since this league doesn't exist.");
        }

        ArrayList<String> playerInviteEmails = new ArrayList<>();

        // If the player doesn't exist add them to the list.
        for (String email : leagueEmail.getPlayerInvites()) {
            if (getPlayerId(email) == -1) {
                playerInviteEmails.add(email);
            }
        }

        // Convert the list from an ArrayList to a String array.
        return playerInviteEmails.toArray(new String [playerInviteEmails.size()]);
    };


    /**
     * Get league invitations made to existing players (player IDs)
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs who have pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int[] getLeaguePlayerInvites(int leagueId) throws IDInvalidException{

        // Does the league exist?
        League leagueEmail = getLeagueIfExist(leagues, leagueId);
        if (leagueEmail == null) {
            throw new IDInvalidException("ID is not valid");
        }

        // If it does loop through all the invites and use the emails to find the player Ids.
        ArrayList<Integer> playerInviteIds = new ArrayList<>();
        
        for (String e : leagueEmail.getPlayerInvites()) {
            if (getPlayerId(e) != -1){
                playerInviteIds.add(getPlayerId(e));
            }
        }
        return convertArrayListIntegerToArrayInt(playerInviteIds);
    };


    /**
     * Get the players in a league. 
     * The order of players should be the user that created the league,
     * (i.e. original owner), followed by other players in the order they accepted 
     * the league invitations.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs in the league or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int[] getLeaguePlayers(int leagueId) throws IDInvalidException{

        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null) {
            throw new IDInvalidException("ID is not valid");
        }

        // Get the list of players in the league
        ArrayList<LeagueMember> tempLeaguePlayers = tempLeague.getPlayers();
        int[] playerIds = new int[tempLeaguePlayers.size()];
        int counter = 0;

        // Add each player ID in the player list to the array
        for (LeagueMember p : tempLeaguePlayers) {
            playerIds[counter++] = p.getPlayerId();
        }

        return playerIds;
    };


    /**
     * Get the owners of a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs that are owners of the league or empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int[] getLeagueOwners(int leagueId) throws IDInvalidException{

        // Gets the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Checks if league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: No league exists with this Id");
        }

        // Converts the Integer ArrayList to an int array before returning
        return convertArrayListIntegerToArrayInt(tempLeague.getOwners());
    };

    /**
     * Get the status of a league. Your code should look at the current local date
     * as epoch day and compare it with any start and end dates that have been set for the league
     * Note that leagues are created without a specified start/end date
     * 
     *  - If the league has start date is in the future (or no start date specified)
     *    the status should be PENDING
     * 
     *  - If the league has a specified start date in the past and 
     *    a specified end date in the future (or no specified end date) then 
     *    the status should be IN_PROGRESS
     * 
     *  - If the league has a specified end date in the past then
     *    the status should be CLOSED
     * 
     * @param leagueId The ID of the league being queried.
     * 
     * @return The status of the league as enum as above
     *          PENDING       not yet started
     *          IN_PROGRESS   league is active
     *          CLOSED        current date is after specified league end date 
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public Status getLeagueStatus(int leagueId) 
        throws IDInvalidException{
        
        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("leagueID is not valid, as no leagues exist with that ID.");
        }

        return tempLeague.getStatus();
    };


    /**
     * Start league
     *
     * @param leagueId The ID of the league to start.
     * @param day Should be set to the epoch day when league will be made active.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalOperationException If the league is already started.
     */
    public void setLeagueStartDate(int leagueId, int day) 
        throws IDInvalidException, IllegalOperationException{
        
        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: leagueId does not match to an existing league");
        }
        
        // Check if the league has already started or is already closed
        if (getLeagueStatus(leagueId) != Status.PENDING){
            throw new IllegalOperationException("Error: This league has already started");
        }
        // Check that the epoch day is valid 
        else if (day < 0) {
            throw new IllegalOperationException("Error: Invalid epoch day.");
        }

        // Set the start day
        tempLeague.setStartDate(day);
    };


    /** 
     * Close league, day specified may be any date after the league start day
     * 
     * @param leagueId The ID of the league to close.
     * @param day Should be set to the epoch day when league will be closed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalOperationException If the league is already closed or invalid day.
     */
    public void setLeagueEndDate(int leagueId, int day) 
        throws IDInvalidException, IllegalOperationException{

        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: No league with this ID exists.");
        }

        // Check if the league is already closed or the day is invalid or before the start date
        if (getLeagueStatus(leagueId) == Status.CLOSED || day <= getLeagueStartDate(leagueId)){
            throw new IllegalOperationException("Error: Either date is invalid or league has ended");
        }

        // Set the new end date
        tempLeague.setEndDate(day);
    };


    /**
     * Get the league start date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The start date of the league as epoch day.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int getLeagueStartDate(int leagueId) throws IDInvalidException{
        
        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempLeague.getStartDate();
    };


    /**
     * Get the date a closed league closed date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The closure date of the league as epoch day or -1 if not closed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int getLeagueCloseDate(int leagueId) throws IDInvalidException{

        // Get league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (tempLeague == null){
            throw new IDInvalidException("ID is not valid");
        }

        return tempLeague.getEndDate();
    };


    /**
     * Reset league by removing all gameplay history i.e. no scores, and gives it pending status. 
     * status of active/inactive players is unchanged.
     * 
     * @param leagueId The ID of the league to reset.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public void resetLeague(int leagueId) throws IDInvalidException{

        // Does the league exist?
        League leagueToReset = getLeagueIfExist(leagues, leagueId);

        // If it doesn't throw IDInvalidException.
        if (leagueToReset == null) {
            throw new IDInvalidException("Error: ID is invalid as it doesn't match to any league in the system.");
        }

        // If it does then reset all gameplay history.
        // Reset all gameplay history.
        leagueToReset.resetLeague();

        // Give pending status
        leagueToReset.setStartDate(-1);
        leagueToReset.setEndDate(-1);
    };


    /**
     * Clone a league to make a new league. 
     * Owners of the original league are also owners of the new league.
     * Invitations are created for all players in the original league.
     * League status is set to pending.
     * 
     * @param leagueId The ID of the league to clone.
     * @param newName The name of the new league.
     * @return The ID of the new league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name
     */
    public int cloneLeague(int leagueId, String newName) 
        throws IDInvalidException, IllegalNameException, IllegalNameException{

        // Get league being queried
        League oldLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (oldLeague == null) {
            throw new IDInvalidException("Error: ID does not match to any league in the system.");
        }
        
        // Make a new league object.
        League newLeague = getLeagueIfExist(leagues, createLeague(oldLeague.getOwner(), newName, oldLeague.getGameType()));
        Player oldOwner = getPlayerIfExist(players, oldLeague.getOwner());
        oldOwner.removeLeagueId(newLeague.getId());
        oldOwner.removeOwnedLeagueId(newLeague.getId());

        // Copy everything in the old league to the new league.
        newLeague.setEndDate(oldLeague.getEndDate());  // Set end date to -1 too?
        newLeague.cloneLeagueSetup(oldLeague.getPlayers(), oldLeague.getOwners(),
                                   oldLeague.getDays(), oldLeague.getRounds());

        // Invitations in the old league are sent out to players in the new league.
        for (String e : oldLeague.getPlayerInvites()) {
            invitePlayerToLeague(newLeague.getId(), e);
        }  // ???? doesn't explicitly state us to do this, may be wrong

        // Sends invitations out to all players in the league that aren't owners,
        // as owners are already added to the new league.
        for (LeagueMember e : oldLeague.getPlayers()) {
            boolean isThisOldOwner = false;
            for (int ownerId : oldLeague.getOwners()) {
                if (ownerId == e.getPlayerId()) {
                    isThisOldOwner = true;
                }
            }
            if (!isThisOldOwner) {
                invitePlayerToLeague(newLeague.getId(), getPlayerIfExist(players, e.getPlayerId()).getEmail());
            }
        }

        // Adds the leagueId to the corresponding arrays in the player class for each owner
        for (int ownerId : oldLeague.getOwners()) {
            Player ownerPlayer = getPlayerIfExist(players, ownerId);
            ownerPlayer.appendOwnedLeagueIds(newLeague.getId());
            ownerPlayer.appendLeagueIds(newLeague.getId());
        }

        return newLeague.getId();
    };


    /**
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * @return Boolean value of whether the player is active in the league or not.
     * @throws IDInvalidException If the ID does not match to any league or player in the league.
     */
    public boolean isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException{

        //Check if player exists
        if (!doesPlayerExist(players, playerId)) {
            throw new IDInvalidException("Error: playerId does not match to any player in the system");
        } 
        // Checks if player is in the league
        else if (!isPlayerInLeague(leagueId, playerId)) {
            throw new IDInvalidException("Error: playerId does not match to any player in the league");
        }

        // Gets the league being queried
        League activeLeague = getLeagueIfExist(leagues, leagueId);

        // Checks if league exists
        if (activeLeague == null) {
            throw new IDInvalidException("Error: leagueId does not match to any league in the system");
        }

        // Loop through the list of active players
        for (int activeId : activeLeague.getActivePlayers()) {
            // Returns true if the player ID matches an ID in the active player list
            if (activeId == playerId) {
                return true;
            }
        }
        
        return false;
    };

    /** 
     * Sets a player to be registered as inactive in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    public void setLeaguePlayerInactive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException {

        // Checks if player exists
        if (!doesPlayerExist(players, playerId)) {
            throw new IDInvalidException("Error: playerID does not match to any player in the system");
        }

        // Gets league being queried
        League inactiveLeague = getLeagueIfExist(leagues, leagueId);

        // Check if league exists
        if (inactiveLeague == null) {
            throw new IDInvalidException("Error: leagueID does not match to any league in the system");
        }

        // Check if player is in the league
        if (!isPlayerInLeague(leagueId, playerId)) {
            throw new IllegalOperationException("Error: playerID does not match any player in this league");
        }

        // Set the given player as inactive
        inactiveLeague.setInactivePlayer(playerId);
    };

    /** 
     * Sets a player to be registered as active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    public void setLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

        // Checks if player exists
        if (!doesPlayerExist(players, playerId)) {
            throw new IDInvalidException("Error: Player does not exist in the system.");
        }

        // Gets league being queried
        League activeLeague = getLeagueIfExist(leagues, leagueId);

        // Checks if league exists
        if (activeLeague == null) {
            throw new IDInvalidException("ID is not valid");
        }

        // Checks if player is in the league
        if (!isPlayerInLeague(leagueId, playerId)) {
            throw new IllegalOperationException("Error: The ID does not match to any player in the league");
        }

        // Set the given player to active
        activeLeague.setActivePlayer(playerId);
    };


    /** 
     * Add owner   
     * 
     * @param leagueId The ID of the league to add the owner to.
     * @param playerId The ID of the player to add as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the player is not a member of the league.
     */
    public void addOwner(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{
        
        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check if the league exists and the player exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: leagueId does not match to an existing league");
        }
        else if (tempPlayer == null) {
            throw new IDInvalidException("Error: playerId does not match to an existing player");
        }

        // Check if the player is a member of the league
        if (!isPlayerInLeague(leagueId, playerId)){
            throw new IllegalOperationException("Error: Player is not a member of this league");
        }

        // Add the specified player to the league's owner list
        tempLeague.addOwner(playerId);

        // Add the specified league to the player's owner list
        tempPlayer.appendOwnedLeagueIds(leagueId);
    };

    /** 
     * Remove owner
     * 
     * @param leagueId The ID of the league to remove the owner from.
     * @param playerId The ID of the player to remove as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the process would leave the league without an owner.
     * 
     */
    public void removeOwner(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Get the player being queried
        Player tempPlayer = getPlayerIfExist(players, playerId);

        // Check if the league exists and the player exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: leagueId does not match to an existing league");
        }
        else if (tempPlayer == null) {
            throw new IDInvalidException("Error: playerId does not match to an existing player");
        }

        // Check if the league only has one owner
        if (tempLeague.getOwners().size() == 1){
            throw new IllegalOperationException("Error: This would leave the league without an owner");
        }

        // Remove the specified player from the league's owner list
        tempLeague.removeOwner(playerId);

        // Remove the specified league to the player's owner list
        tempPlayer.removeOwnedLeagueId(leagueId);
    };


    // Results

    /**
     * Register gameplay by a player in a league. 
     * 
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * @param gameReport A report detailing the gameplay, may be empty if no report made,
     *                   this may be updated e.g. after other players take actions that affect result
     *
     * @throws IDInvalidException If ID do not match to any league &amp; player in the system.
     * @throws IllegalOperationException If the day is not a valid day for the league.
     */
    public void registerGameReport(int day, int leagueId, int playerId, String gameReport) 
        throws IDInvalidException, IllegalOperationException{
        
        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league and player exist
        if (tempLeague == null) {
            throw new IDInvalidException("Error: No league with this ID exists.");
        } else if (!doesPlayerExist(players, playerId)){
            throw new IDInvalidException("Error: No player with this ID exists.");
        }
        
        // Check that the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new IllegalOperationException("Error: Date is not valid for this league");
        }

        // Get the player being queried
        LeagueMember tempMember = tempLeague.getPlayerById(playerId);

        if (tempMember == null) {
            throw new IllegalOperationException("Error: That player is not in the league.");
        }

        // Get the players GameDay relating to that day
        GameDay tempDay = tempMember.getGameDayByDay(day);
        
        // If the GameDay does not exist add a new report
        if (tempDay == null){
            tempMember.addGameDay(day, gameReport);
        }
        // If the GameDay does exist then append the new sting onto the report
        else{
            tempDay.setGameReport(tempDay.getGameReport() + " " + gameReport);
        }

        if(tempLeague.getDayByDay(day).getStatus() == Status.PENDING){
            tempLeague.getDayByDay(day).setStatus(Status.IN_PROGRESS);
        }

        incrementRoundPlayed(playerId);

    };


    /** 
     * Get the game report for a player in a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * 
     * @return The game report for the player in the league on the day, or empty string if no report entry.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public String getGameReport(int day, int leagueId,  int playerId) 
        throws IDInvalidException, InvalidDateException{
        
        // Gets the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Checks that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: No league with that ID exists");
        } else if (!doesPlayerExist(players, playerId)) {
            throw new IDInvalidException("Error: No player with that ID exists");
        }

        // Checks that the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is not valid for this league");
        }

        // Get the player being queried
        LeagueMember tempMember = tempLeague.getPlayerById(playerId);

        if (tempMember == null) {
            throw new IllegalOperationException("Error: That player is not in the league.");
        }

        // Get the player's GameDay for that day
        GameDay tempDay = tempMember.getGameDayByDay(day);

        // If the GameDay does not exist return and empty string
        if (tempDay == null){
            return "";
        }

        return tempDay.getGameReport();
    };


    /**
     * Register day game scores. Will be called when all play in a round is complete.
     * with scores ordered to match player array returned by getLeaguePlayers().
     * 
     * @param day The epoch day the game was played.
     * @param leagueId The ID of the league being queried.
     * @param scores The game scores with order to match the array returned by getLeaguePlayers().
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalArgumentException If the day specified has already been closed,
     *                                  or if current date is 2 days or more after the day being registered.
     */
    public void registerDayScores(int day, int leagueId, int[] scores) 
        throws IDInvalidException, IllegalArgumentException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league");
        }

        // Find the current epoch date
        int currentDate = (int) DateProvider.now().toEpochDay();

        // Get the status of the league on that day
        LeagueDay dayStatus = tempLeague.getDayByDay(day);

        // Check that the league is not already closed and the day is not more than two days before the current date
        if (dayStatus.getStatus() == Status.CLOSED || (currentDate - day) >= 2){
            throw new IllegalArgumentException("Error: The day is not valid");
        }

        int[] playerIds = getLeaguePlayers(leagueId);

        // Loop though each player, set their score and calculate their points
        for (int i = 0; i < scores.length; i++){
            LeagueMember tempMember = tempLeague.getPlayerById(playerIds[i]);

            if(tempMember.getGameDayByDay(day) == null){
                tempMember.addGameDay(day, "");
            }

            tempMember.getGameDayByDay(day).setScore(scores[i]);
            // If the GameType is wordmaster then the points are the same as the scores
            if (tempLeague.getGameType() == GameType.WORDMASTER){
                tempMember.getGameDayByDay(day).setPoints(scores[i]);
            }
        }

        // If the GameType is diceroll then the player(s) with the lowest score get 3 points, the player(s) with the second lowest score get 1 point and anyone else gets nothing
        if (tempLeague.getGameType() == GameType.DICEROLL){
            // Sort the scores in descending order
            int[] orderedScores = bubbleSort(scores);

            int counter = 2;
            int first = -1;
            int second = -1;
            do{
                // Find the lowest score
                first = orderedScores[orderedScores.length-1];
                // Find the second lowest score that is not the same as the lowest score
                second = orderedScores[orderedScores.length-counter++];
            }while(first == second);

            // Loop though the list of scores
            for (int j = 0; j < scores.length; j++){
                // If a score is equal to the lowest score the player gets 3 points
                if (scores[j] == first){
                    tempLeague.getPlayerById(playerIds[j]).getGameDayByDay(day).setPoints(3);
                }
                // If the score is equal to the second lowest score then the player gets 1 point
                if (scores[j] == second){
                    tempLeague.getPlayerById(playerIds[j]).getGameDayByDay(day).setPoints(1);
                }
            }
        }



        tempLeague.incrementTotalRounds();
        dayStatus.setStatus(Status.CLOSED);
    };


    /**
     * Register a void day for a league - all points set to zero
     *
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalArgumentException If the day is not a valid day for the league,
     *                 or the current day is 2 days or more after the day being voided.
     */
    public void voidDayPoints(int day, int leagueId) 
        throws IDInvalidException, IllegalArgumentException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID is not valid");
        }

        // Find the current epoch date
        int currentDate = (int) DateProvider.now().toEpochDay();

        // Check if the day is valid for the league and it is not 2 or more days before the current date
        if (!tempLeague.isDayInLeague(day) || (currentDate - day) >= 2){
            throw new IllegalArgumentException("Error: The day is not valid");
        }

        int[] playerIds = getLeaguePlayers(leagueId);

        // Loop through each player and set their points for that day to 0
        for (int i = 0; i < playerIds.length; i++){
            LeagueMember tempMember = tempLeague.getPlayerById(playerIds[i]);

            if (tempMember.getGameDayByDay(day) == null){
                tempMember.addGameDay(day, "");
            }
            else{
                tempMember.getGameDayByDay(day).setPoints(0);
            }
        }
    };  


    /**
     * Get the status of league games for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        when all players have played or day has ended   
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public Status getDayStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range for this league");
        }

        return tempLeague.getDayByDay(day).getStatus();
    };


    /**
     * Get the scores of a round for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     *
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of registered score results for each player from the day 
     *         or empty array if no gameplay from any players yet.
     *         (where gameplay is in progress the returned scores will be 0)
     *          
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayScores(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{
        
        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range");
        }

        int[] scores = new int[tempLeague.getPlayers().size()];

        // Loop through each player, get their score for that day and add it to the array
        for (int i = 0; i < scores.length; i++){
            LeagueMember tempPlayer = tempLeague.getPlayerById(tempLeague.getPlayers().get(i).getPlayerId());
            if(tempPlayer.getGameDayByDay(day) != null){
                scores[i] = tempPlayer.getGameDayByDay(day).getScore();
            }
            else{
                scores[i] = 0;
            }
        }
        return scores;
    };


    /**
     * Get the league points of a league for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     *
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of the league points for each player from the day 
     *         or empty array if league points have not been finalised yet. 
     *          
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range");
        }

        int[] points = new int[tempLeague.getPlayers().size()];

        // Loop through each player, get their points for that day and add them to the array
        for (int i = 0; i < points.length; i++){
            LeagueMember tempPlayer = tempLeague.getPlayerById(tempLeague.getPlayers().get(i).getPlayerId());
            if(tempPlayer.getGameDayByDay(day) != null){
                points[i] = tempPlayer.getGameDayByDay(day).getPoints();
            }
            else{
                points[i] = 0;
            }
        }
        return points;
    };


    /**
     * Get the player rankings of a league for a given day, 
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return an array containing the rankings of the players
     *         or empty array if rankings not yet available.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check that the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range");
        }

        // Get the array of points for that day
        int[] points = getDayPoints(leagueId, day);

        // Return the ranks of the points
        return rank(points);
    };


    /**
     * Get the status of a league for a given week.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or week ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid week for the league.
     */
    public Status getWeekStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{
        
        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range for this league");
        }

        // Get the first valid day of the week
        int firstDay = getFirstValidDay(tempLeague, getFirstDayOfWeek(day), day);

        // Get the last valid day of the week
        int lastDay = getLastValidDay(tempLeague, getFirstDayOfWeek(day), day, 6);

        // If the first valid day is pending then the whole week must be pending
        if (tempLeague.getDayByDay(firstDay).getStatus() == Status.PENDING){
            return Status.PENDING;
        }
        // If the last valid day is closed then the whole week must be closed
        else if (tempLeague.getDayByDay(lastDay).getStatus() == Status.CLOSED){
            return Status.CLOSED;
        }
        // Anything in between must mean the week is in progress
        else{
            return Status.IN_PROGRESS;
        }
    };


    /**
     * Get the league points of a league for a given week,
     * with results ordered to match player array returned by getLeaguePlayers().
     *  
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return An array of the points of each players total for the week (or part week played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid week for the league.
     */
    public int[] getWeekPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is in the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range for this league");
        }

        // Get the first valid day of the week
        int firstDay = getFirstValidDay(tempLeague, getFirstDayOfWeek(day), day);
        
        // Return the points totals between the first valid day of the week and the current day
        return tempLeague.getTotalPoints(firstDay, day);
    };


    /**
     * Get the ranking of a league for a given week.
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return An array of the rankings of each players for the week (or part week played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException     If the day is not within a valid week for the league.
     */
    public int[] getWeekRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is out of range for this league");
        }

        // Get the first valid day of the week
        int firstDay = getFirstValidDay(tempLeague, getFirstDayOfWeek(day), day);
        // Get the total points between the first valid day of the week and the current day
        int[] weeklyPointsTotal = tempLeague.getTotalPoints(firstDay, day);

        // Return the ranks of the totals
        return rank(weeklyPointsTotal);
    };


    /**
     * Get the status of a league for a given month.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return The status of the month as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or month ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid month for the league.
     */
    public Status getMonthStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check that the day id valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is not valid for this league");
        }

        // Convert the epoch day into LocalDate type
        LocalDate date = LocalDate.ofEpochDay((long) day);

        // Get the first valid day of the month
        int firstDay = getFirstValidDay(tempLeague, date.getDayOfMonth()-1, day);

        // Get the last valid day of the month
        int lastDay = getLastValidDay(tempLeague, date.getDayOfMonth()-1, day, date.lengthOfMonth()-1);

        // If the first valid day of the month is pending then the whole month must be pending
        if (tempLeague.getDayByDay(firstDay).getStatus() == Status.PENDING){
            return Status.PENDING;
        }
        // If the last valid day of the month is closed then the whole month must be closed
        else if (tempLeague.getDayByDay(lastDay).getStatus() == Status.CLOSED){
            return Status.CLOSED;
        }
        // Anything in between must mean the month is in progress
        else{
            return Status.IN_PROGRESS;
        }
    };


    /**
     * Get the league points of a league for a given month.
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the points of each players total for the month (or part month played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid month for the league.
     */
    public int[] getMonthPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{
        
        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is not valid");
        }

        // Convert the epoch day into LocalDate type
        LocalDate date = LocalDate.ofEpochDay((long) day);
        // Get the first valid day of the month
        int firstDay = getFirstValidDay(tempLeague, date.getDayOfMonth()-1, day);

        // Return the array of totals from the first valid day of the month to the current day
        return tempLeague.getTotalPoints(firstDay, day);
    };


    /**
     * Get the ranking of a league for a given month.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the rankings of each players for the month (or part month played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException     If the day is not within a valid month for the league.
     */
    public int[] getMonthRanking(int leagueId, int day ) 
            throws IDInvalidException, InvalidDateException{

            // Get the league being queried
            League tempLeague = getLeagueIfExist(leagues, leagueId);

            // Check if league exists
            if (tempLeague == null){
                throw new IDInvalidException("Error: League ID does not match to an existing league.");
            }
    
            // Check if day is valid for the league
            if (!tempLeague.isDayInLeague(day)){
                throw new InvalidDateException("Error: Date is not valid");
            }
    
            // Convert the epoch day into LocalDate type
            LocalDate date = LocalDate.ofEpochDay((long) day);
            // Get the first valid day of the month
            int firstDay = getFirstValidDay(tempLeague, date.getDayOfMonth()-1, day);
    
            // Get the array of totals from the first valid day of the month to the current day
            int[] totals =  tempLeague.getTotalPoints(firstDay, day);

            // Return the array of ranks
            return rank(totals);
    };


    /**
     * Get the status of a league for a given year.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or year has ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid year for the league.
     */
    public Status getYearStatus(int leagueId, int day ) 
            throws IDInvalidException, InvalidDateException{

            // Get the league being queried
            League tempLeague = getLeagueIfExist(leagues, leagueId);

            // Check if the league exists
            if (tempLeague == null){
                throw new IDInvalidException("Error: League ID does not match to an existing league.");
            }
    
            // Check that the day is valid for the league
            if (!tempLeague.isDayInLeague(day)){
                throw new InvalidDateException("Error: Date is not valid for this league");
            }
    
            // Convert the epoch day to LocalDate type
            LocalDate date = LocalDate.ofEpochDay((long) day);
    
            // Get the first valid day of the year
            int firstDay = getFirstValidDay(tempLeague, date.getDayOfYear()-1, day);
    
            // Get the last valid day of the year
            int lastDay = getLastValidDay(tempLeague, date.getDayOfYear()-1, day, date.lengthOfYear()-1);
    
            // If the status of the first day is pending then the whole year must be pending
            if (tempLeague.getDayByDay(firstDay).getStatus() == Status.PENDING){
                return Status.PENDING;
            }
            // If the status of the last day is closed then the whole year must be closed 
            else if (tempLeague.getDayByDay(lastDay).getStatus() == Status.CLOSED){
                return Status.CLOSED;
            }
            // Anything in between means the year is in progress
            else{
                return Status.IN_PROGRESS;
            }
    };


    /**
     * Get the league points of a league for a given year.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return An array of the league points of each players total for the year (or part year played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid month for the league.
     */
    public int[] getYearPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check if the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check if the day is in the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is not valid");
        }

        // Convert the epoch day to LocalDate type
        LocalDate date = LocalDate.ofEpochDay((long) day);
        // Get the first valid day of the year
        int firstDay = getFirstValidDay(tempLeague, date.getDayOfYear()-1, day);

        // Return the array of totals from the first valid day of the year to the current day
        return tempLeague.getTotalPoints(firstDay, day);
    };


    /**
     * Get the ranking of a league for a given year.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return An array of the rankings of each players for the year (or part year played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException   If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid year for the league.
     **/
    public int[] getYearRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        // Get the league being queried
        League tempLeague = getLeagueIfExist(leagues, leagueId);

        // Check that the league exists
        if (tempLeague == null){
            throw new IDInvalidException("Error: League ID does not match to an existing league.");
        }

        // Check that the day is valid for the league
        if (!tempLeague.isDayInLeague(day)){
            throw new InvalidDateException("Error: Date is not valid");
        }

        // Convert the epoch day to LocalDate type
        LocalDate date = LocalDate.ofEpochDay((long) day);
        // Get the first valid day of the year
        int firstDay = getFirstValidDay(tempLeague, date.getDayOfYear()-1, day);

        // Get the array of total points from the first valid day of the year to the current day
        int[] totals =  tempLeague.getTotalPoints(firstDay, day);

        // Return the ranked totals
        return rank(totals);
    };


    /**
     * Method empties this GamesLeague instance of its contents and resets all
     * internal counters.
     */
    public void eraseGamesLeagueData(){
        players.clear();
        leagues.clear();
    };


    /**
     * Method saves this GamesLeague instance contents into a serialised file,
     * with the filename given in the argument.
     * <p>
     * The state of this GamesLeague instance must be unchanged if any
     * exceptions are thrown.
     *
     * @param filename Location of the file to be saved.
     * @throws IOException If there is a problem experienced when trying to save the 
     *                     contents to the file.
     */
    public void saveGamesLeagueData(String filename) throws IOException{
        
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        // Convert the players ArrayList to an array to simplify serialisation
        Player[] tempPlayers = players.toArray(new Player[players.size()]);
        out.writeObject(tempPlayers);
        // Convert the leagues ArrayList to an array to simplify serialisation
        League[] tempLeagues = leagues.toArray(new League[leagues.size()]);
        out.writeObject(tempLeagues);
        out.close();
    };


    /**
     * Method should load and replace this GamesLeague instance contents with the
     * serialised contents stored in the file given in the argument.
     *
     * @param filename Location of the file to be loaded.
     * @throws IOException If there is a problem experienced when trying
     *                     to load the store contents from the file.
     * @throws ClassNotFoundException If required class files cannot be found when loading.
     */
    public void loadGamesLeagueData(String filename) throws IOException, ClassNotFoundException{

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        // Create new temporary arrays to store the incoming data
        Player[] tempPlayers = null;
        League[] tempLeagues = null;
        // Read in the first object
        Object obj = in.readObject();
        // Check that the object is an array of players
        if (obj instanceof Player[]){
            // Cast the object variable to the temporary array
            tempPlayers = (Player[]) obj;
        }
        // Read in the next object
        obj = in.readObject();
        // Check that the object is an array of leagues
        if (obj instanceof League[]){
            // Cast the object variable to the temporary array
            tempLeagues = (League[]) obj;
        }

        // Add each element of the player array to the main players ArrayList
        if (tempPlayers != null){
            players.clear();
            for (Player p : tempPlayers){
                players.add(p);
            }
        }

        // Add each element of the league array to the main leagues ArrayList
        if (tempLeagues != null){
            leagues.clear();
            for (League l : tempLeagues){
                leagues.add(l);
            }
        }

        in.close();
    };

    /**
     * This performs a bubble sort on a given array. Sorts the array in descending order.
     * 
     * @param tempArray The primitive integer array to be sorted.
     * @return The sorted array.
     */
    public int[] bubbleSort(int[] tempArray){
        boolean swap = false;

        int[] array = tempArray.clone();

        int temp = 0;
        for (int i = 0; i < array.length; i++){
            // Each pass is one element shorter than the last because we know the last element must be in the correct place
            for (int j = 1; j < array.length - i; j++){
                // If the previous element is less than the current element then they should be swapped
                if (array[j-1] < array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                    swap = true;
                }
            }
            // If the sort completes a whole pass without swapping then the array is already sorted and can be returned
            if (!swap) {
                return array;
            } else {
                swap = false;
            }
        }
        return array;
    }

    /**
     * This calculates which day of the week an epoch day is. 0 is Monday up to 6 for Sunday
     * 
     * @param day The epoch day to be checked
     * @return The integer day of the week
     */
    public int getFirstDayOfWeek(int day){
        int dayOfWeek = day % 7;
        int firstDay = -1;
        // The first epoch day was a thursday so if the dayOfWeek variable is 0 then the day is thursday
        // This switch statement changes the numbers so 0 refers to monday, which is more intuitive
        switch(dayOfWeek){
            case 0:
                firstDay = 3;
                break;
            case 1:
                firstDay = 4;
                break;
            case 2:
                firstDay = 5;
                break;
            case 3:
                firstDay = 6;
                break;
            case 4:
                firstDay = 0;
                break;
            case 5:
                firstDay = 1;
                break;
            case 6:
                firstDay = 2;
                break;
        }
        return firstDay;
    }

    /**
     * When given a day that is either within a week, month, or year this will find the first
     * day within the respective week, month, or year that is valid for the league. This helps
     * solve the problem of a league starting half way through a week, month, or year.
     * 
     * @param tempLeague The league to be checked
     * @param day The day within the week, month, or year, e.g for the 21st day of the month day = 20
     * since we start counting from 0
     * @param fullDay This is the full epoch day of the day being entered, e.g the epoch day for the 21st of the
     * month in that year 
     * @return The full epoch day of the first valid day for the before the entered day
     */
    public int getFirstValidDay(League tempLeague, int day, int fullDay){
        int validDay = -1;
        // Loop from the current day in the week, month, or year down to the first day (0)
        for (int i = 0; i <= day; i++){
            // Check if the current day is in the league
            if (!tempLeague.isDayInLeague(fullDay - i)){
                // If the current day is not in the league then the previously checked day must be the first valid day
                // This calculates the epoch day for the first valid day
                validDay = fullDay - (i - 1);
                break;
            }
        }
        return validDay;
    }

    /**
     * When given a day that is either within a week, month, or year this will find the last
     * day within the respective week, month, or year that is valid for the league. This helps
     * solve the problem of a league ending half way through a week, month, or year.
     * 
     * @param tempLeague The league to be checked
     * @param day The day within the week, month, or year, e.g for the 21st day of the month day = 20
     * since we start counting from 0
     * @param fullDay This is the full epoch day of the day being entered, e.g the epoch day for the 21st of the
     * month in that year 
     * @param length The length of the week, month, or year minus 1 since we start counting from 0
     * @return The full epoch day of the last valid day for the before the entered day
     */
    public int getLastValidDay(League tempLeague, int day, int fullDay, int length){
        int validDay = -1;
        // Loop up to the end of the week, month, or year
        for (int i = 0; i <= length; i++){
            // Check if the day is in the league
            if (!tempLeague.isDayInLeague(fullDay + i)){
                // If the day is not in the league then the previous day is the last valid day of the league
                // This calculates the epoch day for the last valid day
                validDay = fullDay + (i-1);
                break;
            }
        }
        return validDay;
    }

    /**
     * This ranks a given integer array in descending order and then reorders the rankings to be
     * consistent with the original array, e.g an array of [23, 10, 23, 50, 15] would return rankings
     * of [2, 5, 2, 1, 4]
     * 
     * @param toRank The array to be ranked
     * @return The correctly ordered array of rankings
     */
    public int[] rank(int[] toRank){
        // Sort the array in descending order
        int[] rankings = bubbleSort(toRank);
        // New array for storing rankings in the correct order
        int[] rankingsOrdered = new int[rankings.length];

        int prev = -1;
        int prevRank = -1;

        // This loop locates where each element in the original list has ended up in the ordered list
        for (int i = 0; i < rankings.length; i++){
            for (int j = 0; j < rankings.length; j++){
                // If the value in the original list is found in the ordered list
                if (rankings[j] == toRank[i]){
                    // Check if the element to rank is the same as the last ranked element
                    if (rankings[j] == prev){
                        // This indicates a tie, so the rank is the same as the last rank 
                        rankingsOrdered[i] = prevRank;
                    }
                    else{
                        // If it is not a tie then the index of the element in the ordered list is used to calculate the rank
                        rankingsOrdered[i] = j+1; 
                    }
                    // These refer to the previous points that were ranked and the rank that they were given
                    prev = rankings[j];
                    prevRank = rankingsOrdered[i];
                }
            }
        }

        return rankingsOrdered;
    }
}
