package pdg5.common.protocol;

/**
 * Class sent by the client to the server to tell him he want to pass or throw
 * depending on the context of the game
 */
public class Pass extends Message {
   
   /**
    * unique id of the game a player wants to pass
    */
   private int idGame;
   
   /**
    * Constructor
    * 
    * @param idGame unique id of the game a player wants to pass
    */
   public Pass(int idGame) {
      this.idGame = idGame;
   }

   /**
    * return the unique id of the game a player wants to pass
    * 
    * @return the unique id of the game a player wants to pass
    */
   public int getIdGame() {
      return idGame;
   }
}
