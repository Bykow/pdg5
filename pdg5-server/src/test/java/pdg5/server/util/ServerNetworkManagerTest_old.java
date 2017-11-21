//package pdg5.server.util;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import pdg5.client.ClientListener;
//import pdg5.client.ClientSender;
//import pdg5.common.Protocol;
//import pdg5.common.protocol.Noop;
//import pdg5.common.protocol.Message;
//
//import java.io.IOException;
//import java.net.Socket;
//
//public class ServerNetworkManagerTest_old {
//
//    private ServerNetworkManager serverNetworkManager;
//
//    private Socket socket = null;
//    private ClientSender clientSender = null;
//    private ClientListener clientListener = null;
//
//    @Before
//    public void init() throws InterruptedException {
//        new Thread(() -> {
//            serverNetworkManager = new ServerNetworkManager();
//        }).start();
//
//        // To be sure the server is running and listen on the port.
//        Thread.sleep(100);
//
//        try {
//            socket = new Socket(Protocol.DEFAULT_SERVER, Protocol.DEFAULT_PORT);
//            clientSender = new ClientSender(socket);
//            clientListener = new ClientListener(socket);
//        } catch (IOException e) {
//        }
//
//    }
//
//    @After
//    public void stop() throws InterruptedException {
//        try {
//            if (socket != null) {
//                socket.close();
//            }
//            if (serverNetworkManager != null) {
//                serverNetworkManager.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testCommunication() {
//        Assert.assertTrue(clientListener.isConnected());
//        Assert.assertTrue(clientSender.isConnected());
//
//        new Thread(clientListener).start();
//        new Thread(clientSender).start();
//
//        Noop noop = new Noop(Noop.Sender.CLIENT);
//
//        clientSender.add(noop);
//        Message response = clientListener.take();
//
//        Assert.assertEquals(
//                "pdg5.common.protocol.Noop",
//                response.getClass().getName());
//    }
//}
