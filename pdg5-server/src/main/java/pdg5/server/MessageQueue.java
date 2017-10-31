package pdg5.server;

import pdg5.common.protocol.Message;

import java.util.Queue;
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
        queue.add(m);
    }

    public Message poll() {
        return queue.poll();
    }
}
