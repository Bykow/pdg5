package pdg5.client;

import pdg5.common.MessageQueue;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender implements Runnable {

    private MessageQueue queue;
    private ObjectOutputStream out;

    public ClientSender(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.queue = new MessageQueue();
    }

    @Override
    public void run() {
        System.out.println("ClientSender.run");
        while (true) {
            try {
                out.writeObject(queue.take());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
