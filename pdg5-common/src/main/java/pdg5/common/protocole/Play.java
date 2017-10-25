package pdg5.common.protocole;

import pdg5.common.game.Composition;

/**
 * Class sended through the connection representing
 * a Composition trying to be played by a client.
 */
public class Play implements IServerRequest {

    //Word trying to be played
    private Composition composition;
    //ID of the game trying to play the word
    private int gameID;
    //ID of the player trying to play the word
    private int playerID;
   
    public Play(Composition composition, int gameID, int playerID) {
       this.composition = composition;
       this.gameID = gameID;
       this.playerID = playerID;
    }

    /**
     * Method immediately used when the server or client receive the object.
     * The server will check :
     *         -the validity of the word in the dictionnary
     *         -If the client isn't cheating 
     *                 (for exemple he has the letters to build this word)
     * Then if the move is valid, he will inform the other player.
     * Else he will wait for a valide move from the same player.
     */
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
