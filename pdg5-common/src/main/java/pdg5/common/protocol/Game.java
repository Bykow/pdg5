package pdg5.common.protocol;

import pdg5.common.game.Board;
import pdg5.common.game.Composition.Square;
import pdg5.common.game.GameModel.State;
import pdg5.common.game.Result;
import pdg5.common.game.Tile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class sent through the connection by the server representing a full game with two
 * players in the point of view of one specific player
 */
public class Game extends Message {

    /**
     * unique id of the game
     */
    private final int ID;

    /**
     * date of creation of the original GameModel
     */
    private final Date created;

    /**
     * date of the last action of a player (throw, play pass)
     */
    private final Date lastActivity;

    /**
     * unique id of the tournament associated
     */
    private final int tournament;

    /**
     * board of the player who will get this point of view
     */
    private final Board board;

    /**
     * board of the opponent player (don't contain Tiles)
     */
    private final Board opponentBoard;

    /**
     * number of Tiles left in the Tile Stack
     */
    private final int nbLeftTile;

    /**
     * Tiles of the last word played (in order)
     */
    private final List<Tile> lastWordPlayed;

    /**
     * the tabulated score with the last word played
     */
    private final int scoreLastWordPlayed;

    /**
     * indicate if it's the turn's player
     */
    private final boolean yourTurn;

    /**
     * the current State of the game (IN_PROGRESS, END_MODE, FINISHED, OUTDATED)
     */
    private State state;

    /**
     * if the game is finished allow to know if the player Won or Lost
     */
    private Result result;

    /**
     * Constructor
     *
     * @param ID                  unique id of the game
     * @param created             date of creation of the original GameModel
     * @param lastActivity        date of the last action of a player (throw, play pass)
     * @param tournament          unique id of the tournament associated
     * @param board               board of the player who will get this point of view
     * @param opponentBoard       board of the opponent player (don't contain Tiles)
     * @param nbLeftTile          number of Tiles left in the Tile Stack
     * @param lastWordPlayed      Tiles of the last word played (in order)
     * @param scoreLastWordPlayed the tabulated score with the last word played
     * @param yourTurn            indicate if it's the turn's player
     * @param state               the current State of the game (IN_PROGRESS, END_MODE, FINISHED,
     *                            OUTDATED)
     */
    public Game(int ID, Date created, Date lastActivity,
                int tournament, Board board, Board opponentBoard, int nbLeftTile,
                List<Tile> lastWordPlayed, int scoreLastWordPlayed, boolean yourTurn, State state) {
        this.ID = ID;
        this.created = created;
        this.lastActivity = lastActivity;
        this.tournament = tournament;
        this.board = board;
        this.opponentBoard = opponentBoard;
        this.nbLeftTile = nbLeftTile;
        this.lastWordPlayed = lastWordPlayed;
        this.scoreLastWordPlayed = scoreLastWordPlayed;
        this.yourTurn = yourTurn;
        this.state = state;
        this.result = Result.NONE;
    }

    /**
     * return the unique ID of a game
     *
     * @return the unique ID of a game
     */
    public int getID() {
        return ID;
    }

    /**
     * return the date of creation of the original GameModel
     *
     * @return the date of creation of the original GameModel
     */
    public Date getCreated() {
        return created;
    }

    /**
     * return the date of the last action of a player (throw, play pass)
     *
     * @return the date of the last action of a player (throw, play pass)
     */
    public Date getLastActivity() {
        return lastActivity;
    }

    /**
     * return the unique id of the tournament associated
     *
     * @return the unique id of the tournament associated
     */
    public int getTournament() {
        return tournament;
    }

    /**
     * returnt the board of the player who will get this point of view
     *
     * @return the board of the player who will get this point of view
     */
    public Board getBoard() {
        return board;
    }

    /**
     * return the board of the opponent player (don't contain Tiles)
     *
     * @return the board of the opponent player (don't contain Tiles)
     */
    public Board getOpponentBoard() {
        return opponentBoard;
    }

    /**
     * return the score of the player who will get this point of view
     *
     * @return the score of the player who will get this point of view
     */
    public int getScore() {
        return board.getScore();
    }

    /**
     * return the name of the player who will get this point of view
     *
     * @return the name of the player who will get this point of view
     */
    public String getNamePlayer() {
        return board.getPlayerName();
    }

    /**
     * return the score of the opponent player
     *
     * @return the score of the opponent player
     */
    public int getOpponentScore() {
        return opponentBoard.getScore();
    }

    /**
     * return the name of the opponent player
     *
     * @return the name of the opponent player
     */
    public String getOpponentName() {
        return opponentBoard.getPlayerName();
    }

    /**
     * return the number of Tiles left in the Tile Stack
     *
     * @return the number of Tiles left in the Tile Stack
     */
    public int getNbLeftTile() {
        return nbLeftTile;
    }

    /**
     * return the letters of the player who will get this point of view without the
     * bonus letters
     *
     * @return the letters of the player who will get this point of view without the
     * bonus letters
     */
    public List<Tile> getLetters() {
        return board.getLetters();
    }

    /**
     * return the bonus letters of the player who will get this point of view
     *
     * @return the bonus letters of the player who will get this point of view
     */
    public List<Tile> getBonusLetters() {
        List<Tile> bonusTiles = board.getBonus();
        bonusTiles.forEach((t) -> t.setBonus(true));
        return bonusTiles;
    }

    /**
     * return the bonus letters of the opponent player
     *
     * @return the bonus letters of the opponent player
     */
    public List<Tile> getOpponentBonusLetters() {
        List<Tile> bonusTiles = opponentBoard.getBonus();
        bonusTiles.forEach((t) -> t.setBonus(true));
        return bonusTiles;
    }

    /**
     * return the Tiles of the last word played (in order)
     *
     * @return the Tiles of the last word played (in order)
     */
    public List<Tile> getLastWordPlayed() {
        return lastWordPlayed;
    }

    /**
     * return the tabulated score with the last word played
     *
     * @return the tabulated score with the last word played
     */
    public int getScoreLastWordPlayed() {
        return scoreLastWordPlayed;
    }

    /**
     * indicate if it's the turn's player
     *
     * @return a boolean that indicate if it's the turn's player
     */
    public boolean isYourTurn() {
        return yourTurn;
    }

    /**
     * return the current State of the game (IN_PROGRESS, END_MODE, FINISHED,
     * OUTDATED)
     *
     * @return the current State of the game (IN_PROGRESS, END_MODE, FINISHED,
     * OUTDATED)
     */
    public State getState() {
        return state;
    }

    /**
     * allow to change the current State of the game (IN_PROGRESS, END_MODE,
     * FINISHED, OUTDATED) improve the rapidity of answer of the server
     *
     * @param state the new current State of the game (IN_PROGRESS, END_MODE,
     *              FINISHED, OUTDATED)
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * return the squares of the player who will get this point of view
     *
     * @return the squares of the player who will get this point of view
     */
    public List<Square> getSquare() {
        return Arrays.asList(board.getComposition().getSquare());
    }

    /**
     * return the squares of the opponent player
     *
     * @return the squares of the opponent player
     */
    public List<Square> getOpponentSquare() {
        return Arrays.asList(opponentBoard.getComposition().getSquare());
    }

    /**
     * return the result of the game for this point of view
     *
     * @return the result of the game for this point of view
     */
    public Result getResult() {
        return result;
    }

    /**
     * allow to change the result of the game
     *
     * @param result the new result of the game
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * override the print of this class for an easier debug in client
     *
     * @return a String representing the game status
     */
    @Override
    public String toString() {
        String output = "";
        output += "Player Name:     " + getNamePlayer() + "\n";
        output += "Player Score:    " + getScore() + "\n";
        output += "Opponent Name:   " + getOpponentName() + "\n";
        output += "Opponent Score:  " + getOpponentScore() + "\n";
        output += "isYourTurn:      " + yourTurn + "\n";
        output += "Last Word Played " + lastWordPlayed.toString() + "\n";
        output += "Bonus            " + getBonusLetters().toString() + "\n";
        output += "Opponent Bonus   " + getOpponentBonusLetters().toString();

        return output;
    }
}
