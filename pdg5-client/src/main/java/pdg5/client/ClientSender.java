package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ClientSender, sender for client to send messages to server
 */
public class ClientSender implements Runnable {

    private static Socket socket;
    private static MessageQueue queue = null;
    private static ObjectOutputStream out;
    private static boolean launch = false;

    /**
     * Ctor with socket
     *
     * @param socketIn socket to use
     * @throws IOException if socket is invalid
     */
    public ClientSender(Socket socketIn) throws IOException {
        socket = socketIn;
        out = new ObjectOutputStream(socket.getOutputStream());
        init();
    }

    /**
     * Ctor
     */
    public ClientSender() {
        init();
    }

    /**
     * Creates a queue if needed
     */
    private void init() {
        if (queue == null) {
            queue = new MessageQueue();
        }
    }

    /**
     * Run for ClientSender, should always be running, contains while(true)
     */
    @Override
    public void run() {
        if (launch) {
            System.err.println("[ERROR] ClientSender already launch");
            return;
        }

        while (true) {
            launch = true;
            try {
                out.writeObject(queue.take());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a message to queue
     *
     * @param m message to add
     */
    public void add(Message m) {
        if (m != null) {
            queue.add(m);
        }
    }
}
