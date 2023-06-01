package Assignment1;

import java.util.ArrayList;
import java.util.List;

public class MediumAI extends EasyAI {
    public static List<String> allComputerGuessesList = new ArrayList<>();
    public void generateMediumAIGuess() {
        do {
            generateEasyAIGuess();

        }
        while (allComputerGuessesList.contains(computerPlayerGuess) == true);
        allComputerGuessesList.add(generatedCode);

    }
}

