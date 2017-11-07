package pdg5.common.game;

import java.util.List;

/**
 * Class representing the model of the game.
 * It contains an inner class Board who is the letters 
 * (bonus and normal letters) manipulated by a player.
 */
public class Game {
   public enum PlayerBoard {
      PLAYER1, PLAYER2
   } 
   
   public class Board {
      private List<Tile> bonus;
      private List<Tile> letters;
      private String playerName;
      
      public Board(String playerName) {
         this.playerName = playerName;
      }
      
      public List<Tile> getBonus() {
         return bonus;
      }
      
      public List<Tile> getLetters() {
         return letters;
      }
   }
   
   private int gameId;
   private Board[] boards;
   private Composition composition;
   
   public Game(String playerName) {
      boards = new Board[PlayerBoard.values().length];
      for (Board board : boards) {
         board = new Board(playerName);
      }
   }
   
   public Game(pdg5.common.protocole.Game game) {
      
   }

   public Board getBoard(PlayerBoard player) {
      return boards[player.ordinal()];
   }
   
   
}
