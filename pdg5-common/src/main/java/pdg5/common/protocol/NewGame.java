package pdg5.common.protocol;

import pdg5.common.game.Utils;

/**
 * Class sendable to start new games. 
 * Can be send multiple times to ask or answer new games challenges.
 */
public class NewGame extends Message {

   /**
    * unique id of the player we want to fight against
    */
   private int idOpponentWished;
   
   /**
    * represent the reason why this NewGame instance as be sent see enum Type for more
    */
   private TYPE type;
   
   /**
    * This enum specify the signification of this NewGame instance
    * RANDOM    The player want to add himself to the matchmaking 
    * REQUEST   Can be used by the client or the server, 
    *           if it's the client means he want to play with a specific player, 
    *           if it's the server we annonce to the player someone wants to play with him.
    * ACCEPT    The specified player accept the challenge
    * REFUSE    The specified player refuse the challenge
    * PENDING   The matchmaking is working
    * CANCEL    Cancel the request
    */ 
   public enum TYPE {
      RANDOM, 
      REQUEST, 
      ACCEPT,  
      REFUSE,  
      PENDING,  
      CANCEL    
   };
   
   /**
    * Constructor for random opponent
    */
   public NewGame() {
      this(Utils.RANDOM_OPPONENT, TYPE.RANDOM);
   }
   
   /**
    * Constructor for specific opponent
    * 
    * @param idOpponentWished the id of the opponent, 0 reserved for a random
    * @param type objective of this instance
    */
   public NewGame(int idOpponentWished, TYPE type) {
      this.idOpponentWished = idOpponentWished;
      this.type = type;
   }

   /**
    * return the unique id of the specific opponent the client want to play with
    * 
    * @return the unique id of the specific opponent the client want to play with
    */
   public int getIdOpponentWished() {
      return idOpponentWished;
   }

   /**
    * return the objective of this instance
    * 
    * @return the objective of this instance
    */
   public TYPE getType() {
      return type;
   }

   /**
    * set the objective of this instance usefull to quickly send back an answer
    * 
    * @param type new objective of the instance
    */
   public void setType(TYPE type) {
      this.type = type;
   }
}
