package pdg5.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing one side of the game (one per player) It contains the letters
 * of one player (bonus and normal letters), and a score
 */
public class Board implements Serializable {

    /**
     * Word in progress for this client
     */
    private final Composition composition;
    private final int playerId; // id of the player associated
    private final String playerName; // name of the player associated
    private LAST_ACTION lastAction;
    private ArrayList<Tile> bonus; // bonus letters
    private ArrayList<Tile> letters; // normal letters
    private int score; // score of the player associated

    /**
     * Constructor
     *
     * @param playerName name of the associated player
     * @param playerId   unique id of the associated player
     */
    public Board(String playerName, int playerId) {
        this.playerName = playerName.substring(0, 1).toUpperCase() + playerName.substring(1).toLowerCase();
        ;
        this.playerId = playerId;

        bonus = new ArrayList<>();
        letters = new ArrayList<>();
        composition = new Composition();
        lastAction = null;

    }

    public Board(Board board) {
        playerName = board.playerName;
        playerId = board.playerId;
        lastAction = board.lastAction;
        score = board.score;

        composition = new Composition(board.composition);
        bonus = new ArrayList<>(board.bonus);
        letters = new ArrayList<>(board.letters);
    }

    /**
     * return the list of bonus letters
     *
     * @return the list of bonus letters
     */
    public List<Tile> getBonus() {
        return bonus;
    }

    /**
     * set the list of bonus letters to a new one given
     *
     * @param bonus list of the new Tiles
     */
    public void setBonus(List<Tile> bonus) {
        this.bonus = new ArrayList<>(bonus);
    }

    /**
     * return the player last action
     *
     * @return the player last action
     */
    public LAST_ACTION getLastAction() {
        return lastAction;
    }

    /**
     * set the player last action
     *
     * @param lastAction the new player last action
     */
    public void setLastAction(LAST_ACTION lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * return the list of normal letters
     *
     * @return the list of normal letters
     */
    public List<Tile> getLetters() {
        return letters;
    }

    /**
     * set the list of normal letters to a new one given
     *
     * @param letters list of the new Tiles
     */
    public void setLetters(List<Tile> letters) {
        this.letters = new ArrayList<>(letters);
    }

    /**
     * return the String name of the associated player
     *
     * @return the String name of the associated player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * return the current score of the associated player
     *
     * @return the current score of the associated player
     */
    public int getScore() {
        return score;
    }

    /**
     * set the score of the associated player
     *
     * @param score the new score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * return the unique id of the associated player
     *
     * @return the unique id of the associated player
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * return the Composition of this client
     *
     * @return the Composition of this client
     */
    public Composition getComposition() {
        return composition;
    }

    public boolean replayWord(List<Tile> word) {
        for (Tile tile : word) {
            if (!bonus.isEmpty() && bonus.remove(tile) || letters.remove(tile)) {
                composition.push(tile);
            } else {
                return false;
            }
        }

        return true;
    }

    public int getValue() {
        int value = composition.getValue();

        // double the word's value if we used all the bonus letters
        if (getBonus().isEmpty()) {
            value *= 2;
        } else {
            for (Tile bonusTile : getBonus()) {
                value -= bonusTile.getValue();
            }
        }
        return value;
    }

    /**
     * compare a Board with an Object and return true if they have same fields and
     * Class
     *
     * @param o Compared Object
     * @return true if the two Object is of same class and have the same fields
     */
    public boolean equals(Object o) {
        return getClass().isInstance(o)
                && getClass() == o.getClass()
                && bonus.equals(((Board) o).bonus)
                && letters.equals(((Board) o).letters)
                && playerId == ((Board) o).playerId
                && playerName.equals(((Board) o).playerName)
                && score == ((Board) o).score
                && composition.equals(((Board) o).composition)
                && lastAction == ((Board) o).lastAction;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.composition);
        hash = 67 * hash + Objects.hashCode(this.bonus);
        hash = 67 * hash + Objects.hashCode(this.letters);
        hash = 67 * hash + this.playerId;
        hash = 67 * hash + Objects.hashCode(this.playerName);
        hash = 67 * hash + this.score;
        hash = 67 * hash + Objects.hashCode(this.lastAction);
        return hash;
    }

    public enum LAST_ACTION {
        PLAY, THROW, PASS
    }
}
