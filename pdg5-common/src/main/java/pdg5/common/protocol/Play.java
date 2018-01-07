package pdg5.common.protocol;

import pdg5.common.game.Composition;

/**
 * Class sent through the connection by a client representing
 * a Composition(word) trying to be played by a client.
 */
public class Play extends Message {

    /**
     * Word trying to be played
     */
    private Composition composition;
    
    /**
     * ID of the game trying to play the word
     */
    private int gameID;
   
    /**
     * Constructor
     * 
     * @param composition Word played by a player.
     * @param gameID Unique ID game where the word is played
     */
    public Play(Composition composition, int gameID) {
       this.composition = composition;
       this.gameID = gameID;
    }

    /**
     * return the Composition containing the word we try to play
     * 
     * @return the Composition containing the word we try to play
     */
   public Composition getComposition() {
      return composition;
   }

   /**
    * return the unique id of the game we try the move
    * 
    * @return the unique id of the game we try the move
    */
   public int getGameID() {
      return gameID;
   }
}
