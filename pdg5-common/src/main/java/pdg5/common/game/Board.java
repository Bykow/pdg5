/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.game;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jimmy Verdasca
 */

public class Board implements Serializable{

   private List<Tile> bonus;
   private List<Tile> letters;
   private final int playerId;
   private final String playerName;
   private int score;

   public Board(String playerName, int playerId) {
      this.playerName = playerName;
      this.playerId = playerId;
   }

   public List<Tile> getBonus() {
      return bonus;
   }

   public List<Tile> getLetters() {
      return letters;
   }

   public String getPlayerName() {
      return playerName;
   }

   public int getScore() {
      return score;
   }

   public int getPlayerId() {
      return playerId;
   }
   
   public void setBonus(List<Tile> bonus) {
      this.bonus = bonus;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setLetters(List<Tile> letters) {
      this.letters = letters;
   }
}
