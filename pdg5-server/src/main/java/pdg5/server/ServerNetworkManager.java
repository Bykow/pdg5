package pdg5.server;

import pdg5.common.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private ServerRequestManager requestManager;

    public ServerNetworkManager() {
        // TODO accept multiple connection
        // Accept only one connection for the moment
        requestManager = new ServerRequestManager();
        try {
            serverSocket = new ServerSocket(Config.SERVER_PORT);
            socket = serverSocket.accept();
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ServerNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(Object o) throws IOException {
        out.writeObject(o);
    }

    public void receive() throws IOException, ClassNotFoundException {
        requestManager.execute(in.readObject());
    }
}
