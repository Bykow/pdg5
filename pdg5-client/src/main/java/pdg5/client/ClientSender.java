package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender implements Runnable {

    private Socket socket;
    private MessageQueue queue;
    private ObjectOutputStream out;

    public ClientSender(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.queue = new MessageQueue();
    }

    public void add(Message msg) {
        queue.add(msg);
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

    public boolean isConnected() {
        return socket.isConnected();
    }
}
