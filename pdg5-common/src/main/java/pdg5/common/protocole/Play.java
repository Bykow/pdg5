package pdg5.common.protocole;

import java.io.Serializable;

/**
 * @author Maxime Guillod
 */
public class Play implements Serializable {

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

}
