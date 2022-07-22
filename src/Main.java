import java.util.Scanner;
import java.util.Random;

public class Main {
    public final static Random random = new Random();
    public final static String[] words = new String[] { "HEART" };
    public static String chosenWord = words[random.nextInt(words.length)];;

    public static boolean complete = false;

    public static String checkGuessedWord(String guessedWord) {
        String[] output = new String[5];
        String dupChosenWord = chosenWord;
        for (byte idx = 0; idx < guessedWord.length(); idx++) {
            if (guessedWord.charAt(idx) == chosenWord.charAt(idx)) {
                dupChosenWord = dupChosenWord.replace(String.valueOf(guessedWord.charAt(idx)), "");
                output[idx] = "🟩";
            } else if (chosenWord.contains(String.valueOf(guessedWord.charAt(idx))) & dupChosenWord.contains(
                    String.valueOf(guessedWord.charAt(idx)))) {
                dupChosenWord = dupChosenWord.replace(String.valueOf(guessedWord.charAt(idx)),
                        "");
                output[idx] = "🟨";
            } else {
                output[idx] = "⬜";
            }
        }
        if (String.join(" ", output).equals("🟩 🟩 🟩 🟩 🟩")) {
            complete = true;
        }
        return String.join(" ", output);
    }

    public static void sendText(byte guessesLeft) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        if (scanner.hasNextLine()) {
            String guessedWord = scanner.nextLine().toUpperCase();
            if (guessedWord.length() == 5) {
                String output = checkGuessedWord(guessedWord);
                System.out.println("Guess Attempts Left: " + (6 - (guessesLeft + 1)));
                System.out.println("━━━━━━━━━━━━━━━━━");
                System.out
                        .println(guessedWord.charAt(0) + "  " + guessedWord.charAt(1) + "  " + guessedWord.charAt(2)
                                + "  "
                                + guessedWord.charAt(3)
                                + "  " + guessedWord.charAt(4));
                System.out.println(output);
                System.out.println("━━━━━━━━━━━━━━━━━");
            } else
                sendText(guessesLeft);
        }
        if (complete) {
            System.out.println("Congratulations! You guessed the correct word.");
            scanner.close();
        }
    }

    public static void main(String[] args) {
        for (byte guesses = 0; guesses < 6; guesses++) {
            if (complete)
                break;
            sendText(guesses);
        }
    }
}