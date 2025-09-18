import gamesleague.*;
//import src.gamesleague.IllegalEmailException.java;
import java.util.Arrays;

/*
 * javac -d ./bin ./src/gamesleague/*.java
 * javac -cp bin ./TestSystem/TestPlayerApp.java
 * java -cp bin:TestSystem TestPlayerApp 
 */

public class TestPlayerApp  {
    public static void main(String[] args) {
        System.out.println("Testing GamesLeague methods for player management");
        GamesLeague gl = new GamesLeague();

        System.out.println("Making player 1.");
        int playerId1 = gl.createPlayer("sc1448@exeter.ac.uk", "s.carp", "Samuel", "999");
        System.out.println("Player 1 successfully made, making player 2.");
        int playerId2 = gl.createPlayer("thebigmanhimself@google.co.uk", "big.cheese", "Lord Business", "001");
        System.out.println("Player 2 successfully made, now testing getters.\n");

        System.out.println("Getting and printing player Ids.");
        System.out.println("Player list: " + Arrays.toString(gl.getPlayerIds()));


        System.out.println("Getting and printing playerId, email and display name.");
        System.out.print(gl.getPlayerId("sc1448@exeter.ac.uk") + ", ");
        System.out.print(gl.getPlayerEmail(playerId1) + ", ");
        System.out.println(gl.getPlayerDisplayName(playerId1) + "\n");


        System.out.println("Updating, getting, and then printing display name.");
        System.out.println(gl.getPlayerDisplayName(playerId2));
        gl.updatePlayerDisplayName(playerId2, "massive.cheese");
        System.out.println(gl.getPlayerDisplayName(playerId2));

        System.out.println("\nTesting edge cases and exceptions");
        System.out.println("\nTesting name parameter in createPlayer");
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123", null, "123 456 789");
            System.out.println("Name can be null.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123", " Fake Person", "123 456 789");
            System.out.println("Name can start with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123", "Fake Person ", "123 456 789");
            System.out.println("Name can end with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123", "Fake", "123 456 789");
            System.out.println("Name can be shorter than 5 characters when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123", "FakeFakeFakeFakeFakeFakeFakeFakeFakeFakeFakeFake123", "123 456 789");
            System.out.println("Name can be longer than 50 characters when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting display name parameter in createPlayer");
        try {
            int playerId3 = gl.createPlayer("test@example.com", null, "Fake Person", "123 456 789");
            System.out.println("displayName can be null.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", " test123", "Fake Person", "123 456 789");
            System.out.println("displayName can start with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test123 ", "Fake Person", "123 456 789");
            System.out.println("displayName can end with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "", "Fake Person", "123 456 789");
            System.out.println("displayName can be shorter than 1 character when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("test@example.com", "test1test1test1test12", "Fake Person", "123 456 789");
            System.out.println("displayName can be longer than 20 characters when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting email parameter in createPlayer");
        try {
            int playerId3 = gl.createPlayer(null, "test123", "Fake Person", "123 456 789");
            System.out.println("Email can be null when it shouldn't be able to.");
        } catch(InvalidEmailException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("", "test123", "Fake Person", "123 456 789");
            System.out.println("Email can be empty when it shouldn't be able to.");
        } catch(InvalidEmailException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("example.com", "test123", "Fake Person", "123 456 789");
            System.out.println("Email can doesn't need an @ symbol, when it should.");
        } catch(InvalidEmailException ex) {System.out.println(ex.getMessage());}
        try {
            int playerId3 = gl.createPlayer("sc1448@exeter.ac.uk", "test123", "Fake Person", "123 456 789");
            System.out.println("Email can be a duplicate of another email.");
        } catch(IllegalEmailException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting playerId parameter in getPlayerEmail");
        try {
            String playerId3 = gl.getPlayerEmail(-1);
            System.out.println("Can request the email of a player that doesn't exist.");
        } catch(IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting playerId parameter in getPlayerDisplayName");
        try {
            String playerId3 = gl.getPlayerDisplayName(-1);
            System.out.println("Can request the display name of a player that doesn't exist.");
        } catch(IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting displayName parameter in updateDisplayName");
        try {
            gl.updatePlayerDisplayName(1, null);
            System.out.println("displayName can be null.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updatePlayerDisplayName(1, " test123");
            System.out.println("displayName can start with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updatePlayerDisplayName(1, "test123 ");
            System.out.println("displayName can end with whitespace when it shouldn't be able to.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updatePlayerDisplayName(1, "");
            System.out.println("displayName can be shorter than 1 character when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updatePlayerDisplayName(1, "test1test1test1test12");
            System.out.println("displayName can be longer than 20 characters when it shouldn't be.");
        } catch(InvalidNameException ex) {System.out.println(ex.getMessage());}
        try {
            gl.updatePlayerDisplayName(-1, "test123");
            System.out.println("Can change the displayName of a player that doesn't exist.");
        } catch(IDInvalidException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nHopefully everything is good.");
    }
}
