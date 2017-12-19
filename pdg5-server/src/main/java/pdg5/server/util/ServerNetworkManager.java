package pdg5.server.util;

import pdg5.common.Protocol;
import pdg5.server.model.GameController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listen the network on the DEFAULT_PORT, and start a {@link ClientHandler} when a new connection is detected.
 *
 * @author Maxime Guillod
 */
public class ServerNetworkManager {

    private ServerSocket serverSocket;
    private Socket socket;
    private ServerActiveUser activeUser;
    private GameController gameController;

    public ServerNetworkManager() {
        System.out.println("START ServerNetworkManager");
        activeUser = new ServerActiveUser();
        gameController = new GameController();

        try {
            serverSocket = new ServerSocket(Protocol.DEFAULT_PORT);
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, activeUser)).start();
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
