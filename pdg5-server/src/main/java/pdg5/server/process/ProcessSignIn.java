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

    private SignIn signIn;
    private ManageUser manager;
    private ServerActiveUser activeUser;
    private GameController gameController;
    private ClientHandler clientHandler;

    public ProcessSignIn(SignIn signIn, ManageUser manager, ServerActiveUser activeUser, GameController gameController, ClientHandler clientHandler) {
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
            int userId = manager.getUserByUsername(signIn.getUsername()).getId();
            activeUser.add(userId, clientHandler);

            return gameController.findGamesOf(userId);
        } else {
            return new ErrorMessage("Password invalid in SignIn for user " + signIn.getUsername());
        }
    }
}
