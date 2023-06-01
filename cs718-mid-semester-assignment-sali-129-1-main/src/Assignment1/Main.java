package Assignment1;


import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    Game game = new Game();
    HumanPlayer humanPlayer = new HumanPlayer();
    ComputerPlayer computerPlayer = new ComputerPlayer();


    public static void main(String[] args) {
        Main main = new Main();
        main.game.introMessage();
        main.game.aboutGameMessage();
        String filler = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(filler);
        main.game.askDifficultyLevel();
        System.out.println(filler);
        main.humanPlayer.askGuessSource();
        System.out.println(filler);
        main.humanPlayer.generateSecretCode();
        main.humanPlayer.validateSecretCode();
        main.computerPlayer.generateSecretCode();   //No need to validate the generated ComputerSecret Code as it's always valid.
        System.out.println("Alright, now it's your time to guess what the computer's secret code might be. Good luck!!");
        int currentRound = 1;
        boolean isDraw = true;
        while (currentRound <= main.game.getRoundLimit()) {
            System.out.println(filler);
            Game.gameResult.add(filler);
            String RoundNumber = "Round " + currentRound + " out of " + main.game.getRoundLimit() + ":";
            System.out.println(RoundNumber);
            Game.gameResult.add(RoundNumber);
            main.humanPlayer.generateHumanGuess(main.humanPlayer.getGuessOption());
            String humanGuess = "You Guessed: " + main.humanPlayer.humanPlayerGuess;
            System.out.println(humanGuess);
            Game.gameResult.add(humanGuess);
            main.computerPlayer.bullsAndCows(main.humanPlayer.humanPlayerGuess, main.computerPlayer);  // --> This checks the Bulls and Cows of a Human Guess against a Computer Secret Code.
            if (main.computerPlayer.checkIfHumanGuessMatchCompSecret(main.humanPlayer.humanPlayerGuess, main.computerPlayer)) {
                String youWon = "Congratulations! You've won. You've guessed the correct secret code.";
                System.out.println(youWon);
                Game.gameResult.add(youWon);
                isDraw = false;
                break;
            }
            System.out.println();
            Game.gameResult.add("");
            String computerGuessed = "Computer Guessed: " + main.computerPlayer.generateComputerGuess(main.game.difficultyLevel);
            System.out.println(computerGuessed);
            Game.gameResult.add(computerGuessed);
            main.humanPlayer.bullsAndCows(main.computerPlayer.computerPlayerGuess, main.humanPlayer);
            if (main.humanPlayer.checkIfComputerGuessMatchHumanSecret(main.computerPlayer.computerPlayerGuess, main.humanPlayer)) {
                String ohSnap = "Oh snap! The computer has guessed your secret code correctly.";
                System.out.println(ohSnap);
                Game.gameResult.add(ohSnap);
                String giveComputerSecretCode = "The computers secret code was: ";
                System.out.println(giveComputerSecretCode);
                Game.gameResult.add(giveComputerSecretCode);
                main.computerPlayer.printSecretCode(main.computerPlayer);
                isDraw = false;
                break;
            }
            currentRound++;


        }
        if (isDraw == true) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            Game.gameResult.add(filler);
            String welp = "Welp! It seems like neither you nor the computer were able to guess each others secret codes. Therefore, this game ends in a draw!";
            System.out.println(welp);
            Game.gameResult.add(welp);
            String both = "Here are both of your secret code: ";
            System.out.println(both);
            Game.gameResult.add(both);
            main.computerPlayer.printSecretCode(main.computerPlayer);
            main.humanPlayer.printHumanSecretCode(main.humanPlayer);
            System.out.println(filler);
        }

        main.game.askSavingPreference();
    }

}





