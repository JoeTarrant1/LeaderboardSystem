# LeaderboardSystem

This program was part of a paired programming project that was set to us as coursework. It was written using the paired programming technique where one of us would code and the other would oversee, we would switch periodically.

The program allows users to create leagues that other users can join so that they can compete in points based games. Users input their scores throughout the day and once all scores have been collected the league will produce daily, weekly, monthly, and yearly rankings using the scores from the users. Leagues can have owners who can customize the league, such as, changing the name or managing users in the league. Start and end dates can be set for leagues so that users can compete over a set period of time, or the end date can be left open and the league will carry on indefinitely. Data is saved using serialization so that leagues can be preseverd even when the program stops running. 

**Note: The `GamesLeagueInterface.java` file and a few of the exception files were given to us as a starting point for the coursework. Our task was to produce a working program from this interface. The `DateProvider.java` file was also given to us later in the project to help out with testing. All other files were made by my coding partner and I.**

## How to run

The test files in the `TestSystem` folder show how the league system is meant to operate, they can be run by using the three terminal commands that are commented at the top of each test file
