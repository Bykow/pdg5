package pdg5.common.protocol;

/**
 *
 * @author Jimmy Verdasca
 */
public class End extends Message {
   public enum RESULT {
      WIN, LOSE, EQUALITY
   }
   
   private final RESULT result;
   
   public End(RESULT result) {
      this.result = result;
   }

   public RESULT getResult() {
      return result;
   }
}
