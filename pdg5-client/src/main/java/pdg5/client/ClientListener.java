package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ClientListener, listens to server for incoming messages
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private static MessageQueue queue = null;
    private static ObjectInputStream in;
    private static boolean launch = false;

    /**
     * Ctor
     */
    public ClientListener() {
        init();
    }

    /**
     * Create a queue if needed
     */
    private void init() {
        if (queue == null) {
            queue = new MessageQueue();
        }
    }

    /**
     * Ctor with TCP Socket
     *
     * @param socket connection to server
     *
     * @throws IOException if socket is invalid
     */
    public ClientListener(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        init();
    }

    /**
     * Run for ClientListener, should always be running, contains while(true)
     */
    @Override
    public void run() {
        if (launch) {
            System.err.println("[ERROR] ClientListener already launch.");
            return;
        }

        try {
            launch = true;
            System.out.println("ClientListener.run");
            while (true) {
                queue.add((Message) in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    /**
     * Takes message from process queue
     *
     * @return Message
     */
    public Message take() {
        return queue.take();
    }

}
