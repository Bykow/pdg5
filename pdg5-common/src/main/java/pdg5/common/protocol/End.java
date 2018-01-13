package pdg5.common.protocol;

import pdg5.common.game.Result;

/**
 * Class sent through the connection by the server
 * to indicate the result of an ended game
 */
public class End extends Message {
   
   /**
    * unique id of the game we are updating
    */
   private int idGame;
   
   /**
    * current result of the game
    */
   private final Result result;
   
   /**
    * Constructor
    * 
    * @param result finish result of the game
    * @param idGame unique id of the game
    */
   public End(Result result, int idGame) {
      this.result = result;
      this.idGame = idGame;
   }

   /**
    * return the result of the game
    * 
    * @return the result of the game
    */
   public Result getResult() {
      return result;
   }

   /**
    * return unique id of the game we are updating
    * 
    * @return unique id of the game we are updating
    */
   public int getIdGame() {
      return idGame;
   }
   
   /**
    * override the print of this class, for easier debug in client
    * it return a string with only the result
    * 
    * @return a string with only the result
    */
   @Override
   public String toString() {
      return result.toString();
   }
}
