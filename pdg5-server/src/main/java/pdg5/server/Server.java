package pdg5.server;

import pdg5.server.util.ServerNetworkManager;

/**
 * @author Maxime Guillod
 */
public class Server {

   /**
    * entry point of the server
    * 
    * @param args parameters not used
    */
    public static void main(String... args) {
        ServerNetworkManager netManager = new ServerNetworkManager();
        netManager.begin();
    }
}
