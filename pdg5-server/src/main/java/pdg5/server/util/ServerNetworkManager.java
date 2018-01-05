package pdg5.server.util;

import pdg5.common.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.server.model.GameController;

/**
 * Listen the network on the DEFAULT_PORT, and start a {@link ClientHandler} when a new connection is detected.
 *
 * @author Maxime Guillod
 */
public class ServerNetworkManager {

    private ServerSocket serverSocket;
    private Socket socket;
    private final ServerActiveUser activeUser;
    private final GameController gameController;

    public ServerNetworkManager() {
        System.out.println("START ServerNetworkManager");
        activeUser = new ServerActiveUser();
        gameController = new GameController(activeUser);
    }
    
    public void begin(){
        try {
            serverSocket = new ServerSocket(Protocol.DEFAULT_PORT);
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, activeUser, gameController)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
