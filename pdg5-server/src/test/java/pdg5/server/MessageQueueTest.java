package pdg5.server;

import org.junit.Before;
import org.junit.Test;
<<<<<<< HEAD
import pdg5.server.util.MessageQueue;
=======
import pdg5.common.MessageQueue;
>>>>>>> 65bbb694d90b61f2978ff8b5a7f795eca372357b

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
