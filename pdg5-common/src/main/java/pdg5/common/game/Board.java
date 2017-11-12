/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing one side of the game (one per player)
 * It contains the letters of one player (bonus and normal letters), and a score
 */

public class Board implements Serializable{

   private List<Tile> bonus; // bonus letters
   private List<Tile> letters; // normal letters
   private final int playerId; // id of the player associated
   private final String playerName; // name of the player associated
   private int score; // score of the player associated

   /**
    * Constructor
    * 
    * @param playerName name of the associated player
    * @param playerId unique id of the associated player
    */
   public Board(String playerName, int playerId) {
      this.playerName = playerName;
      this.playerId = playerId;
      
      bonus = new ArrayList<>();
      letters = new ArrayList<>();
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
    * return the list of normal letters
    * 
    * @return the list of normal letters
    */
   public List<Tile> getLetters() {
      return letters;
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
    * return the unique id of the associated player
    * 
    * @return the unique id of the associated player
    */
   public int getPlayerId() {
      return playerId;
   }
   
   /**
    * set the list of bonus letters to a new one given
    * 
    * @param bonus list of the new Tiles
    */
   public void setBonus(List<Tile> bonus) {
      this.bonus = bonus;
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
    * set the list of normal letters to a new one given
    * 
    * @param letters list of the new Tiles
    */
   public void setLetters(List<Tile> letters) {
      this.letters = letters;
   }
   
   /**
    * compare a Board with an Object and 
    * return true if they have same fields and Class
    * 
    * @param o Compared Object
    * @return true if the two Object is of same class and have the same fields
    */
   public boolean equals(Object o) {
      return getClass().isInstance(o) &&
              getClass() == o.getClass() &&
              bonus.equals(((Board) o).bonus) &&
              letters.equals(((Board) o).letters) &&
              playerId == ((Board) o).playerId &&
              playerName.equals(((Board) o).playerName) &&
              score == ((Board) o).score;
   }
}
