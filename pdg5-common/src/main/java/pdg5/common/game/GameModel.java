package pdg5.common.game;

import java.io.Serializable;

/**
 * Class representing the model of the game. 
 * It allow to manipulate data of the game properly
 */
public class GameModel implements Serializable {

   /**
    * enum to differenciate players
    */
   public enum PlayerBoard {
      PLAYER1, PLAYER2
   }

   private int gameId; //Unique id of the game
   private Board[] boards; //Two parts of the game one for each player
   private Composition composition; //Word in progress for this client

   /**
    * Constructor
    * @param boards array of two Board representing the two sides of the game
    * @param gameId unique id for the game
    * @throws IllegalArgumentException if the array has wrong length, 
    *                                  should be 2
    */
   public GameModel(Board[] boards, int gameId) throws IllegalArgumentException{
      if(boards.length != PlayerBoard.values().length){
         throw new IllegalArgumentException("There must be exactly two boards in the array");
      }
      this.boards = boards;
      this.gameId = gameId;
      composition = new Composition();
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
    * @param player that we want the board
    * @return the Board of the given player
    */
   public Board getBoard(PlayerBoard player) {
      return boards[player.ordinal()];
   }

   /**
    * Return the Board of the given id player
    * @param playerId that we want the board
    * @return the Board of the given id player
    * @throws IllegalArgumentException if the given id isn't one of the players
    */
   public Board getBoardById(int playerId) {
      for (Board board : boards) {
         if (board.getPlayerId() == playerId) {
            return board;
         }
      }
      throw new IllegalArgumentException("the playerId is not in the game");
   }
   
   /**
    * compare a GameModel with an Object and 
    * return true if they have same fields and Class
    * 
    * @param o Compared Object
    * @return true if the two Object is of same class and have the same fields
    */
   public boolean equals(Object o) {
      return getClass().isInstance(o) &&
              getClass() == o.getClass() &&
              composition.equals(((GameModel) o).composition) &&
              boards[0].equals(((GameModel) o).boards[0]) &&
              boards[1].equals(((GameModel) o).boards[1]) &&
              gameId == ((GameModel) o).gameId;
   }
}