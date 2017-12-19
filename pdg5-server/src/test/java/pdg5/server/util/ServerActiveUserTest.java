package pdg5.server.util;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerActiveUserTest {

    private ServerActiveUser serverActiveUser;

    @BeforeEach
    void setUp() {
        serverActiveUser = new ServerActiveUser();
    }

    @Test
    void contain() {
        // Initialisation
        int id1 = 42;
        ClientHandler c1 = new ClientHandler(null, null);

        int id2 = 41;
        ClientHandler c2 = new ClientHandler(null, null);

        // Add
        serverActiveUser.add(id1, c1);
        serverActiveUser.add(id2, c2);


        Assert.assertTrue(serverActiveUser.contains(id1));
        Assert.assertTrue(serverActiveUser.contains(c1));
        Assert.assertTrue(serverActiveUser.contains(id2));
        Assert.assertTrue(serverActiveUser.contains(c2));

        // Remove from id
        serverActiveUser.remove(id2);
        Assert.assertFalse(serverActiveUser.contains(id2));
        Assert.assertFalse(serverActiveUser.contains(c2));

        // Remove from ClientHandler
        serverActiveUser.remove(c1);
        Assert.assertFalse(serverActiveUser.contains(id1));
        Assert.assertFalse(serverActiveUser.contains(c1));
    }
}