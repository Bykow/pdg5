package pdg5.common;

import pdg5.common.protocol.Message;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * MessageQueue, queue of message to process
 */
public class MessageQueue {

    // First in first out queue
    private BlockingDeque<Message> queue;

    public MessageQueue() {
        this.queue = new LinkedBlockingDeque<>();
    }

    /**
     * Adds a message to the queue
     *
     * @param m message to add
     */
    public void add(Message m) {
        if (m != null) {
            queue.add(m);
        }
    }

    /**
     * Take the next message from queue
     *
     * @return message
     */
    public Message take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
