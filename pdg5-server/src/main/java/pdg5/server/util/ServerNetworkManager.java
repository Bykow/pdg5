package pdg5.server.util;

import pdg5.common.Protocol;
import pdg5.server.model.GameController;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdg5.server.manage.Manager;

/**
 * Listen the network on the DEFAULT_PORT, and start a {@link ClientHandler} when a
 * new connection is detected.
 *
 * @author Maxime Guillod
 */
public class ServerNetworkManager {

    private ServerSocket serverSocket;
    private Socket socket;
    private final ServerActiveUser activeUser;
    private final GameController gameController;

    public ServerNetworkManager() {
        System.out.println("START ServerNetworkManager");
        activeUser = new ServerActiveUser();
        gameController = new GameController(activeUser);
        
        new Manager().getSession();
        System.out.println("Hibernate Connected");
    }

    public void begin() {
        try {
            ServerSocketFactory ssf = ServerSocketFactory.getDefault();
            serverSocket = ssf.createServerSocket(Protocol.DEFAULT_PORT);
            while (true) {
                socket = serverSocket.accept();

                final char[] JKS_PASSWORD = "pdg5Password".toCharArray();
                final char[] KEY_PASSWORD = "psg5Password".toCharArray();

                /* Get the JKS contents */
                final KeyStore keyStore = KeyStore.getInstance("JKS");
                try (final InputStream is = ServerNetworkManager.class.getResourceAsStream("pdg5.jks")) {
                    keyStore.load(is, JKS_PASSWORD);
                }
                final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                        .getDefaultAlgorithm());
                kmf.init(keyStore, KEY_PASSWORD);
                final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
                        .getDefaultAlgorithm());
                tmf.init(keyStore);

                /*
                 * Creates a socket factory for HttpsURLConnection using JKS
                 * contents
                 */
                final SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf.getKeyManagers(), null, new java.security.SecureRandom());

                SSLSocketFactory sslSf = sslContext.getSocketFactory();
                // The host name doesn't really matter, since we're turning it into a server socket
                // (No need to match the host name to the certificate on this side).
                SSLSocket sslSocket = (SSLSocket) sslSf.createSocket(socket, null,
                        socket.getPort(), false);
                sslSocket.setUseClientMode(false);

                sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                                
                // Use the sslSocket InputStream/OutputStream as usual.
                new Thread(new ClientHandler(sslSocket, activeUser, gameController)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerNetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (final GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
