package pdg5.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
   private ObjectOutputStream out;
   private ObjectInputStream in;

   public ClientNetworkManager() {
      try {
         clientSocket = new Socket(Config.serverAddress, Config.serverPort);
         this.out = new ObjectOutputStream(clientSocket.getOutputStream());
         this.in = new ObjectInputStream(clientSocket.getInputStream());

      } catch (IOException ex) {
         Logger.getLogger(ClientNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void send(IClientRequest request) throws IOException {
      out.writeObject(request);
   }

   public IServerRequest receive() throws IOException, ClassNotFoundException {
      return (IServerRequest) in.readObject();
   }

}
