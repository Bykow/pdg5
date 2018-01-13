package pdg5.server.model;

import pdg5.common.game.Composition;
import pdg5.common.game.Composition.Square;
import pdg5.common.game.Utils;

import java.io.Serializable;
import java.util.Random;

/**
 * This class manage the turn for a game
 */
public class TurnManager implements Serializable {

    private final static Composition.Square[][] POSSIBLE_ARRAY_SQUARES = {
            {Square.DOUBLE, Square.NORMAL, Square.NORMAL, Square.W, Square.NORMAL, Square.W, Square.BONUS},
            {Square.NORMAL, Square.NORMAL, Square.W, Square.NORMAL, Square.W, Square.TRIPLE, Square.BONUS},
            {Square.W, Square.TRIPLE, Square.W, Square.NORMAL, Square.NORMAL, Square.NORMAL, Square.BONUS},
            {Square.NORMAL, Square.W, Square.NORMAL, Square.W, Square.DOUBLE, Square.NORMAL, Square.BONUS},
            {Square.NORMAL, Square.NORMAL, Square.DOUBLE, Square.W, Square.W, Square.NORMAL, Square.BONUS},
            {Square.W, Square.NORMAL, Square.NORMAL, Square.TRIPLE, Square.NORMAL, Square.W, Square.BONUS}
    };

    private final static Composition.Square[] FIRST_SQUARE_OF_TOURNAMENT = {
            Square.NORMAL,
            Square.NORMAL,
            Square.NORMAL,
            Square.NORMAL,
            Square.NORMAL,
            Square.NORMAL,
            Square.BONUS
    };

    private int idPlayer1;
    private int idPlayer2;
    private int currentPlayer;
    private Random rand;
    private int turn;

    /**
     * Constructor against an IA
     *
     * @param idPlayer1 unique id of the client
     * @param seed      random seed for this game
     */
    public TurnManager(int idPlayer1, long seed) {
        this(idPlayer1, Utils.ID_AI, seed);
    }

    /**
     * Constructor for two players
     *
     * @param idPlayer1 unique id of a client
     * @param idPlayer2 unique id of other client
     * @param seed      random seed for this game
     */
    public TurnManager(int idPlayer1, int idPlayer2, long seed) {
        rand = new Random(seed);
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.turn = 0;

        // random for first player
        if (idPlayer2 == Utils.ID_AI || rand.nextBoolean()) {
            this.currentPlayer = idPlayer1;
        } else {
            this.currentPlayer = idPlayer2;
        }
    }

    /**
     * return true if the given id is the current player that have to play a
     * Composition
     *
     * @param idPlayer unique id of the client we are checking the turn
     * @return true if the given id is the current player that have to play a
     * Composition
     */
    public boolean isCurrentPlayer(int idPlayer) {
        return idPlayer == currentPlayer;
    }

    /**
     * the current id player that have to play a Composition
     *
     * @return the current id player that have to play a Composition
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * signal to this manager that the turn ended then he can update datas himself
     *
     * @return the new player unique id
     */
    public int turnEnded() {
        if (currentPlayer == idPlayer1) {
            currentPlayer = idPlayer2;
        } else {
            currentPlayer = idPlayer1;
            turn++;
        }
        return currentPlayer;
    }

    /**
     * return the array of Square associated to the unique id player given
     *
     * @param idPlayer unique id of a player
     * @return the array of Square associated to the unique id player given
     * @throws IllegalArgumentException if the id isn't one of the players
     */
    public Composition.Square[] getSquares(int idPlayer) throws IllegalArgumentException {
        checkIdPlayerInGame(idPlayer);
        int size = POSSIBLE_ARRAY_SQUARES.length;
        int position;
        if (isAnAIGame() && idPlayer == idPlayer2) {
            position = (size - 2 - turn) % size;
        } else if (idPlayer == idPlayer2) {
            position = (size - 1 - turn) % size;
        } else {
            position = turn % size;
        }

        Square[] squares;

        // prevents Server Crash in case of problem
        try {
            squares = POSSIBLE_ARRAY_SQUARES[position];
        } catch (IndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
            squares = POSSIBLE_ARRAY_SQUARES[0];
        }

        return squares;
    }

    /**
     * return if true if player2 is an AI, else false
     *
     * @return if true if player2 is an AI, else false
     */
    private boolean isAnAIGame() {
        return idPlayer2 == Utils.ID_AI;
    }

    /**
     * check if an unique id player is one of both participant of the game
     *
     * @param idPlayer unique id we want to check
     * @throws IllegalArgumentException if the given id isn't a participant
     */
    private void checkIdPlayerInGame(int idPlayer) throws IllegalArgumentException {
        if (idPlayer != idPlayer1 && idPlayer != idPlayer2) {
            throw new IllegalArgumentException("the given id is not one of the players");
        }
    }
}
