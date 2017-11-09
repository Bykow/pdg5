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

    private MessageQueue queue;
    private ObjectInputStream in;

    public ClientListener(Socket socket) throws IOException {
        this.in = new ObjectInputStream(socket.getInputStream());
        this.queue = new MessageQueue();
    }

    @Override
    public void run() {
        System.out.println("ClientListener.run");
        try {
            while (true) {
                queue.add((Message)in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
