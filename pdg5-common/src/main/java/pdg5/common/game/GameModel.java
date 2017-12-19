package pdg5.common.game;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Class representing the model of the game. It allow to manipulate data of the game
 * properly
 */
public class GameModel implements Serializable {

   /**
    * enum to differenciate players
    */
   public enum PlayerBoard {
      PLAYER1, PLAYER2
   }

   private int gameId; //Unique id of the game

   /**
    * unique id of the Tournament, 0 if it is not a Tournament
    */
   private int idTournament;
   private Board[] boards; //Two parts of the game one for each player
   private Composition composition; //Word in progress for this client
   private final Date creation;
   private Date lastMove;

   /**
    * Constructor
    *
    * @param boards array of two Board representing the two sides of the game
    * @param gameId unique id for the game
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
      composition = new Composition();
      lastMove = new Date();
   }

   /*
   public GameModel(pdg5.common.protocole.GameModel game) {
      gameId = game.getID();
      boards = new Board[PlayerBoard.values().length];
      int p1 = PlayerBoard.PLAYER1.ordinal();
      boards[p1] = new Board(game.getNamePlayer());
      boards[PlayerBoard.PLAYER2.ordinal()] = new Board(game.getOpponentName());
      board[PlayerBoard.PLAYER1.ordinal()]
   }*/
   /**
    * return the Composition of this client
    *
    * @return the Composition of this client
    */
   public Composition getComposition() {
      return composition;
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

   /**
    * return the unique id of this game
    *
    * @return the unique id of this game
    */
   public int getGameId() {
      return gameId;
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
         && composition.equals(((GameModel) o).composition)
         && boards[PlayerBoard.PLAYER1.ordinal()].equals(((GameModel) o).boards[PlayerBoard.PLAYER1.ordinal()])
         && boards[PlayerBoard.PLAYER2.ordinal()].equals(((GameModel) o).boards[PlayerBoard.PLAYER2.ordinal()])
         && gameId == ((GameModel) o).gameId;
   }

   /**
    * Hashcode mandatory for use of Equals
    *
    * @return
    */
   @Override
   public int hashCode() {
      int hash = 7;
      hash = 71 * hash + this.gameId;
      hash = 71 * hash + Arrays.deepHashCode(this.boards);
      hash = 71 * hash + Objects.hashCode(this.composition);
      return hash;
   }
}
