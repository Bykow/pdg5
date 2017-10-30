package pdg5.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.common.Config;
import pdg5.common.protocole.IClientRequest;
import pdg5.common.protocole.IServerRequest;

/**
 *
 */
public class ClientNetworkManager {

   private Socket clientSocket;
   private DataOutputStream out;
   private BufferedReader in;

   public ClientNetworkManager() {
      try {
         clientSocket = new Socket(Config.serverAddress, Config.serverPort);
         out = new DataOutputStream(clientSocket.getOutputStream());
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         
      } catch (IOException ex) {
         Logger.getLogger(ClientNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   public void send(IClientRequest request){
      
   }
   
   public IServerRequest receive(){
       return null;
      
      
   }

}
