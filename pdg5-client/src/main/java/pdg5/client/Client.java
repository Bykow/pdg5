package pdg5.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.common.protocol.SignIn;

public class Client {
    public static void main(String... args){
       
       ClientNetworkManager netManager = new ClientNetworkManager();
       
       try {
          System.out.println("Sending information");
          netManager.send(new SignIn("Bonjour", "Au revoir"));
          
          System.out.println("Receiving");
          netManager.receive();
          
          System.out.println("Waiting");
          Thread.sleep(100000);
          
       } catch (IOException | InterruptedException | ClassNotFoundException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
    }
}
