package pdg5.common.protocole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime Guillod
 */
public class Main {

    public Main() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTest() {
        try {
            ServerSocket serverSocket = new ServerSocket(1000);
            Socket client = new Socket("127.0.0.1", 1000);

            Socket server = serverSocket.accept();

            server(server);
            client(client);
            
            Thread.sleep(2000);
                    

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void server(Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    
                    IServerRequest o = (IServerRequest) in.readObject();
                    System.out.println(o);
                    System.out.println("--------------------");
                    o.execute();
                    System.out.println("--------------------");
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void client(Socket socket) {
        
        SignIn sign = new SignIn("maxime", "myPass");
        
        new Thread(() -> {
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                
                out.writeObject(sign);
                
                in.readObject();
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
}
