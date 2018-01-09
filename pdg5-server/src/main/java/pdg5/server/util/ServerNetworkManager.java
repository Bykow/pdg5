package pdg5.server.util;

import pdg5.common.Protocol;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.server.model.GameController;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

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
            ServerSocketFactory ssf = ServerSocketFactory.getDefault();
            serverSocket = ssf.createServerSocket(Protocol.DEFAULT_PORT);
            while (true) {
                socket = serverSocket.accept();

                SSLSocketFactory sslSf = sslContext.getSocketFactory();
                // The host name doesn't really matter, since we're turning it into a server socket
                // (No need to match the host name to the certificate on this side).
                SSLSocket sslSocket = (SSLSocket) sslSf.createSocket(socket, null,
                        socket.getPort(), false);
                sslSocket.setUseClientMode(false);

                // Use the sslSocket InputStream/OutputStream as usual.
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
