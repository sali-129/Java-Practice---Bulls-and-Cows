package Assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;

public class ComputerPlayer extends Player {


    private String alphaNumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int TOTAL_ALPHA_NUMERIC_CHARACTERS = alphaNumericCharacters.length();
    private String subString;
    private String computerPlayerSecretCode;
    public String computerPlayerGuess;
    private Game game = new Game();
    public String generatedCode;
    private int bullsCounter;
    private int cowsCounter;





    //This method generates a subString of type game.getStringType from the string of alphaNumericCharacters. This is done so that the generation of code (computerPlayerSecretCode and/or computerPlayerGuess) would be from the correct type only.
    public void generateSubString() {
        Pattern pattern = Pattern.compile(game.getStringType());
        Matcher matcher = pattern.matcher(alphaNumericCharacters);
        if (matcher.find()) {
            subString = matcher.group();
        }
    }


    //This method generates a string of game.getCharacterLengthLimit length --> which could be used as a computerSecretCode or a computerSecretGuess.
    public String generateCode() {
        List<String> mySubstringList = new ArrayList<>();
        for (int i = 0; i < subString.length(); i++) {
            mySubstringList.add(String.valueOf(subString.charAt(i)));
        }
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        while (counter < game.getCharacterLengthLimit()) {
            Random random = new Random();
            int low = 0;
            int randomCharPosition = random.nextInt(mySubstringList.size() - low) + low;
            String randomChar = mySubstringList.get(randomCharPosition);
            mySubstringList.remove(randomChar);
            stringBuilder.append(randomChar);
            counter++;
        }
        return generatedCode = stringBuilder.toString();
    }


    @Override
    public void generateSecretCode() {
        generateSubString();
        generateCode();
        computerPlayerSecretCode = generatedCode;
    }


    public String generateComputerGuess(int difficultyLevel) {
        if (difficultyLevel == 1){
            EasyAI easyAI = new EasyAI();
            easyAI.generateEasyAIGuess();
            return computerPlayerGuess = easyAI.computerPlayerGuess;
        }
        if (difficultyLevel == 2){
            MediumAI mediumAI = new MediumAI();
            mediumAI.generateMediumAIGuess();
            return computerPlayerGuess = mediumAI.computerPlayerGuess;
        }
        if (difficultyLevel == 3){
            HardAI hardAI = new HardAI();
            hardAI.generateHardAIguess();
            return  computerPlayerGuess = hardAI.computerPlayerGuess;
        }
        return null;
    }



    private String getComputerSecretCode() {
        return computerPlayerSecretCode;
    }


    public boolean checkIfHumanGuessMatchCompSecret(String humanGuess, ComputerPlayer computerPlayer){
        if (humanGuess.equals(computerPlayer.getComputerSecretCode())){
            return true;
        }
        return false;
    }


    //This method gets the bulls and cows for a human guess.
    public void bullsAndCows(String guess, ComputerPlayer computerPlayer) {
        bullsCounter = 0;
        cowsCounter = 0;
        for (int i = 0; i < game.getCharacterLengthLimit(); i++) {
            if (guess.charAt(i) == computerPlayer.getComputerSecretCode().charAt(i)) {
                bullsCounter++;
            }
            if (computerPlayer.getComputerSecretCode().contains(String.valueOf(guess.charAt(i))) && guess.charAt(i) != computerPlayer.getComputerSecretCode().charAt(i)) {
                cowsCounter++;
            }

        }
        System.out.println("Your Result: " + bullsCounter  + " bull and " + cowsCounter  + " cows");
        Game.gameResult.add("Your Result: " + bullsCounter + " bull and " + cowsCounter + " cows");
    }




    public void printSecretCode(ComputerPlayer computerPlayer){
        System.out.println(" Computer Secret Code: " + computerPlayer.getComputerSecretCode());
        Game.gameResult.add(" Computer Secret Code: " + computerPlayer.getComputerSecretCode());
    }



}