package pdg5.common.protocol;

/**
 * @author Jimmy Verdasca
 * 
 * Class sent through the connection  by the client 
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
   
   /**
    * signification of this Friend instance (see enum Friend.TYPE)
    */
   private TYPE type;
   
   /**
    * unique id of the player concerned by the action
    */
   private int idTargettedPlayer;

   /**
    * Constructor 
    * 
    * @param type objective of the message
    * @param idTargettedPlayer unique ID of the player concerned by the objective
    */
   public Friend(TYPE type, int idTargettedPlayer) {
      this.type = type;
      this.idTargettedPlayer = idTargettedPlayer;
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
