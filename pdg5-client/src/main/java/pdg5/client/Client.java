package pdg5.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.common.protocole.SignIn;

public class Client {
    public static void main(String... args) {
     try {
            Socket socket = new Socket("127.0.0.1", 1000);
            
            SignIn sign = new SignIn("maxime", "myPass");
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            out.writeObject(sign);
            
            Thread.sleep(2000);

            socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
