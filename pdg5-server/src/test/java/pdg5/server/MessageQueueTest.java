package pdg5.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageQueueTest extends Assert {

    private MessageQueue queue;

    @Before
    void setUp() {
        queue = new MessageQueue();
    }

    @Test
    public void testPoolIsBlockingIfEmpty() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(true);
        }).start();

        queue.poll();
        fail("Must wait");
    }

}
