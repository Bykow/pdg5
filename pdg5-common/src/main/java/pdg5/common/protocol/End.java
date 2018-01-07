package pdg5.common.protocol;

/**
 * Class sent through the connection by the server, 
 * to indicate the result of an ended game.
 * 
 * @author Jimmy Verdasca
 */
public class End extends Message {
   
   /**
    * result possibilities
    */
   public enum RESULT {
      WIN, LOSE, EQUALITY
   }
   
   /**
    * unique id of the game we are updating
    */
   private int idGame;
   
   /**
    * current result of the game
    */
   private final RESULT result;
   
   /**
    * Constructor
    * 
    * @param result finish result of the game
    * @param idGame unique id of the game
    */
   public End(RESULT result, int idGame) {
      this.result = result;
      this.idGame = idGame;
   }

   /**
    * return the result of the game
    * 
    * @return the result of the game
    */
   public RESULT getResult() {
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
   
   
}
