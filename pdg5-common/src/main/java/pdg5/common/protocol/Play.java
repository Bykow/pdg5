package pdg5.common.protocol;

import pdg5.common.game.Composition;

/**
 * Class sended through the connection by a client representing
 * a Composition(word) trying to be played by a client.
 */
public class Play extends Message {

    //Word trying to be played
    private Composition composition;
    //ID of the game trying to play the word
    private int gameID;
    //ID of the player trying to play the word
    private int playerID;
   
    /**
     * Constructor
     * 
     * @param composition Word played by a player.
     * @param gameID Unique ID game where the word is played
     * @param playerID Unique ID client wich play the word.
     */
    public Play(Composition composition, int gameID, int playerID) {
       this.composition = composition;
       this.gameID = gameID;
       this.playerID = playerID;
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

   /**
    * return the unique id of the player trying to play
    * 
    * @return the unique id of the player trying to play
    */
   public int getPlayerID() {
      return playerID;
   }
}
