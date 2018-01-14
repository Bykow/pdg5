package pdg5.server.process;

import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientAlreadyConnected;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * class built by the server when he receive the request SignIn
 * Then the server wil try to sign in the client who sent this request
 */
public class ProcessSignIn implements GenericProcess {

   /**
    * the original SignIn request received
    */
    private final SignIn signIn;
    
    /**
     * to store/get users datas in the database
     */
    private final ManageUser managerUser;
    
    /**
     * manager of the user who are connected
     */
    private final ServerActiveUser activeUser;
    
    /**
     * GameController that will send all the games informations if the sign in succed to the client
     */
    private final GameController gameController;
    
    /**
     * manager of the manager of the socket where we received the SignIn
     */
    private final ClientHandler clientHandler;

    /**
     * Constructor
     * 
     * @param signIn the original SignIn request received
     * @param managerUser to store/get users datas in the database
     * @param activeUser manager of the user who are connected
     * @param gameController GameController that will send all the games informations if the sign in succed to the client
     * @param clientHandler manager of the manager of the socket where we received the SignIn
     */
    public ProcessSignIn(SignIn signIn, ManageUser managerUser,
        ServerActiveUser activeUser, GameController gameController,
        ClientHandler clientHandler) {
        this.signIn = signIn;
        this.managerUser = managerUser;
        this.activeUser = activeUser;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    /**
     * try to log in the client who requested it with the informations he gave us
     * 
     * @return a SignInOk if succeed, an ErrorMessage otherwise
     */
    @Override
    public Message execute() {
        if (managerUser.isCorrectPassword(signIn.getUsername(), signIn.getPassword())) {

            int idUser = managerUser.getUserByUsername(signIn.getUsername()).getId();

            try {
                activeUser.add(idUser, clientHandler);
                clientHandler.setPlayerId(idUser);

                // Will be receive by the SignInController
                clientHandler.addToQueue(new SignInOK());

                gameController.dataLoad(idUser);

                //clientHandler.addToQueue(new Dictionnary(gameController.getDictionnary()));
                return new Load(gameController.findGamesOf(idUser), gameController.getAllChatsOfPlayer(idUser));
            } catch (ClientAlreadyConnected ex) {
                return new ErrorMessage(ex.getMessage());
            }
        } else {
            return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
        }
    }
}
