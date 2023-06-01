package Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private String humanPlayerSecretCode;
    protected String humanPlayerGuess;
    private int guessOption = 0;
    Game game = new Game();
    protected List<String> fileGuessesList = new ArrayList<>();
    protected String filepath;
    private int bullsCounter;
    private int cowsCounter;

    //This method asks a humanPlayer to enter his/her secret code.
    @Override
    public void generateSecretCode() {
        System.out.println("Please enter your secret code:");
        Scanner scanner = new Scanner(System.in);
        humanPlayerSecretCode = scanner.next();
    }

    //This method validates if the input entered is of correct input type (for example: [0-9]+) or not. If not, it informs the user and returns false, if yes, then it returns true.
    public boolean isInputOfValidType(String stringToCheck) {
        if (stringToCheck.matches(game.getStringType()) == false) {
            System.out.println("Oops! Looks like your input isn't of the correct type. Please ensure your input contains characters from " + game.getStringType() + " only and try again :)");
            return false;
        }
        return true;
    }

    //This method validates if the input entered is of correct input characterLengthLimit (for example: 4 characters) or not. If not, it informs the user and returns false, if yes, then it returns true.
    public boolean isInputOfRequiredLength(String stringToCheck) {
        if (stringToCheck.length() != game.getCharacterLengthLimit()) {
            System.out.println("Oops! Looks like your input isn't of the correct length. Please ensure your input is " + game.getCharacterLengthLimit() + " characters long and try again :)");
            return false;
        }

        return true;
    }

    //This method validates the input in terms of whether it has duplicate characters in it or not. If it contains duplicate character(s), it informs the user and returns true, if not, then it returns false.
    public boolean inputContainsDuplicate(String stringToCheck) {
        if (isDuplicate(stringToCheck) == true) {
            System.out.println("Oops! Looks like your input has duplicate characters in it. Please try again :)");
            return true;
        }
        return false;
    }

    //This method basically validates all requirements for a humanPlayer's secretCode to be valid.
    @Override
    public void validateSecretCode() {
        while (isInputOfValidType(humanPlayerSecretCode) == false || isInputOfRequiredLength(humanPlayerSecretCode) == false || inputContainsDuplicate(humanPlayerSecretCode) == true) {
            generateSecretCode();
        }
    }


    //This method asks a humanPlayer to enter his/her guess.

    //This method asks a humanPlayer his/her guessing source. That is either manually typed or from a file.
    public void askGuessSource() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> guessOptions = new ArrayList<>();
        guessOptions.add(1);
        guessOptions.add(2);
        boolean isValid = false;
        while (isValid == false) {
            try {
                while (guessOption < 1 || guessOption > 2) {
                    System.out.println("Would you like to manually type your guess or generate it from a text file source?");
                    System.out.println(" Press [1]: To type manually");
                    System.out.println(" Press [2]: To generate from text file source");
                    guessOption = Integer.parseInt(scanner.next());
                    if (!guessOptions.contains(guessOption)) {
                        System.out.println("Oops! Looks like you have entered the wrong input. Please try again :)");
                        System.out.println();
                    }
                }
                if (guessOption == 1) {
                    System.out.println();
                    System.out.println("Your Guess Source is: Manually Typed");
                    isValid = true;

                }
                if ((guessOption == 2)) {
                    System.out.println();
                    System.out.println("Your Guess Source is: From Text File");
                    insertFileGuessesToList();
                    isValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Oops! Looks like you have entered a different datatype. Please choose either 1 or 2. Try again :)");
                System.out.println();
            }
        }
    }

    @Override
    public void generateGuess() {
        System.out.println("Please enter your guess:");
        Scanner scanner = new Scanner(System.in);
        humanPlayerGuess = scanner.next();
    }


    //This method actually generates guesses.
    public void generateHumanGuess(int guessOption) {
        if (guessOption == 1) {
            generateGuess();
            validateGuess();
        }
        if (guessOption == 2) {
            int indexCounter = 0;
            while (indexCounter < fileGuessesList.size()) {
                humanPlayerGuess = fileGuessesList.get(indexCounter);
                System.out.println(humanPlayerGuess);
                fileGuessesList.remove(indexCounter);
                break;
            }
        }

    }


    public String getGuessesFilePath() {
        System.out.println("Please Enter The Path of your text file.");
        System.out.println("NOTE: Make sure you enter the \".txt\" extension as well");
        Scanner scanner = new Scanner(System.in);
        filepath = scanner.next();
        return filepath;
    }

    public void insertFileGuessesToList() {
        boolean valid = false;
        while (valid == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(getGuessesFilePath()));
                String stringsInFile;
                while ((stringsInFile = br.readLine()) != null) {
                    fileGuessesList.add(stringsInFile);
                }
                br.close();
                valid = true;
            } catch (IOException e) {
                System.out.println("Oops something has gone wrong. Make sure your file path is correct and has a .txt extension and please try again :) \n");
            }
        }
    }

    //This method basically validates all requirements for a humanPlayer's guess to be valid.
    @Override
    public void validateGuess() {
        while (isInputOfValidType(humanPlayerGuess) == false || isInputOfRequiredLength(humanPlayerGuess) == false || inputContainsDuplicate(humanPlayerGuess) == true) {
            generateGuess();
        }
    }


    private String getHumanSecretCode() {
        return humanPlayerSecretCode;
    }


    //This method gets the bulls and cows for a computer guess.
    public void bullsAndCows(String guess, HumanPlayer humanPlayer) {
        bullsCounter = 0;
        cowsCounter = 0;
        for (int i = 0; i < game.getCharacterLengthLimit(); i++) {
            if (guess.charAt(i) == humanPlayer.getHumanSecretCode().charAt(i)) {
                bullsCounter++;
            }
            if (humanPlayer.humanPlayerSecretCode.contains(String.valueOf(guess.charAt(i))) && guess.charAt(i) != humanPlayer.humanPlayerSecretCode.charAt(i)) {
                cowsCounter++;
            }

        }
        System.out.println("Computer Result: " + bullsCounter  + " bull and " + cowsCounter + " cows");
        Game.gameResult.add("Computer Result: " + bullsCounter  + " bull and " + cowsCounter + " cows");
    }


    //This method prints the human secret code.
    public void printHumanSecretCode(HumanPlayer humanPlayer) {
        System.out.println(" Your Secret Code: " + humanPlayer.getHumanSecretCode());
        Game.gameResult.add(" Your Secret Code: " + humanPlayer.getHumanSecretCode());
    }

    //This method returns the guessOption
    public int getGuessOption() {
        return guessOption;
    }

    //This method checks
    public boolean checkIfComputerGuessMatchHumanSecret(String computerGuess, HumanPlayer humanPlayer){
        if (computerGuess.equals(humanPlayer.getHumanSecretCode())){
            return true;
        }
        return false;
    }

}

