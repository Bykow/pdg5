//package pdg5.server;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import pdg5.server.util.ClientHandler;
//import pdg5.server.util.ServerActiveUser;
//
//import static junit.framework.TestCase.assertTrue;
//
///**
// * @author Maxime Guillod
// */
//public class ServerActiveUserTest {
//
//    private ServerActiveUser activeUser;
//    private ClientHandler clientHandler;
//
//    @Before
//    public void setUp() {
//        activeUser = new ServerActiveUser();
//        clientHandler = new ClientHandler(null, null);
//    }
//
//    @Test
//    public void global() {
//        activeUser.add(1, clientHandler);
//        activeUser.add(2, null);
//        assertTrue(activeUser.contains(1));
//        assertTrue(!activeUser.contains(42));
//        activeUser.remove(2);
//        assertTrue(!activeUser.contains(2));
//        Assert.assertEquals(
//                clientHandler,
//                activeUser.getClientHangler(1)
//        );
//        Assert.assertNotEquals(
//                clientHandler,
//                activeUser.getClientHangler(2)
//        );
//    }
//
//}
