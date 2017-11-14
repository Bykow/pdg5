package pdg5.common.game;

import java.io.Serializable;
import java.util.List;

/**
 * Class representing the model of the game. It contains an inner class Board
 * who is the letters (bonus and normal letters) manipulated by a player.
 */
public class GameModel implements Serializable {

   public enum PlayerBoard {
      PLAYER1, PLAYER2
   }

   private int gameId;
   private Board[] boards;
   private Composition composition;

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
   
   public Board getBoard(PlayerBoard player) {
      return boards[player.ordinal()];
   }

   public Board getBoardById(int playerId) {
      for (Board board : boards) {
         if (board.getPlayerId() == playerId) {
            return board;
         }
      }
      throw new IllegalArgumentException("the playerId is not in the game");
   }
}