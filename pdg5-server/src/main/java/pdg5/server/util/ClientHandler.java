package pdg5.server.util;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

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
    private ServerActiveUser activeUser;
    private static int id;

    /**
     * @param socket
     * @param activeUser
     */
    public ClientHandler(Socket socket, ServerActiveUser activeUser) {
        if (socket == null) return;
        System.out.println("SRV : new client #" + id++);
        this.socket = socket;
        this.queueIn = new MessageQueue();
        this.queueOut = new MessageQueue();
        this.activeUser = activeUser;
        this.requestManager = new ServerRequestManager(this.activeUser, this);
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add message to be send to the client
     *
     * @param message
     */
    public void addToQueue(Message message) {
        queueOut.add(message);
    }

    @Override
    public void run() {
        // Start receive message
        receive();

        // Start send message
        send();

        // Process message
        while (true) {
            queueOut.add(
                    requestManager.execute(
                            queueIn.take()
                    )
            );
        }
    }

    private void send() {
        new Thread(() -> {
            while (true) {
                try {
                    out.writeObject(queueOut.take());
                } catch (IOException e) {
                    // TODO Must close on exit
                    System.err.println("COUCOU");
                }
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
                System.err.println("Connection close");
                activeUser.remove(this);
            }
        }).start();
    }

}
