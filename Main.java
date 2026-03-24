import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    static Scanner scanner = new Scanner(System.in);
    static String playerName;
    static int secretNumber;
    static int attempts;
    static final int MAX_ATTEMPTS = 10;
    static final int MIN = 1;
    static final int MAX = 100;

    // Display welcome message
    public static void displayWelcome() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hello, " + playerName + "!\n");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have 10 attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.\n");
        System.out.println("Let's begin!");
        System.out.println("========================================");
    }

    // Generate random number
    public static int generateSecretNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    // Get user guess with validation
    public static int getUserGuess() {
        int guess;

        while (true) {
            System.out.print("Enter your guess (1-100): ");
            guess = scanner.nextInt();

            if (guess < MIN || guess > MAX) {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            } else {
                break;
            }
        }

        return guess;
    }

    // Give hint
    public static void giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    // Play game
    public static void playGame() {
        attempts = 0;
        secretNumber = generateSecretNumber(MIN, MAX);

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("\n--- Attempt #" + (attempts + 1) + " ---");
            int guess = getUserGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attempts + " attempts!");
                return;
            } else {
                giveHint(guess);
            }
        }

        // If failed
        System.out.println("\nGAME OVER!");
        System.out.println("You've used all 10 attempts.");
        System.out.println("The secret number was " + secretNumber + ".");
        System.out.println("Better luck next time, " + playerName + "!");
    }

    // Display statistics
    public static void displayStats() {
        String rating;

        if (attempts == 1) {
            rating = "Perfect!";
        } else if (attempts <= 3) {
            rating = "Excellent!";
        } else if (attempts <= 6) {
            rating = "Good job!";
        } else if (attempts <= 10) {
            rating = "Nice try!";
        } else {
            rating = "Better luck next time!";
        }

        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attempts);
        System.out.println("Rating: " + rating);
        System.out.println("========================================");
    }

    // Ask to play again
    public static boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        char choice = scanner.next().toLowerCase().charAt(0);
        return choice == 'y';
    }

    // Start game
    public static void startGame() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
      
        System.out.print("Enter your name: ");
      
        playerName = scanner.nextLine();

        boolean playAgain;

        do {
            displayWelcome();
            playGame();
            displayStats();
            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("\n========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    // Main method
    public static void main(String[] args) {
        startGame();
    }
}