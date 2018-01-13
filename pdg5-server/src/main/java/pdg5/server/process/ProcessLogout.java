package pdg5.server.process;

import pdg5.common.protocol.Logout;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.server.util.ClientHandler;
import pdg5.server.util.ServerActiveUser;

/**
 * @author Jimmy Verdasca
 */
public class ProcessLogout implements GenericProcess {

    private Logout logout;
    private ServerActiveUser activeUser;
    private ClientHandler clientHandler;

    public ProcessLogout(Logout logout, ServerActiveUser activeUser, ClientHandler clientHandler) {
        this.logout = logout;
        this.activeUser = activeUser;
        this.clientHandler = clientHandler;
    }

    @Override
    public Message execute() {
        int idUser = clientHandler.getPlayerId();
        activeUser.remove(idUser);
        clientHandler.setPlayerId(null);

        return new Noop(Noop.Sender.SERVER);
    }

}
