package homework;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class PaperRockScissors {

    private static final Map<String, String> RESULTS = new HashMap<String, String>() {{
        put("00", "tie");
        put("01", "win");
        put("02", "loss");
        put("10", "loss");
        put("11", "tie");
        put("12", "win");
        put("20", "win");
        put("21", "loss");
        put("22", "tie");
    }};

    private int computerGuess;

    private Scanner scanner = new Scanner(System.in);

    public PaperRockScissors() {
        this.computerGuess = new Random().nextInt(3);
    }

    public String play() {
        System.out.println("*********************************************");
        System.out.println("Enter 0 (paper), 1 (rock), or 2 (scissors): ");

        String result = this.RESULTS.get(this.scanner.nextLine() + this.computerGuess);

        System.out.println("The result was a " + result + " for you.");
        System.out.println();

        return result;
    }
}
