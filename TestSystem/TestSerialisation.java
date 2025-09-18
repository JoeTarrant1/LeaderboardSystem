import gamesleague.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.time.LocalDate;

/*
 * javac -d ./bin ./src/gamesleague/*.java
 * javac -cp bin ./TestSystem/TestSerialisation.java
 * java -cp bin:TestSystem TestSerialisation 
 */


public class TestSerialisation  {
    public static void main(String[] args) {
        System.out.println("Testing GamesLeague methods for serialisation.");
        GamesLeague gl = new GamesLeague();

        /*
         * Methods to test:
         * 
         * saveGamesLeagueData
         * eraseGamesLeagueData
         * loadGamesLeagueData
         */

         int playerId0 = gl.createPlayer("person3@example.com", "person3", "Person 1", "123");
         int playerId1 = gl.createPlayer("person4@example.com", "person4", "Person 2", "456");
         int playerId2 = gl.createPlayer("person5@example.com", "person5", "Person 3", "789");
         int leagueId0 = gl.createLeague(playerId0, "Test League", GameType.DICEROLL);
         gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId1));
         gl.invitePlayerToLeague(leagueId0, gl.getPlayerEmail(playerId2));
         gl.acceptInviteToLeague(leagueId0, playerId1);
         gl.setLeagueStartDate(leagueId0, (int) DateProvider.now().toEpochDay() - 372);

        try{
            gl.saveGamesLeagueData("test.ser");
        }
        catch(IOException e){
            System.out.println("Save failed");
        }

         System.out.println(Arrays.toString(gl.getPlayerIds()));

         int playerId3 = gl.createPlayer("email@email.com", "person", "person", "428948");

         System.out.println(Arrays.toString((gl.getPlayerIds())));

         System.out.println(Arrays.toString(gl.getLeagueIds()));

         int leagueId1 = gl.createLeague(playerId0, "Test League2", GameType.DICEROLL);

         System.out.println(Arrays.toString(gl.getLeagueIds()));

         try{
            gl.loadGamesLeagueData("test.ser");
         }
         catch(IOException e){
            System.out.println("Load failed");
         }
         catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
         }

         System.out.println(Arrays.toString(gl.getPlayerIds()));

         System.out.println(Arrays.toString(gl.getLeagueIds()));

         gl.eraseGamesLeagueData();

         System.out.println(Arrays.toString(gl.getPlayerIds()));

         System.out.println(Arrays.toString(gl.getLeagueIds()));



        
    }
}
