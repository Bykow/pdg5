package pdg5.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class representing the model of the game. It allow to manipulate data of the game
 * properly
 */
public class GameModel implements Serializable {

    /**
     * Date when the game as been created
     */
    private final Date creation;
    private int scoreLastWordPlayed;
    private List<Tile> lastWordPlayed;
    /**
     * Unique id of the game
     */
    private int gameId;
    /**
     * current state of the game
     */
    private State state;
    /**
     * unique id of the Tournament, 0 if it is not a Tournament
     */
    private int idTournament;
    /**
     * Two parts of the game one for each player
     */
    private Board[] boards;
    /**
     * Date of the last word played by a player
     */
    private Date lastMove;

    /**
     * Constructor
     *
     * @param boards       array of two Board representing the two sides of the game
     * @param gameId       unique id for the game
     * @param creationDate Date of creation
     * @param idTournament Tournament Id
     * @throws IllegalArgumentException if the array has wrong length, should be 2
     */
    public GameModel(Board[] boards, int gameId, Date creationDate, int idTournament) throws IllegalArgumentException {
        if (boards.length != PlayerBoard.values().length) {
            throw new IllegalArgumentException("There must be exactly two boards in the array");
        }
        this.boards = boards;
        this.gameId = gameId;
        this.creation = creationDate;
        this.idTournament = idTournament;
        lastMove = new Date();
        state = State.IN_PROGRESS;
        lastWordPlayed = new ArrayList<>();
        scoreLastWordPlayed = 0;
    }

    /**
     * Constructor
     *
     * @param game Game used to build the GameModel
     */
    public GameModel(pdg5.common.protocol.Game game) {
        gameId = game.getID();
        boards = new Board[PlayerBoard.values().length];
        int p1 = PlayerBoard.PLAYER1.ordinal();
        int p2 = PlayerBoard.PLAYER2.ordinal();
        boards[p1] = new Board(game.getNamePlayer(), 1);
        boards[p2] = new Board(game.getOpponentName(), 2);
        boards[p1].setLetters(game.getAddedTile());
        boards[p1].setScore(game.getScore());
        boards[p2].setScore(game.getOpponentScore());

        if (game.isYourTurn()) {
            boards[p1].setBonus(game.getBonusLetters());
        } else {
            boards[p1].setBonus(game.getBonusLetters());
        }

        creation = game.getCreated();
        idTournament = game.getTournament();
        lastMove = game.getLastActivity();
        lastWordPlayed = game.getLastWordPlayed();
        scoreLastWordPlayed = game.getScoreLastWordPlayed();
        state = game.getState();

    }

    /**
     * Return the Board of the given player
     *
     * @param player that we want the board
     * @return the Board of the given player
     */
    public Board getBoard(PlayerBoard player) {
        return boards[player.ordinal()];
    }

    /**
     * return the board of the opponent given id player
     *
     * @param playerId id of player
     * @return the board of the opponent given id player
     * @throws IllegalArgumentException if the id given isn't one of the players
     */
    public Board getOpponentBoard(int playerId) throws IllegalArgumentException {

        Board player1Board = boards[PlayerBoard.PLAYER1.ordinal()];
        Board player2Board = boards[PlayerBoard.PLAYER2.ordinal()];

        if (playerId == player1Board.getPlayerId()) {
            return player2Board;
        } else if (playerId == player2Board.getPlayerId()) {
            return player1Board;
        }
        throw new IllegalArgumentException("the playerId is not in the game");
    }

    /**
     * Return the Board of the given id player
     *
     * @param playerId that we want the board
     * @return the Board of the given id player
     * @throws IllegalArgumentException if the given id isn't one of the players
     */
    public Board getBoardById(int playerId) throws IllegalArgumentException {
        for (Board board : boards) {
            if (board.getPlayerId() == playerId) {
                return board;
            }
        }
        throw new IllegalArgumentException("the playerId is not in the game");
    }

    public Board getOpponentBoardById(int playerId) {
        Board opponentBoard = null;
        Boolean isPlayer = false;
        for (Board board : boards) {
            if (board.getPlayerId() != playerId) {
                opponentBoard = board;
            } else {
                isPlayer = true;
            }
        }
        if (isPlayer) {
            return opponentBoard;
        } else {
            throw new IllegalArgumentException("the playerId is not in the game");
        }
    }

    /**
     * return the unique id of this game
     *
     * @return the unique id of this game
     */
    public int getGameId() {
        return gameId;
    }

    public State getState() {
        return state;
    }

    /**
     * set the current state of the game
     *
     * @param state the new state of the game
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * return the date we created this game
     *
     * @return the date we created this game
     */
    public Date getCreation() {
        return creation;
    }

    /**
     * return the date when the last player played a word
     *
     * @return the date when the last player played a word
     */
    public Date getLastMove() {
        return lastMove;
    }

    /**
     * return the unique id of the associated tournament. It's 0 if it is not
     * associated.
     *
     * @return the unique id of the associated tournament
     */
    public int getIdTournament() {
        return idTournament;
    }

    /**
     * return the last word that a player has play
     *
     * @return the last word that a player has play
     */
    public List<Tile> getLastWordPlayed() {
        return lastWordPlayed;
    }

    public void setLastWordPlayed(List<Tile> lastWordPlayed) {
        this.lastWordPlayed = lastWordPlayed;
    }

    /**
     * return the score of the last played word by a player
     *
     * @return the score of the last played word by a player
     */
    public int getScoreLastWordPlayed() {
        return scoreLastWordPlayed;
    }

    public void setScoreLastWordPlayed(int scoreLastWordPlayed) {
        this.scoreLastWordPlayed = scoreLastWordPlayed;
    }

    /**
     * compare a GameModel with an Object and return true if they have same fields and
     * Class
     *
     * @param o Compared Object
     * @return true if the two Object is of same class and have the same fields
     */
    @Override
    public boolean equals(Object o) {
        return getClass().isInstance(o)
                && getClass() == o.getClass()
                && boards[PlayerBoard.PLAYER1.ordinal()].equals(((GameModel) o).boards[PlayerBoard.PLAYER1.ordinal()])
                && boards[PlayerBoard.PLAYER2.ordinal()].equals(((GameModel) o).boards[PlayerBoard.PLAYER2.ordinal()])
                && gameId == ((GameModel) o).gameId;
    }

    /**
     * Hashcode mandatory for use of Equals
     *
     * @return the hash as int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.gameId;
        hash = 71 * hash + Arrays.deepHashCode(this.boards);
        return hash;
    }

    /**
     * enum to differenciate players
     */
    public enum PlayerBoard {
        PLAYER1, PLAYER2
    }

    /**
     * enum to identifiate the State of the game
     */
    public enum State {
        IN_PROGRESS, END_MODE, FINISHED, OUTDATED
    }
}
