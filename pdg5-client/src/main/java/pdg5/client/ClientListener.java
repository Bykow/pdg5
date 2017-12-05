package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Maxime Guillod
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private static MessageQueue queue = null;
    private static ObjectInputStream in;

    public ClientListener() {
        init();
    }

    private void init() {
        if (queue == null) {
            queue = new MessageQueue();
        }
    }

    public ClientListener(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        init();
    }

    @Override
    public void run() {
        System.out.println("ClientListener.run");
        try {
            while (true) {
                queue.add((Message) in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public Message take() {
        return queue.take();
    }

}
