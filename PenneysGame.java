import java.util.Random;
import java.util.Scanner;


/**
 * 
 *  The Penney's Game is a game based on predicting coin tosses.
 *  Two players supply a string of H or T characters which represent heads or tails.
 *  The game then supplies random coin tosses, the first player to have their string matched wins
 * 
 *  I have added my own small addition by adding a 'difficulty' feature, if you initialise the 
 *  PenneysGame object and supply an integer, that becomes the guess length, a shorter guess length
 *  makes the game quicker or 'easier'
 * 
 */
public class PenneysGame
{
    //Scanner instance used to retrieve input from the user
    Scanner input = new Scanner(System.in);

    //Random instance created to later generate random number
    Random random = new Random();

    //This String will store the previous 10 tosses to display to the user
    String previousTosses = "";

    //I also plan to keep track of how many tosses occur until one player wins
    int tossesCount = 0;

    //I have added a small feature which allows the 'difficulty' of the game to be changed
    //By default this value is 3 however if you increased this it would make it harder to guess
    int guessLength;

    //Variables to store the guesses for each player
    String player1Guess;
    String player2Guess;

    //Overloaded constructor for if a guessLength is provided
    public PenneysGame(int guessLength)
    {
        this.guessLength = guessLength;
        
        //Gets the player guesses and assigns them to the respective variable
        String [] guessArray = getPlayerGuesses();
        player1Guess = guessArray[0];
        player2Guess = guessArray[1];

        //Begins the game
        nextTurn();
        
    }


    //Overloaded constructor for if a guessLength is not provided
    public PenneysGame()
    {
        this.guessLength = 3;

       //Gets the player guesses and assigns them to the respective variable
       String [] guessArray = getPlayerGuesses();
       player1Guess = guessArray[0];
       player2Guess = guessArray[1];

       //Begins the game
       nextTurn();
    }


    //This private method is used to randomly generate either a H (Head) or T (Tail) 
    //To simulate a coin toss
    private String getCoinToss()
    {
        //Here i use random.nextInt(2) + 1 as random.nextInt(N) generates a random integer between 0 and N 
        //However i want the numbers 1 or 2 so doing this generates numbers in the range we need
        int randomNum = random.nextInt(2) + 1;

        if (randomNum == 1)
        {
            return "H";
        }
        else
        {
            return "T";
        }
        
    }

    //Method to check if a given guess has won
    //A player has won if their pattern is contained within the previousTosses
    private boolean checkWin(String guess)
    {
        return previousTosses.contains(guess);
    }


    //Method to carry out a single 'turn' within the game
    private void nextTurn()
    {
        System.out.println("============ TURN " + tossesCount + " ============");
        System.out.println("Player 1 guess: " + player1Guess);
        System.out.println("Player 2 guess: " + player2Guess);
        System.out.println();

        //The previous tosses String keeps track of 2 * guessLength number of previous coin tosses
        if (previousTosses.length() == guessLength*2)
        {
            previousTosses = previousTosses.substring(1);
        }

        //Adds the next coin toss to the previous tosses variable
        previousTosses += getCoinToss();

        //for loop to keep track of the position of the 'v' pointer to help the user see what was the most recent coin toss
        for (int i = 0; i < previousTosses.length() -1 ; i ++ )
        {
            System.out.print("  ");
        }
        System.out.print("v");
        System.out.println();

        //Outputs all the previous coin tosses
        for (int i = 0; i < previousTosses.length(); i ++)
        {
            System.out.print(previousTosses.charAt(i) + " ");
        }
        System.out.println();


        //If a player has won the game ends, otherwise the game waits for user input then carries on
        if (checkWin(player1Guess))
        {
            System.out.println("Player 1 wins!");
        }
        else if (checkWin(player2Guess))
        {
            System.out.println("Player 2 wins!");
        }
        else
        {
            System.out.println("Press enter when you are ready to move onto the next coin toss");
            input.nextLine();
            System.out.println();
            tossesCount++;
            System.out.println("================================");
            nextTurn();
        }
        
    }


    //Private method used to check if a guess is valid 
    // only contains H or T and length is not greater than the given guessLength (3 by default)
    private boolean checkGuess(String guess)
    {
        boolean validGuess = true;

        //Checks if the guess length is correct
        if (guess.length() != guessLength)
        {
            validGuess = false;
        }

        //Checks if the guess only contains the characters H or T
        for (int i = 0; i < guess.length(); i++ )
        {
            if (guess.charAt(i) != 'H' && guess.charAt(i) != 'T' )
            {
                validGuess = false;
            }
        }
        return validGuess;
    }

    //Private method to retrieve the guesses from the user
    //Returns an array of two elements, the guesses given by the two users
    private String [] getPlayerGuesses()
    {
        System.out.print("Player 1 please enter your three outcomes: ");
        String player1Guess = input.nextLine().toUpperCase();

        System.out.println();

        System.out.print("Player 2 please enter your three outcomes: ");
        String player2Guess = input.nextLine().toUpperCase();
        System.out.println();


        //Checks if the guesses are valid guesses
        if ((!checkGuess(player1Guess) || !checkGuess(player2Guess)))
        {
            System.out.println("Invalid guesses. Please enter guesses again");
            System.out.println();
            return getPlayerGuesses();
        }

        //Checks if the guesses are equal
        if (player1Guess.equals(player2Guess))
        {
            System.out.println("Guesses cannot be the same. Please enter guesses again");
            System.out.println();
            return getPlayerGuesses();
        }

        String [] returnArray = {player1Guess, player2Guess};
        return  returnArray;
    }


    public static void main(String[] args) 
    {

        PenneysGame PG = new PenneysGame();
        
    }

}