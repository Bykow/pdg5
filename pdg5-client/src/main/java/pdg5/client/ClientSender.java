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
    private static boolean launch = false;

    public ClientSender(Socket socketIn) throws IOException {
        socket = socketIn;
        out = new ObjectOutputStream(socket.getOutputStream());
        init();
    }

    public ClientSender() {
        init();
    }

    private void init() {
        if (queue == null) {
            queue = new MessageQueue();
        }
    }

    @Override
    public void run() {
        if (launch) {
            System.err.println("[ERROR] ClientSender already launch");
            return;
        }

        while (true) {
            launch = true;
            System.out.println("ClientSender.run");
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
