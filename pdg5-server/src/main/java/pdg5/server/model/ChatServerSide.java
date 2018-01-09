package pdg5.server.model;

/**
 *
 * @author Jimmy Verdasca
 */
public class ChatServerSide {
   
   private long timeStamp;
   private int idSender;
   private String message;
   private int idGame;

   /**
    * Constructor
    * 
    * @param timeStamp
    * @param idSender
    * @param message
    * @param idGame 
    */
   public ChatServerSide(long timeStamp, int idSender, String message, int idGame) {
      this.timeStamp = timeStamp;
      this.idSender = idSender;
      this.message = message;
      this.idGame = idGame;
   }

   public int getIdGame() {
      return idGame;
   }

   public int getIdSender() {
      return idSender;
   }

   public String getMessage() {
      return message;
   }

   public long getTimeStamp() {
      return timeStamp;
   }
}
