import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

/**
 * The game of Pig is a very simple jeopardy dice game in which two players
 * race to reach 100 points.Each turn, a player repeatedly rolls a die until
 * either a 1 is rolled or the player holds and scores the sum of the rolls
 * (i.e. the turn total). At any time during a player's turn,
 * the player is faced with two decisions:
 *
 *  roll -  If the player rolls a
 *      1:      The player scores nothing and it becomes the opponent's turn.
 *      2 - 6:  The number is added to the player's turn total and the player's
 *              turn continues.
 *  hold -  The turn total is added to the player's score and it becomes the
 *          opponent's turn.
 *
 * Set your program up so a human and a computer can play together. Program a
 * simple computer opponent, ie perhaps a rule 'always roll 3 times',
 * 'roll a die and take that many rolls' or something like that. The human
 * player goes first. You can use the console or the GUI,
 * whichever you are most comfortable with.
 */
public class Pig {
    /**
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        String decision         = " ";
        int currentRole         = 0;
        final String ROLL = "roll";
        final String HOLD = "hold";

        final int ONE_HUNDRED = 100;

        Game game = new Game();
        User player = new User("Joe", true);
        User npc = new User("Bender");

        game.addUser(player);
        game.addUser(npc);
        game.start();

        while (game.isActive()) {
            do {
                decision = game.getCurrentPlayer().promptAction();
            } while (!decision.equals(ROLL) && !decision.equals(HOLD));

            if (decision.equals(ROLL)) {
                currentRole = Dice.roll();

                if (currentRole != 1) {
                    game.getCurrentPlayer().setTurnScore(game
                            .getCurrentPlayer().getTurnScore() + currentRole);

                    JOptionPane.showMessageDialog(null,
                            game.getCurrentPlayer().getUsername() + " rolled:"
                                    + " " + currentRole + ". \n"
                                    + "turn score: " + game.getCurrentPlayer()
                                    .getTurnScore() + ".\n"
                                    + "game score: " + game.getCurrentPlayer()
                                    .getScore() + ".");
                } else {
                    game.getCurrentPlayer().setTurnScore(0);

                    JOptionPane.showMessageDialog(null,
                            game.getCurrentPlayer().getUsername() + " rolled:"
                                    + " " + currentRole + ". \n"
                                    + "turn score is now " + game
                                    .getCurrentPlayer().getTurnScore() + ".\n"
                                    + "game score: " + game.getCurrentPlayer()
                                    .getScore() + ".\n"
                                    + "Turn is now over!");

                    game.nextPlayer();
                    continue;
                }

            } else if (decision.equals(HOLD)) {
                game.getCurrentPlayer().hold();
                game.nextPlayer();
            }

            if (ONE_HUNDRED <= game.getCurrentPlayer().getScore()) {

                JOptionPane.showMessageDialog(null, game.getCurrentPlayer()
                        .getUsername()
                        + " is the winner with a score of "
                        + game.getCurrentPlayer().getScore()
                        + ".");

                game.setActive(false);
            }
        }
    }
}

class User {
    private int score;
    private int turnScore;

    private String username;
    private boolean human;
    private String action   = " ";
    final int FIFTEEN = 15;
    final int SIX           = 6;

    public User(String username) {
        this(username, false);
    }

    public User(String username, boolean isHuman) {
        this.username = username;
        this.score = 0;
        this.human = isHuman;
    }

    public String promptAction() {
        String action = " ";

        if (this.human) {
            action = (String) JOptionPane.showInputDialog(this.username + ", "
                    + "your score is " + this.getScore() + ". Would you like "
                    + "" + "to"
                    + " \"roll\" or \"hold\"?");
        } else if (!this.human) {
            if (this.getTurnScore() >= FIFTEEN) {
                action = "hold";
                JOptionPane.showMessageDialog(null, this.username + " chose "
                        + "to \"hold\".");
            } else {
                action = "roll";
            }
        }

        return action;
    }

    public void hold() {
        this.setScore(this.getScore() + this.getTurnScore());
        this.setTurnScore(0);
    }

    public int getTurnScore() {
        return this.turnScore;
    }

    public void setTurnScore(int score) {
        this.turnScore = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isHuman() {
        return this.human;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String usernameParam) {
        this.username = usernameParam;
    }
}

class Dice {
    public static int roll() {
        return new Random().nextInt(SIX) + 1;
    }
}

class Game {
    private boolean active = false;
    private User currentPlayer;
    private ArrayList<User> users = new ArrayList<User>();
    private int turnScore = 0;

    public void start() {
        this.active = true;
        setFirstPlayer();
    }

    public void end() {
        this.active = false;
    }

    public void nextPlayer() {
        int index = (this.users.indexOf(this.currentPlayer) + 1) % this.users
                .size();
        this.currentPlayer = this.users.get(index);
    }

    private void setFirstPlayer() {
        for (User user : this.users) {
            if (user.isHuman()) {
                this.currentPlayer = user;
                break;
            }
        }
    }


    public void setTurnScore(int score) {
        this.turnScore = score;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean bool) {
        this.active = bool;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getCurrentPlayer() {
        return this.currentPlayer;
    }
}