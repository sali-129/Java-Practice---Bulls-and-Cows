package Assignment1;

import java.util.Scanner;

public abstract class Player {

    //This method generates/asks for a secret code.
    public void generateSecretCode(){
    }

    //This method checks for duplication of characters within a string. If there is/are duplicate character(s), then it returns a true otherwise false.
    public boolean isDuplicate(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            for (int j = i + 1; j < stringToCheck.length(); j++) {
                if (stringToCheck.charAt(i) == stringToCheck.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    //This method generates/asks for a guess.
    public void generateGuess(){

    }
    //This method validates a secretCode
    public void validateSecretCode(){

    }
    //This method validates a guess
    public void validateGuess(){

    }



}