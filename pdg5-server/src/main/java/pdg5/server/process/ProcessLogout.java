package pdg5.server.process;

import pdg5.common.protocol.Logout;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * process to log out a user asking it
 */
public class ProcessLogout implements GenericProcess {

    /**
     * the original log out request received
     */
    private Logout logout;

    /**
     * manager of the user who are connected
     */
    private ServerActiveUser activeUser;

    /**
     * manager of the manager of the socket where we received the Logout
     */
    private ClientHandler clientHandler;

    /**
     * Constructor
     *
     * @param logout        the original log out request received
     * @param activeUser    manager of the user who are connected
     * @param clientHandler manager of the socket where we received the Logout
     */
    public ProcessLogout(Logout logout, ServerActiveUser activeUser, ClientHandler clientHandler) {
        this.logout = logout;
        this.activeUser = activeUser;
        this.clientHandler = clientHandler;
    }

    /**
     * log out a user
     *
     * @return Noop
     */
    @Override
    public Message execute() {
        int idUser = clientHandler.getPlayerId();
        activeUser.remove(idUser);
        clientHandler.setPlayerId(null);

        return new Noop(Noop.Sender.SERVER);
    }

}
