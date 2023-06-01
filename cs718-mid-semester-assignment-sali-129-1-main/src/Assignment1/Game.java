package Assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    public static String stringType = "[0-9]+";
    private int characterLengthLimit = 4;
    private int roundLimit = 7;
    public int difficultyLevel;
    private List<Integer> difficultyLevelList = new ArrayList<>();
    private int maxDifficultLevel = 3;
    private int guessOption = 0;
    private String alphaNumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static List<String> gameResult = new ArrayList<>();

    //This method sets the stringType to the regEx inserted as the parameter
    public  void setStringType(String stringType){
        if (stringType.length() >= alphaNumericCharacters.length()){
            System.out.println("Oops! You simply cannot insert a regular expression which has greater length limit the the alphaNumericCharacters [A-Za-z0-9]+ as this will cause duplication. You need to enter a regular expression less than or equal to " + alphaNumericCharacters.length() + "." );
        }
        else {
            Game.stringType = stringType;
        }
    }

    //This method prints the Intro Message of the Bulls and Cows Game.
    public void introMessage() {
        System.out.println("Hello User!");
        System.out.println("Welcome to a fun \"Bulls and Cows\" game!");
    }

    //This method informs what the Bulls and Cows Game is about.
    public void aboutGameMessage() {
        System.out.println("\nHow it works:");
        System.out.println("(1). Bulls and Cows is an old code-breaking mind game in which you must guess a secret code with unique characters.");
        System.out.println("(2). This game is designed for a player being pitted against a computer player. The players chooses a secret code of " + characterLengthLimit + " characters long ranging from " + stringType + ".");
        System.out.println("(3). The characters must all be different. The goal of the game is for the players to guess each others secret code within " + roundLimit + " rounds. If neither of the players are able to guess each others secret code within " + roundLimit + " rounds, then the game ends in a draw.");
        System.out.println("(4). After each players' guess, number of BULLS and COWS will be show");
        System.out.println(" (4A). The Number of Bulls: This means that x number of characters in your guess matches the secret code of the other player and is in their right position(s).");
        System.out.println(" (4B). The Number of Cows: This means that x number of characters in your guess matches the secret code of the other player, but is NOT in their right position(s).");
        System.out.println("(5). You can also choose the difficulty level of your preference. There are 3 difficulty level to choose from:");
        System.out.println(" (5A). Easy");
        System.out.println(" (5B). Medium");
        System.out.println(" (5C). Hard");
    }

    //This method returns the variable "stringType". This variable stores a regEx which then determines the secretCodes and Guess.
    public  String getStringType() {
        return stringType;
    }

    //This method returns the variable "characterLengthLimit". This variable determines the length of secretCodes and Guess.
    public int getCharacterLengthLimit() {
        return characterLengthLimit;
    }

    //This method returns the variable "roundLimit". This variable determines the maximum number of round that will be played in a Bulls and Cows game.
    public int getRoundLimit() {
        return roundLimit;
    }

    //This method asks the difficulty level a player wishes to choose. It incorporates addDifficultToDifficultyLevelList().
    public void askDifficultyLevel() {
        boolean isValid = false;
        while (isValid == false) {
            try {
                addDifficultyToDifficultyLevelList();
                System.out.println("Please select the difficulty level of the game");
                System.out.println(" Press [1]: Easy\n Press [2]: Medium\n Press [3]: Hard");
                Scanner scanner = new Scanner(System.in);
                difficultyLevel = Integer.parseInt(scanner.next());
                if (difficultyLevelList.contains(difficultyLevel) == false) {
                    System.out.println("Oops! Looks if you didn't choose a correct difficulty level. Please try again :)\n");
                    //askDifficultyLevel();
                } else {
                    if (difficultyLevel == 1) {
                        System.out.println("\nYour chosen difficulty level is: Easy");

                    }
                    if (difficultyLevel == 2) {
                        System.out.println("\nYour chosen difficulty level is: Medium");

                    }
                    if (difficultyLevel == 3) {
                        System.out.println("\nYour chosen difficulty level is: Hard");

                    }
                    isValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Oops! Looks like you have entered a different datatype. Please choose either 1, 2 or 3. Try again :)\n");
            }
        }
    }

    //This method asks the user if he/she wants to save the result of the game.
    public void askSavingPreference(){
        List <Integer> savingPreferences = new ArrayList<>();
        savingPreferences.add(1);
        savingPreferences.add(2);
        int savingChoice;
        boolean isValid = false;
        while (isValid == false){
            try {
                System.out.println("Would you like to save the game result to a file?");
                System.out.println(" Press [1]: Yes\n Press [2]: No");
                Scanner scanner = new Scanner(System.in);
                savingChoice = Integer.parseInt(scanner.next());
                if (savingPreferences.contains(savingChoice)==false){
                    System.out.println(("Oops! Looks like you didn't choose either 1 or 2. Please try again :)\n"));
                } else {
                if (savingChoice == 1){
                    System.out.println("\nYou have chosen to save your game result");
                    saveResultToPath();

                }
                if (savingChoice == 2) {
                    System.out.println("\nYou have chosen not to save your game result. Thanks for playing, bye :)");
                }
                isValid = true;
                }

            } catch (NumberFormatException e){
                System.out.println("Oops! Looks like you have entered a different datatype. Please choose either 1 or 2. Try again :)\n");
            }
        }
    }


    //This method prints the game result in the filePath specified in the parameter.
    public void saveResultToPath() {
        boolean isValid = false;
        while (isValid == false) {
            try {
                System.out.println("\nPlease enter the filepath of where you would like to save your result.");
                Scanner scanner = new Scanner(System.in);
                String filePath = scanner.next();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
                for (int i = 0; i < Game.gameResult.size(); i++) {
                    String str = Game.gameResult.get(i).toString();
                    bufferedWriter.write(str);
                    if (i<Game.gameResult.size()-1){
                        bufferedWriter.write("\n");
                    }
                }
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Oops! Something went wrong. Make sure you have entered the correct filepath with a .txt extension");
            }
            System.out.println("\nOkay, all done. You can now view your result in your file :)");
            isValid = true;
        }
    }




    //This method is useful for scaling the difficulty level. What if you want to have 100 levels of difficulty? This method will add int till 100 (which is the maxDifficultLevel) to a list called difficultyLevelList.
    public void addDifficultyToDifficultyLevelList() {
        int counter = 1;
        while (counter <= maxDifficultLevel) {
            difficultyLevelList.add(counter);
            counter++;
        }
    }


    //This method is useful for setting the maxDifficultLevel of the Game.
    public void setMaxDifficultLevel(int newMaxDifficultLevels) {
        this.maxDifficultLevel = newMaxDifficultLevels;
    }


    //This method returns the guessOption for the game.
    public int getGuessOption(){
        return guessOption;
    }


    //This method adds to the gameResultList

    public void addToArrayList(String toAdd){
        gameResult.add(toAdd);
    }


}
