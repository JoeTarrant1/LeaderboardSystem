import gamesleague.*;

import java.time.LocalDate;
import java.util.Arrays;

/*
 * javac -d ./bin ./src/gamesleague/*.java
 * javac -cp bin ./TestSystem/TestLeagueApp.java
 * java -cp bin:TestSystem TestLeagueApp 
 */


public class TestLeagueApp  {
    public static void main(String[] args) {
        System.out.println("Testing GamesLeague methods for League management.");
        GamesLeague gl = new GamesLeague();

        System.out.println("Getting players IDs, should return an empty array.");
        System.out.println("Player list: " + Arrays.toString(gl.getPlayerIds()));

        System.out.println("Getting league IDs, should return an empty array.");
        System.out.println("League list: " + Arrays.toString(gl.getLeagueIds()));

        System.out.println("\nMaking players");
        int playerId0 = gl.createPlayer("sc1448@exeter.ac.uk", "s.carp", "Samuel", "999");
        int playerId1 = gl.createPlayer("thebigmanhimself@google.co.uk", "big.cheese", "Lord Business", "001");
        int playerId2 = gl.createPlayer("imaginary@friend.com", "imag.friend", "Imaginary Friend", "(505) 503-4455");
        System.out.println("Players made.\n");

        System.out.println("Making leagues");
        int leagueId0 = gl.createLeague(playerId0, "Ultimate League", GameType.DICEROLL);
        System.out.println("Diceroll league made.");
        int leagueId1 = gl.createLeague(playerId1, "Business League", GameType.WORDMASTER);
        System.out.println("Wordmaster league made.\n");

        System.out.println("Getting league ID");
        System.out.println("League list: " + Arrays.toString(gl.getLeagueIds()));
        System.out.println("\nGetting league name");
        System.out.println(gl.getLeagueName(leagueId0));

        System.out.println("The following arrays should be empty: ");
        System.out.println(gl.getLeagueEmailInvites(leagueId0));
        System.out.println(gl.getLeaguePlayerInvites(leagueId0));
        System.out.println(gl.getPlayerInvites(playerId0));
        System.out.println(gl.getLeaguePlayers(leagueId0));
        System.out.println(gl.getPlayerLeagues(playerId0));

        System.out.println("\nTesting exception for getLeagueName");
        try {
            System.out.println(gl.getLeagueName(-9999999));
            System.out.println("ERROR: You can get the name of a non-existent league.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for createLeague");
        try {
            int leagueId3 = gl.createLeague(-999999, "Cool League", GameType.WORDMASTER);
            System.out.println("ERROR: Can create a league with an owner that doesn't exist!");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        
        try {
            int leagueId3 = gl.createLeague(playerId0, null, GameType.WORDMASTER);
            System.out.println("ERROR: The league name can be null.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId3 = gl.createLeague(playerId0, " Cool League", GameType.WORDMASTER);
            System.out.println("ERROR: The league name can begin with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId3 = gl.createLeague(playerId0, "Cool League ", GameType.WORDMASTER);
            System.out.println("ERROR: The league name can end with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId3 = gl.createLeague(playerId0, "", GameType.WORDMASTER);
            System.out.println("ERROR: The league name can be less than 1 character.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId3 = gl.createLeague(playerId0, "123456789012345678901", GameType.WORDMASTER);
            System.out.println("ERROR: The league name can be more than 20 characters.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}

        try {
            int leagueId3 = gl.createLeague(playerId0, "Ultimate League", GameType.WORDMASTER);
            System.out.println("ERROR: The league can have the same name as another league.");
        } catch (IllegalNameException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting updateLeagueName");
        System.out.println(gl.getLeagueName(leagueId0));
        gl.updateLeagueName(leagueId0, "New League!");
        System.out.println(gl.getLeagueName(leagueId0));


        System.out.println("\nTesting exceptions for updateLeagueName");
        try {
            gl.updateLeagueName(-99999, "Cool League");
            System.out.println("ERROR: Can update the name of a league that doesn't exist!");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        
        try {
            gl.updateLeagueName(leagueId0, null);
            System.out.println("ERROR: The league name can be null.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updateLeagueName(leagueId0, " Cool League");
            System.out.println("ERROR: The league name can begin with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updateLeagueName(leagueId0, "Cool League ");
            System.out.println("ERROR: The league name can end with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updateLeagueName(leagueId0, "");
            System.out.println("ERROR: The league name can be less than 1 character.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updateLeagueName(leagueId0, "123456789012345678901");
            System.out.println("ERROR: The league name can be more than 20 characters.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}

        try {
            gl.updateLeagueName(leagueId0, "Business League");
            System.out.println("ERROR: The league can have the same name as another league.");
        } catch (IllegalNameException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nInviting player to league");
        gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId1));
        System.out.println("Player invited, testing getters");
        System.out.println("League Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId0)));
        System.out.println("League Player Invites: " + Arrays.toString(gl.getLeaguePlayerInvites(leagueId0)));
        System.out.println("Player Invites to Leagues: " + Arrays.toString(gl.getPlayerInvites(playerId1)));
        System.out.println("\nRemoving invites from league then printing invite getters");
        gl.removeInviteFromLeague(leagueId0, gl.getPlayerEmail(playerId1));
        System.out.println("League Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId0)));
        System.out.println("League Player Invites: " + Arrays.toString(gl.getLeaguePlayerInvites(leagueId0)));
        System.out.println("Player Invites to Leagues: " + Arrays.toString(gl.getPlayerInvites(playerId1)));
        System.out.println("\nInviting player then accepting");
        gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId1));
        gl.acceptInviteToLeague(leagueId0, playerId1);
        System.out.println("Invite sent and accepted");
        System.out.println("Players in league: " + Arrays.toString(gl.getLeaguePlayers(leagueId0)));
        System.out.println("Leagues the player is in: " + Arrays.toString(gl.getPlayerLeagues(playerId1)));
        System.out.println("Player join date: " + gl.getPlayerJoinDate(playerId1));

        System.out.println("\nTesting exceptions for invitePlayerToLeague");
        try {
            gl.invitePlayerToLeague(-99999, "imaginary@friend.com");
            System.out.println("ERROR: Can invite to a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.invitePlayerToLeague(leagueId0, "fakefriend@evil.com");
            System.out.println("ERROR: Can invite a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        try {
            gl.invitePlayerToLeague(leagueId0, null);
            System.out.println("ERROR: Can invite a null email");
        } catch (InvalidEmailException ex) {System.out.println(ex.getMessage());}
        try {
            gl.invitePlayerToLeague(leagueId0, "");
            System.out.println("ERROR: Can invite an empty email");
        } catch (InvalidEmailException ex) {System.out.println(ex.getMessage());}
        try {
            gl.invitePlayerToLeague(leagueId0, "freaky-email");
            System.out.println("ERROR: Can invite an email without an @");
        } catch (InvalidEmailException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for getLeagueEmailInvites");
        try {
            gl.getLeagueEmailInvites(-9999);
            System.out.println("ERROR: Can get the invites from a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for getLeaguePlayerInvites");
        try {
            gl.getLeaguePlayerInvites(-9999);
            System.out.println("ERROR: Can get the invites from a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for getPlayerInvites");
        try {
            gl.getPlayerInvites(-9999);
            System.out.println("ERROR: Can get the invites for a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for acceptInviteToLeague");
        try {
            gl.acceptInviteToLeague(leagueId0, -99999);
            System.out.println("ERROR: Can accept an invite from an account that doesn't exist.");
        }   catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.acceptInviteToLeague(-99999999, playerId2);
            System.out.println("ERROR: Can accept an invite from a league that doesn't exist.");
        }   catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.acceptInviteToLeague(leagueId0, playerId2);
            System.out.println("ERROR: Can accept an invite that doesn't exist.");
        }   catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for removeInviteFromLeague");
        try {
            gl.removeInviteFromLeague(leagueId0, "fake-friend@evil.com");
            System.out.println("ERROR: Can remove an invite from an account that doesn't exist.");
        }   catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.removeInviteFromLeague(-99999999, "imaginary@friend.com");
            System.out.println("ERROR: Can remove an invite from a league that doesn't exist.");
        }   catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.removeInviteFromLeague(leagueId0, "imaginary@friend.com");
            System.out.println("ERROR: Can remove an invite that doesn't exist.");
        }   catch (IllegalEmailException ex) {System.out.println(ex.getMessage());}


        System.out.println("\nTesting exceptions for getLeaguePlayers");
        try {
            gl.getLeaguePlayers(-9999);
            System.out.println("ERROR: Can get the players in a league for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for getPlayerLeagues");
        try {
            gl.getPlayerLeagues(-9999);
            System.out.println("ERROR: Can get the leagues a player is in for a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting exceptions for getPlayerJoinDate");
        try {
            gl.getPlayerJoinDate(-9999);
            System.out.println("ERROR: Can get the join date of a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId2));
        gl.acceptInviteToLeague(leagueId0, playerId2);
        System.out.println("\nIs player deactivated? " + gl.isDeactivatedPlayer(playerId2));
        System.out.println("Is player active in their league? " + gl.isLeaguePlayerActive(leagueId0, playerId2));
        System.out.println("Deactivating Player");
        gl.deactivatePlayer(playerId2);
        System.out.println("Is player deactivated? " + gl.isDeactivatedPlayer(playerId2));
        System.out.println("Display Name: " + gl.getPlayerDisplayName(playerId2));
        System.out.println("Email: " + gl.getPlayerEmail(playerId2));
        System.out.println("Is player active in their league? " + gl.isLeaguePlayerActive(leagueId0, playerId2));

        System.out.println("\nTesting deactivatePlayer Exceptions");
        try {
            gl.deactivatePlayer(-999999);
            System.out.println("ERROR: Can deactivate a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.deactivatePlayer(playerId0);
            System.out.println("ERROR: Can deactivate a player that is the sole owner of a league.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting isDeactivatedPlayer Exceptions");
        try {
            gl.isDeactivatedPlayer(-999999);
            System.out.println("ERROR: Can check deactivation status of a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting isLeaguePlayerActive Exceptions");
        try {
            gl.isLeaguePlayerActive(leagueId0, -999999);
            System.out.println("ERROR: Can check whether a player that doesn't exist is in the league.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.isLeaguePlayerActive(leagueId1, playerId1);
            System.out.println("ERROR: Can check whether a player that isn't in the league is active");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.isLeaguePlayerActive(-9999999, playerId2);
            System.out.println("ERROR: Can check status of player in a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\n\nGetting league start and end day");
        System.out.println("League Start Date: " + gl.getLeagueStartDate(leagueId0));
        System.out.println("League Close Date: " + gl.getLeagueCloseDate(leagueId0));   
        System.out.println("League Status: " + gl.getLeagueStatus(leagueId0));     

        System.out.println("\nSetting league status to in-progress and then printing getters");
        gl.setLeagueStartDate(leagueId0, (int) DateProvider.now().toEpochDay() - 1);
        gl.setLeagueEndDate(leagueId0, 999999999);
        System.out.println("League Start Date: " + gl.getLeagueStartDate(leagueId0));
        System.out.println("League Close Date: " + gl.getLeagueCloseDate(leagueId0));   
        System.out.println("League Status: " + gl.getLeagueStatus(leagueId0));

        System.out.println("\nSetting league status to closed and then printing getters");
        gl.setLeagueEndDate(leagueId0, (int) DateProvider.now().toEpochDay());
        System.out.println("League Start Date: " + gl.getLeagueStartDate(leagueId0));
        System.out.println("League Close Date: " + gl.getLeagueCloseDate(leagueId0));   
        System.out.println("League Status: " + gl.getLeagueStatus(leagueId0));

        System.out.println("\nTesting getLeagueStatus Exceptions");
        try {
            gl.getLeagueStatus(-9999);
            System.out.println("ERROR: Can get the status of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting getLeagueStartDate Exceptions");
        try {
            gl.getLeagueStartDate(-9999);
            System.out.println("ERROR: Can get the start date ofa. league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting getLeagueCloseDate Exceptions");
        try {
            gl.getLeagueCloseDate(-9999);
            System.out.println("ERROR: Can get the end date of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting setLeagueStartDate Exceptions");
        try {
            gl.setLeagueStartDate(-9999, 0);
            System.out.println("ERROR: Can change the start date of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeagueStartDate(leagueId1, -99999);
            System.out.println("ERROR: Can change start date of league to invalid epoch day.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeagueStartDate(leagueId1, 10);
            gl.setLeagueStartDate(leagueId1, 0);
            System.out.println("ERROR: Can change the start date of a league that has already started.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        
        System.out.println("\nTesting setLeagueEndDate Exceptions");
        try {
            gl.setLeagueEndDate(-9999, 0);
            System.out.println("ERROR: Can change the start date of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeagueEndDate(leagueId1, -9999);
            System.out.println("ERROR: Can change the end date of the league to an invalid epoch day.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeagueEndDate(leagueId1, 10);
            System.out.println("ERROR: Can change the end date of the league to a day before the start day.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeagueEndDate(leagueId1, 100);
            gl.setLeagueEndDate(leagueId1, 150);
            System.out.println("ERROR: Can change the end date of a league that has already ended.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\n\nTesting League Player Active");
        gl.invitePlayerToLeague(leagueId1, gl.getPlayerEmail(playerId0));
        gl.acceptInviteToLeague(leagueId1, playerId0);
        System.out.println("Is league player active? " + gl.isLeaguePlayerActive(leagueId1, playerId0));
        System.out.println("Setting league player to inactive.");
        gl.setLeaguePlayerInactive(leagueId1, playerId0);
        System.out.println("Is league player active? " + gl.isLeaguePlayerActive(leagueId1, playerId0));
        System.out.println("Setting league player back to active.");
        gl.setLeaguePlayerActive(leagueId1, playerId0);
        System.out.println("Is league player active? " + gl.isLeaguePlayerActive(leagueId1, playerId0));
        System.out.println("Status of deactivated league player: " + gl.isLeaguePlayerActive(leagueId0, playerId2));

        System.out.println("\nTesting setLeaguePlayerActive Exceptions");
        try {
            gl.setLeaguePlayerActive(leagueId1, -99999);
            System.out.println("ERROR: Can change a player that doesn't exist to active.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeaguePlayerActive(-99999, playerId0);
            System.out.println("ERROR: Can change a player to active in a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeaguePlayerActive(leagueId1, playerId2);
            System.out.println("ERROR: Can set a player to active in a league they don't belong to.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting setLeaguePlayerInactive Exceptions");
        try {
            gl.setLeaguePlayerInactive(leagueId1, -99999);
            System.out.println("ERROR: Can change a player that doesn't exist to inactive.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeaguePlayerInactive(-99999, playerId0);
            System.out.println("ERROR: Can change a player to inactive in a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.setLeaguePlayerInactive(leagueId1, playerId2);
            System.out.println("ERROR: Can set a player to inactive in a league they don't belong to.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\n\nCreating 3 new players, and a new league.");
        int playerId3 = gl.createPlayer("person3@example.com", "person3", "Person 1", "123");
        int playerId4 = gl.createPlayer("person4@example.com", "person4", "Person 2", "456");
        int playerId5 = gl.createPlayer("person5@example.com", "person5", "Person 3", "789");
        int leagueId2 = gl.createLeague(playerId3, "Test League", GameType.WORDMASTER);

        System.out.println("\nGetting League Owners");
        System.out.println("League Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId2)));
        System.out.println("Adding New League Owner");
        gl.invitePlayerToLeague(leagueId2, gl.getPlayerEmail(playerId4));
        gl.acceptInviteToLeague(leagueId2, playerId4);
        gl.addOwner(leagueId2, playerId4);
        System.out.println("Getting League Owners");
        System.out.println("League Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId2)));
        System.out.println("Leagues Owned by Player 3: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId3)));
        System.out.println("Leagues Owned by Player 4: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId4)));
        System.out.println("Removing Original Owner then Printing Getters");
        gl.removeOwner(leagueId2, playerId3);
        System.out.println("League Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId2)));
        System.out.println("Leagues Owned by Player 3: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId3)));
        System.out.println("Leagues Owned by Player 4: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId4)));

        System.out.println("\nTesting addOwner Exceptions");
        try {
            gl.addOwner(-99999, playerId3);
            System.out.println("ERROR: Can add a player as an owner in a class that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.addOwner(leagueId2, -9999);
            System.out.println("ERROR: Can add a non-existent player as an owner into the class.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.addOwner(leagueId2, playerId5);
            System.out.println("ERROR: You can add a player as an owner to a class they aren't in.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.addOwner(leagueId2, playerId4);
            System.out.println("ERROR: You can add a player as an owner, even though they are already an owner.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting getLeagueOwners Exceptions");
        try {
            gl.getLeagueOwners(-9999);
            System.out.println("ERROR: You can get the owners of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting getPlayerOwnedLeagues Exceptions");
        try {
            gl.getPlayerOwnedLeagues(-9999);
            System.out.println("ERROR: You can get the player owned leagues of a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting removeOwner Exceptions");
        gl.addOwner(leagueId2, playerId3);
        try {
            gl.removeOwner(-99999, playerId4);
            System.out.println("ERROR: Can remove the owner of a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.removeOwner(leagueId2, -99999);
            System.out.println("ERROR: Can remove a player as owner that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.removeOwner(leagueId2, playerId5);
            System.out.println("ERROR: Can remove a player that isn't even the owner of the league.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.removeOwner(leagueId2, playerId3);
            gl.removeOwner(leagueId2, playerId4);
            System.out.println("ERROR: Can remove the sole-owner of a league.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\n\nAdding extra attributes to league");
        gl.setLeagueEndDate(leagueId2, 100000000);
        gl.setLeagueStartDate(leagueId2, 100);
        gl.invitePlayerToLeague(leagueId2, gl.getPlayerEmail(playerId5));
        gl.acceptInviteToLeague(leagueId2, playerId5);
        System.out.println("Cloning the League");
        int leagueId3 = gl.cloneLeague(leagueId2, "Clone League");
        System.out.println("League Cloned");
        System.out.println("League Name: " + gl.getLeagueName(leagueId3));
        System.out.println("League Start Date: " + gl.getLeagueStartDate(leagueId3));
        System.out.println("League End Date: " + gl.getLeagueCloseDate(leagueId3));
        System.out.println("League Status: " + gl.getLeagueStatus(leagueId3));
        System.out.println("\nLeagueId2 Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId2)));
        System.out.println("LeagueId2 Players: " + Arrays.toString(gl.getLeaguePlayers(leagueId2)));
        System.out.println("LeagueId2 Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId2)));
        System.out.println("\nLeagueId3 Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId3)));
        System.out.println("LeagueId3 Players: " + Arrays.toString(gl.getLeaguePlayers(leagueId3)));
        System.out.println("LeagueId3 Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId3)));
        System.out.println("\nLeagues player3 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId3)));
        System.out.println("Leagues player3 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId3)));
        System.out.println("Leagues player3 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId3)));
        System.out.println("\nLeagues player4 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId4)));
        System.out.println("Leagues player4 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId4)));
        System.out.println("Leagues player4 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId4)));
        System.out.println("\nLeagues player5 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId5)));
        System.out.println("Leagues player5 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId5)));
        System.out.println("Leagues player5 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId5)));

        gl.acceptInviteToLeague(leagueId3, playerId5);
        System.out.println("\n\nRemoving League");
        gl.removeLeague(leagueId2);
        System.out.println("\nLeagues player3 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId3)));
        System.out.println("Leagues player3 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId3)));
        System.out.println("Leagues player3 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId3)));
        System.out.println("\nLeagues player4 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId4)));
        System.out.println("Leagues player4 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId4)));
        System.out.println("Leagues player4 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId4)));
        System.out.println("\nLeagues player5 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId5)));
        System.out.println("Leagues player5 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId5)));
        System.out.println("Leagues player5 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId5)));

        System.out.println("\n\nCloning New League to Get the Old League Back Again");
        leagueId2 = gl.cloneLeague(leagueId3, "Testing League");
        System.out.println("League Cloned");
        System.out.println("League Name: " + gl.getLeagueName(leagueId3));
        System.out.println("League Start Date: " + gl.getLeagueStartDate(leagueId3));
        System.out.println("League End Date: " + gl.getLeagueCloseDate(leagueId3));
        System.out.println("League Status: " + gl.getLeagueStatus(leagueId3));
        System.out.println("\nLeagueId2 Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId2)));
        System.out.println("LeagueId2 Players: " + Arrays.toString(gl.getLeaguePlayers(leagueId2)));
        System.out.println("LeagueId2 Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId2)));
        System.out.println("\nLeagueId3 Email Invites: " + Arrays.toString(gl.getLeagueEmailInvites(leagueId3)));
        System.out.println("LeagueId3 Players: " + Arrays.toString(gl.getLeaguePlayers(leagueId3)));
        System.out.println("LeagueId3 Owners: " + Arrays.toString(gl.getLeagueOwners(leagueId3)));
        System.out.println("\nLeagues player3 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId3)));
        System.out.println("Leagues player3 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId3)));
        System.out.println("Leagues player3 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId3)));
        System.out.println("\nLeagues player4 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId4)));
        System.out.println("Leagues player4 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId4)));
        System.out.println("Leagues player4 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId4)));
        System.out.println("\nLeagues player5 Owns: " + Arrays.toString(gl.getPlayerOwnedLeagues(playerId5)));
        System.out.println("Leagues player5 Resides In: " + Arrays.toString(gl.getPlayerLeagues(playerId5)));
        System.out.println("Leagues player5 Invites: " + Arrays.toString(gl.getPlayerInvites(playerId5)));

        System.out.println("\n\nCreating New League, then Resetting it");
        int leagueId5 = gl.createLeague(playerId5, "Test League", GameType.WORDMASTER);
        gl.invitePlayerToLeague(leagueId5, gl.getPlayerEmail(playerId3));
        gl.invitePlayerToLeague(leagueId5, gl.getPlayerEmail(playerId4));
        gl.acceptInviteToLeague(leagueId5, playerId4);
        gl.addOwner(leagueId5, playerId4);
        gl.removeOwner(leagueId5, playerId5);
        System.out.println("League Created");
        gl.setLeaguePlayerInactive(leagueId5, playerId5);
        System.out.println("Resetting League");
        gl.resetLeague(leagueId5);
        System.out.println("League Reset");
        if (gl.isLeaguePlayerActive(leagueId5, playerId5)) {
            System.out.println("ERROR: Player that was inactive before league reset, is now active again!");
        }
        if (!gl.isLeaguePlayerActive(leagueId5, playerId4)) {
            System.out.println("ERROR: Player that was active before league reset, is now inactive.");
        }

        System.out.println("\n\nTesting cloneLeague Exceptions");
        try {
            int leagueId6 = gl.cloneLeague(-9999, "Mystery League");
            System.out.println("ERROR: Can clone a league with a league that doesn't exist!");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        
        try {
            int leagueId6 = gl.cloneLeague(leagueId5, null);
            System.out.println("ERROR: The clone league name can be null.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId6 = gl.cloneLeague(leagueId5, " Mystery League");
            System.out.println("ERROR: The clone league name can begin with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId6 = gl.cloneLeague(leagueId5, "Mystery League ");
            System.out.println("ERROR: The clone league name can end with whitespace.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId6 = gl.cloneLeague(leagueId5, "");
            System.out.println("ERROR: The clone league name can be less than 1 character.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int leagueId6 = gl.cloneLeague(leagueId5, "123456789012345678901");
            System.out.println("ERROR: The clone league name can be more than 20 characters.");
        } catch (InvalidNameException ex) {System.out.println(ex.getMessage());}

        try {
            int leagueId6 = gl.cloneLeague(leagueId5, "New League!");
            System.out.println("ERROR: The clone league can have the same name as another league.");
        } catch (IllegalNameException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for resetLeague");
        try {
            gl.resetLeague(-99999);
            System.out.println("ERROR: Can reset a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for removeLeague");
        try {
            gl.removeLeague(-99999);
            System.out.println("ERROR: Can remove a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
    }
}
