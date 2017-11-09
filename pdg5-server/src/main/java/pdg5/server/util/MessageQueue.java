package pdg5.server.util;

import pdg5.common.protocol.Message;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Maxime Guillod
 */
public class MessageQueue {

    private BlockingDeque<Message> queue;

    public MessageQueue() {
        this.queue = new LinkedBlockingDeque<>();
    }

    public void add(Message m) {
        if (m != null) {
            queue.add(m);
        }
    }

    public Message take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
