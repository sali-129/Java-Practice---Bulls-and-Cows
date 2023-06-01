package Assignment1;

public class EasyAI extends ComputerPlayer {

    public void generateEasyAIGuess(){
        generateSubString();
        generateCode();
        computerPlayerGuess = generatedCode;
    }



}