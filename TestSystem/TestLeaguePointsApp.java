import gamesleague.*;

import java.time.LocalDate;
import java.util.Arrays;

import javax.sound.midi.Soundbank;

/*
 * javac -d ./bin ./src/gamesleague/*.java
 * javac -cp bin ./TestSystem/TestLeaguePointsApp.java
 * java -cp bin:TestSystem TestLeaguePointsApp 
 */


public class TestLeaguePointsApp  {
    public static void main(String[] args) {
        System.out.println("Testing GamesLeague methods for game results, points, scores, reports, etc.");
        GamesLeague gl = new GamesLeague();

        int playerId0 = gl.createPlayer("person3@example.com", "person3", "Person 1", "123");
        int playerId1 = gl.createPlayer("person4@example.com", "person4", "Person 2", "456");
        int playerId2 = gl.createPlayer("person5@example.com", "person5", "Person 3", "789");
        int leagueId0 = gl.createLeague(playerId0, "Test League", GameType.DICEROLL);
        gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId1));
        gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId2));
        gl.acceptInviteToLeague(leagueId0, playerId1);
        gl.setLeagueStartDate(leagueId0, (int) DateProvider.now().toEpochDay() - 372);

        System.out.println("Empty Game Report: " + gl.getGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId1));
        System.out.println("Making game report");
        gl.registerGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId1, "It was crazy, you should've been there daug!");
        System.out.println("Game report made");
        System.out.println("Game Report: " + gl.getGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId1));
        gl.registerGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId1, "Fireballs everywhere!");
        System.out.println("New Game Report: " + gl.getGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId1));
        gl.registerGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId0, "Wordle goes hard man!!!!!!");

        System.out.println("\nRegistering Day Scores");
        gl.registerDayScores((int) DateProvider.now().toEpochDay(), leagueId0, new int[] {5, 10});
        System.out.println("Scores registered.");
        System.out.println("Day Status: " + gl.getDayStatus(leagueId0, (int) DateProvider.now().toEpochDay()));
        System.out.println("Day Scores: " + Arrays.toString(gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay())));
        System.out.println("Day Points: " + Arrays.toString(gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay())));
        System.out.println("Day Ranking: " + Arrays.toString(gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay())));

        System.out.println("\nTESTING BUBBLE SORT");
        System.out.println("SORTED: " + Arrays.toString(gl.bubbleSort(new int[] {1, 7, 2, 0, 9, 28, 10000})));
        System.out.println("RANKED: " + Arrays.toString(gl.rank(new int[] {1, 7, 2, 7, 0, 9, 28, 10000})));
        System.out.println("RANKED: " + Arrays.toString(gl.rank(new int[] {4, 4, 4, 4, 4, 4, 4, 4, 6})));
        System.out.println("RANKED: " + Arrays.toString(gl.rank(new int[] {1, 2, 3, 4, 5, 6})));
        System.out.println("RANKED: " + Arrays.toString(gl.rank(new int[] {1, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 2})));


        System.out.println("\n\nTesting Exceptions for registerGameReport");
        try {
            gl.registerGameReport((int) DateProvider.now().toEpochDay(), -999999, playerId0, "Test Game Report");
            System.out.println("ERROR: Can make a game report for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.registerGameReport((int) DateProvider.now().toEpochDay(), leagueId0, -999999, "Test Game Report");
            System.out.println("ERROR: Can make a game report for a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.registerGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId2, "Test Game Report");
            System.out.println("ERROR: Can make a game report for a player not in the league.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.registerGameReport((int) DateProvider.now().toEpochDay() - 1000, leagueId0, playerId0, "Test Game Report");
            System.out.println("ERROR: Can make a game report for an invalid day.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for getGameReport");
        try {
            gl.getGameReport((int) DateProvider.now().toEpochDay(), -999999, playerId0);
            System.out.println("ERROR: Can make get a game report for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getGameReport((int) DateProvider.now().toEpochDay(), leagueId0, -999999);
            System.out.println("ERROR: Can make get a game report for a player that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getGameReport((int) DateProvider.now().toEpochDay(), leagueId0, playerId2);
            System.out.println("ERROR: Can make get a game report for a player not in the league.");
        } catch (IllegalOperationException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getGameReport((int) DateProvider.now().toEpochDay() - 1000, leagueId0, playerId0);
            System.out.println("ERROR: Can get a game report for an invalid day.");
        } catch (InvalidDateException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for registerDayScores");
        try {
            gl.registerDayScores((int) DateProvider.now().toEpochDay(), -9999, new int[] {5, 10});
            System.out.println("ERROR: Can register day scores for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.registerDayScores((int) DateProvider.now().toEpochDay(), leagueId0, new int[] {5, 10});
            System.out.println("ERROR: Day specified has already been closed.");
        } catch (IllegalArgumentException ex) {System.out.println(ex.getMessage());}
        try {
            gl.registerDayScores((int) DateProvider.now().toEpochDay() - 2, leagueId0, new int[] {5, 10});
            System.out.println("ERROR: Can register a day score for a date 2 or more days older than the current date.");
        } catch (IllegalArgumentException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for getDayStatus");
        try {
            gl.getDayStatus(-9999, (int) DateProvider.now().toEpochDay());
            System.out.println("ERROR: Can get day status for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getDayStatus(leagueId0, (int) DateProvider.now().toEpochDay() - 99999);
            System.out.println("ERROR: Can get day status for an invalid day.");
        } catch (InvalidDateException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for getDayScores");
        try {
            gl.getDayScores(-9999, (int) DateProvider.now().toEpochDay());
            System.out.println("ERROR: Can get day scores for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay() - 99999);
            System.out.println("ERROR: Can get day scores for an invalid day.");
        } catch (InvalidDateException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for getDayPoints");
        try {
            gl.getDayPoints(-9999, (int) DateProvider.now().toEpochDay());
            System.out.println("ERROR: Can get day points for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay() - 99999);
            System.out.println("ERROR: Can get day points for an invalid day.");
        } catch (InvalidDateException ex) {System.out.println(ex.getMessage());}

        System.out.println("\nTesting Exceptions for getDayRanking");
        try {
            gl.getDayRanking(-9999, (int) DateProvider.now().toEpochDay());
            System.out.println("ERROR: Can get day ranking for a league that doesn't exist.");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay() - 99999);
            System.out.println("ERROR: Can get day ranking for an invalid day.");
        } catch (InvalidDateException ex) {System.out.println(ex.getMessage());}


        System.out.println("\nVoiding Day Points");
        gl.voidDayPoints((int) DateProvider.now().toEpochDay() - 1, leagueId0);
        System.out.println("Day Points: " + Arrays.toString(gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Scores: " + Arrays.toString(gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Rankings: " + Arrays.toString(gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        gl.acceptInviteToLeague(leagueId0, playerId2);
        gl.registerGameReport((int) DateProvider.now().toEpochDay() - 1, leagueId0, playerId0, "Game Report 0");
        gl.registerGameReport((int) DateProvider.now().toEpochDay() - 1, leagueId0, playerId1, "Game Report 1");
        gl.registerGameReport((int) DateProvider.now().toEpochDay() - 1, leagueId0, playerId2, "Game Report 2");
        gl.registerDayScores((int) DateProvider.now().toEpochDay() - 1, leagueId0, new int[] {5, 10, 0});
        System.out.println("Day Scores: " + Arrays.toString(gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Points: " + Arrays.toString(gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Ranking: " + Arrays.toString(gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        gl.deactivatePlayer(playerId2);
        System.out.println("Deactivating Player");
        System.out.println("Day Scores: " + Arrays.toString(gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Points: " + Arrays.toString(gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Ranking: " + Arrays.toString(gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));

        System.out.println("\nResetting League");
        gl.resetLeague(leagueId0);
        System.out.println("Day Scores: " + Arrays.toString(gl.getDayScores(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Points: " + Arrays.toString(gl.getDayPoints(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Day Ranking: " + Arrays.toString(gl.getDayRanking(leagueId0, (int) DateProvider.now().toEpochDay() - 1)));
        System.out.println("Game Report: " + gl.getGameReport((int) DateProvider.now().toEpochDay() - 1, leagueId0, playerId2));
        
        System.out.println("\nTesting Exceptions for voidDayPoints");
        try {
            gl.voidDayPoints((int) DateProvider.now().toEpochDay() + 1, -9999);
            System.out.println("ERROR: Can void day points for a non-existent league");
        } catch (IDInvalidException ex) {System.out.println(ex.getMessage());}
        try {
            gl.voidDayPoints((int) DateProvider.now().toEpochDay() - 2, leagueId0);
            System.out.println("ERROR: Can void day points for a day 2 or more days away.");
        } catch (IllegalArgumentException ex) {System.out.println(ex.getMessage());}
        try {
            gl.voidDayPoints((int) DateProvider.now().toEpochDay() - 9999999, leagueId0);
            System.out.println("ERROR: Can void an invalid day.");
        } catch (IllegalArgumentException ex) {System.out.println(ex.getMessage());}

        /*
         * Methods to test:
         * 
         * resetLeague - Specifically check if gameplay history and scores have been deleted
         * 
         * getWeekStatus
         * getWeekPoints
         * getWeekRanking
         * 
         * getMonthStatus
         * getMonthPoints
         * getMonthRanking
         * 
         * getYearStatus
         * getYearPoints
         * getYearRanking
         * 
         * getPlayerRoundsPlayed
         * getPlayerRoundsPercentage
         */
    }
}
