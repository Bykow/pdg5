package pdg5.server.process;

import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.common.WordCombinations;
import pdg5.common.protocol.*;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
import pdg5.server.util.ClientAlreadyConnected;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * Created on 31.10.17 by Bykow
 */
public class ProcessSignIn implements GenericProcess {

    private final SignIn signIn;
    private final ManageUser manager;
    private final ServerActiveUser activeUser;
    private final GameController gameController;
    private final ClientHandler clientHandler;

    public ProcessSignIn(SignIn signIn, ManageUser manager,
            ServerActiveUser activeUser, GameController gameController,
            ClientHandler clientHandler) {
        this.signIn = signIn;
        this.manager = manager;
        this.activeUser = activeUser;
        this.gameController = gameController;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        if (manager.isCorrectPassword(signIn.getUsername(), signIn.getPassword())) {
            // TODO waiting for game logic to improve
            // TODO add client to activeUser

            int idUser = manager.getUserByUsername(signIn.getUsername()).getId();

            try {
                // TODO check if user is logged in already
                activeUser.add(idUser, clientHandler);
                clientHandler.setPlayerId(idUser);

                // Will be receive by the SignInController
                clientHandler.addToQueue(new SignInOK());
                clientHandler.addToQueue(new Dictionnary(gameController.getDictionnary()));
                // Will be receive by the ClientRequestController
                return new Load();
                // TODO test this v
                //return gameController.findGamesOf(idUser);
            } catch (ClientAlreadyConnected ex) {
                return new ErrorMessage(ex.getMessage());
            }
        } else {
            return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
        }
    }
}
