/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg5.common.protocol;

/**
 * class sent through the connection by a client 
 * to ask to delete from his list of game the game
 * 
 * @author Jimmy Verdasca
 */
public class DeleteGame extends Message {
   
   /**
    * Unique id of the game we want to delete
    */
   private int idGame;

   /**
    * Unique id of the game we want to delete
    * @param idGame Unique id of the game we want to delete
    */
   public DeleteGame(int idGame) {
      this.idGame = idGame;
   }

   /**
    * return the unique id of the game we want to delete
    * 
    * @return Unique id of the game we want to delete
    */
   public int getIdGame() {
      return idGame;
   }
   
   
}
