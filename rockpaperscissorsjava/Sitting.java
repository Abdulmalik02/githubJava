package homework;

import homework.PaperRockScissors;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Sitting {

    int maxWins;
    int maxLosses;

    public Sitting(int maxWins, int maxLosses) {
        this.maxWins = maxWins;
        this.maxLosses = maxLosses;
    }

    public Sitting() {
        this(2, 2);
    }

    public void play() {
        Map<String, AtomicInteger> results = new HashMap<String, AtomicInteger>() {{
            put("win", new AtomicInteger(0));
            put("loss", new AtomicInteger(0));
            put("tie", new AtomicInteger(0));
        }};

        System.out.println();
        System.out.println("The game of Paper, Rock, Scissors!");
        System.out.println(String.format("In this sitting, we'll play until someone has either won %s times or lost %s times.", this.maxWins, this.maxLosses));
        System.out.println();

        while (results.get("win").get() < this.maxWins && results.get("loss").get() < this.maxLosses) {
            PaperRockScissors game = new PaperRockScissors();
            String result = game.play();
            results.get(result).incrementAndGet();
        }

        System.out.println(String.format("Well, it looks like you %s.", (results.get("win").get() == this.maxWins ? "won" : "lost")));
    }
}
