package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender implements Runnable {

    private static MessageQueue queue;
    private static ObjectOutputStream out;

    public ClientSender(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.queue = new MessageQueue();
    }

    public ClientSender(){}

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

    public void addToQueue(Message m) {
        queue.add(m);
    }
}
