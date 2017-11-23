package pdg5.client;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender implements Runnable {

    private static Socket socket;
    private static MessageQueue queue = null;
    private static ObjectOutputStream out;

    public ClientSender(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        init();
    }

    public ClientSender() {
       init();
    }

    private void init() {
       if(queue == null) {
          queue = new MessageQueue();
       }
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

    public void add(Message m) {
        if (m != null) {
            queue.add(m);
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }
}
