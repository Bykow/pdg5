//package pdg5.server;
//
//import org.junit.jupiter.api.Test;
//import pdg5.client.ClientNetworkManager;
//import pdg5.common.protocol.SignIn;
//import pdg5.server.util.ServerNetworkManager;
//
//import java.io.IOException;
//
///**
// * @author Maxime Guillod
// */
//class ServerNetworkManagerTest {
//
//    private ServerNetworkManager netManager;
//
//    @Test
//    void sendAndReceive() {
//        System.out.println("ServerNetworkManagerTest.sendAndReceive");
//        // Launch Server
//        new Thread(() -> {
//            try {
//                netManager = new ServerNetworkManager();
//                Thread.sleep(100);
//                System.out.println("SRV wait receive");
//                netManager.receive();
//                System.out.println("SRV end");
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        // Start one client
//        ClientNetworkManager client = new ClientNetworkManager();
//        try {
//            System.out.println("CLIENT send");
//            client.send(new SignIn("maxime", "myPass"));
//            System.out.println("CLIENT sleep");
//            Thread.sleep(200);
//            System.out.println("CLIENT end");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
