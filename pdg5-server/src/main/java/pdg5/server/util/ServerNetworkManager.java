package pdg5.server.util;

import pdg5.common.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Maxime Guillod
 */
public class ServerNetworkManager {

    private ServerSocket serverSocket;
    private Socket socket;

    public ServerNetworkManager() {
        try {
            serverSocket = new ServerSocket(Protocol.DEFAULT_PORT);
            while(true) {
                socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
