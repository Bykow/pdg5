package pdg5.common.protocol;

import pdg5.common.game.Utils;

/**
 * Class sendable to the serveur by a client to ask for start a new game.
 */
public class NewGame extends Message {

   private int idPlayerAsking;
   private int idOpponentWished = Utils.RANDOM_OPPONENT;
   
   /**
    * Constructor for random opponent
    * 
    * @param idPlayerAsking our own unique id to ask a new game
    */
   public NewGame(int idPlayerAsking) {
      this.idPlayerAsking = idPlayerAsking;
   }
   
   /**
    * Constructor for specific opponent
    * 
    * @param idPlayerAsking
    * @param idOpponentWished 
    */
   public NewGame(int idPlayerAsking, int idOpponentWished) {
      this(idPlayerAsking);
      this.idOpponentWished = idOpponentWished;
   }
   
   /**
    * return the unqiue of the player asking for a new game
    * 
    * @return the unqiue of the player asking for a new game
    */
   public int getIdPlayerAsking() {
      return idPlayerAsking;
   }

   /**
    * return the unique id of the specific opponent the client want to play with
    * 
    * @return the unique id of the specific opponent the client want to play with
    */
   public int getIdOpponentWished() {
      return idOpponentWished;
   }
   
}
