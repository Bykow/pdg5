package pdg5.common.protocol;

/**
 * @author Jimmy Verdasca
 * 
 * Class sended through the connection  by the client 
 * to let him manipulate his friend list or black list.
 */
public class Friend extends Message {
   
   // This enum specify the signification of this Friend instance
   public enum TYPE {
      ADD_FRIEND,       // Add a favorite player
      REMOVE_FRIEND,    // Remove a favorite player
      ADD_BLACKLIST,    // Add a player to the BlackList
      REMOVE_BLACKLIST  // Remove a player to the BlackList
   }
   
   private TYPE type;
   private int idPlayer;
   private int idTargettedPlayer;

   /**
    * Constructor 
    * 
    * @param type objective of the message
    * @param idPlayer unique ID of the player who sended this Message
    * @param idTargettedPlayer unique ID of the player concerned by the objective
    */
   public Friend(TYPE type, int idPlayer, int idTargettedPlayer) {
      this.type = type;
      this.idPlayer = idPlayer;
      this.idTargettedPlayer = idTargettedPlayer;
   }

   /**
    * return the unique ID of the player who sended this Message
    * 
    * @return the unique ID of the player who sended this Message
    */
   public int getIdPlayer() {
      return idPlayer;
   }

   /**
    * return the unique ID of the player concerned by the objective
    * 
    * @return the unique ID of the player concerned by the objective
    */
   public int getIdTargettedPlayer() {
      return idTargettedPlayer;
   }

   /**
    * return the objective of the message
    * 
    * @return the objective of the message 
    */
   public TYPE getType() {
      return type;
   }
}
