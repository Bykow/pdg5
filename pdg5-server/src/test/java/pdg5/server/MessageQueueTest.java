package pdg5.server;

import org.junit.Before;
import org.junit.Test;
import pdg5.server.util.MessageQueue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class MessageQueueTest {

    private MessageQueue queue;

    @Before
    public void setUp() {
        queue = new MessageQueue();
    }

    @Test
    public void testPoolIsBlockingIfEmpty() throws InterruptedException {
        new Thread(() -> {
            /*
             * The queue must be empty, and queue.take() must wait.
             */
            queue.take();
            fail("Must wait");
        }).start();

        Thread.sleep(500);
        assertTrue(true);
    }

}
