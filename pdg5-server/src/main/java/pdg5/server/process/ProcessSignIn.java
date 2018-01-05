package pdg5.server.process;

import pdg5.common.protocol.ErrorMessage;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.SignIn;
import pdg5.server.manage.ManageUser;
import pdg5.server.model.GameController;
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

            activeUser.add(idUser, clientHandler);
            clientHandler.setPlayerId(idUser);

            return gameController.findGamesOf(idUser);
        } else {
            return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
        }
    }
}
