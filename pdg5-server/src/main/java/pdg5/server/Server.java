package pdg5.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import pdg5.common.protocole.IServerRequest;

/**
 *
 * @author Maxime Guillod
 */
public class Server {

    public static void main(String... args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1000);
            Socket socket = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                IServerRequest o = (IServerRequest) in.readObject();
                System.out.println(o);
                System.out.println("--------------------");
                o.execute();
                System.out.println("--------------------");
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
