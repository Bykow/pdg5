package pdg5.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Maxime Guillod
 */
public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerRequestManager requestManager;
    private MessageQueue queueIn;
    private MessageQueue queueOut;

    public ClientHandler(Socket socket) {
        System.out.println("SRV : new client");
        this.socket = socket;
        this.queueIn = new MessageQueue();
        this.queueOut = new MessageQueue();
        this.requestManager = new ServerRequestManager();
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Start receive message
        receive();

        // Start send message
        send();

        // Process message
        queueOut.add(requestManager.execute(queueIn.poll()));
    }

    public ServerRequestManager getRequestManager() {
        return requestManager;
    }

    public void send(Object o) {
        try {
            out.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send() {
        new Thread(() -> {
            while (true) {
                out.writeObject(queueOut.poll());
            }
        }).start();
    }

    private void receive() {
        new Thread(() -> {
            try {
                while (true) {
                    queueIn.add((Message) in.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // todo addQUeuout

}
