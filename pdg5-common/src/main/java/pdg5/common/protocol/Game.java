package pdg5.common.protocol;

import java.util.Arrays;
import pdg5.common.game.Board;
import pdg5.common.game.GameModel.State;
import pdg5.common.game.Tile;

import java.util.Date;
import java.util.List;
import pdg5.common.game.Composition.Square;
import pdg5.common.game.Result;

/**
 * @author Maxime Guillod
 *
 * Class sended through the connection  by the server representing
 * a full game with two players.
 */
public class Game extends Message {

    private final int ID;
    private final Date created;
    private final Date lastActivity;
    private final int tournament;
    private final Board board;
    private final Board opponentBoard;
    private final int nbLeftTile;
    private final List<Tile> lastWordPlayed;
    private final int scoreLastWordPlayed;
    private final boolean yourTurn;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private Result result;

    private State state;

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
     * @return the unique ID of a game
     */
    public int getID() {
        return ID;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public int getTournament() {
        return tournament;
    }

    public Board getBoard() {
        return board;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public int getScore() {
        return board.getScore();
    }

    public String getNamePlayer() {
        return board.getPlayerName();
    }

    public int getOpponentScore() {
        return opponentBoard.getScore();
    }

    public String getOpponentName() {
        return opponentBoard.getPlayerName();
    }

    public int getNbLeftTile() {
        return nbLeftTile;
    }

    public List<Tile> getAddedTile() {
        return board.getLetters();
    }

    public List<Tile> getBonusLetters() {
        return board.getBonus();
    }

    public List<Tile> getOpponentBonusLetters() {
        return opponentBoard.getBonus();
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public List<Tile> getLastWordPlayed() {
        return lastWordPlayed;
    }

    public int getScoreLastWordPlayed() {
        return scoreLastWordPlayed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Square> getSquare(){
        return Arrays.asList(board.getComposition().getSquare());
    }

    public List<Square> getOpponentSquare(){
        return Arrays.asList(opponentBoard.getComposition().getSquare());
    }

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
