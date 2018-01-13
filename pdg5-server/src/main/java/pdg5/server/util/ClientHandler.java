package pdg5.server.util;

import pdg5.common.MessageQueue;
import pdg5.common.protocol.Message;
import pdg5.server.model.GameController;

import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.hibernate.Session;
import pdg5.server.manage.ManageChat;
import pdg5.server.manage.ManageGame;
import pdg5.server.manage.ManageMessage;
import pdg5.server.manage.ManageUser;
import pdg5.server.manage.Manager;

/**
 * @author Maxime Guillod
 */
public class ClientHandler implements Runnable {

    private SSLSocket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerRequestManager requestManager;
    private MessageQueue queueIn;
    private MessageQueue queueOut;
    private ServerActiveUser activeUser;
    private static int id;
    private Integer playerId;

    private DatabaseManagers databaseManagers;

    public ClientHandler(SSLSocket socket, ServerActiveUser activeUser, GameController gameController) {
        if (socket == null) {
            return;
        }
        System.out.println("SRV : new client #" + id++);
        this.socket = socket;

        databaseManagers = new DatabaseManagers();

        this.queueIn = new MessageQueue();
        this.queueOut = new MessageQueue();
        this.activeUser = activeUser;
        this.requestManager = new ServerRequestManager(activeUser, gameController);
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerId = null;
    }

    public void addToQueue(Message message) {
        queueOut.add(message);
    }

    @Override
    public void run() {
        // Start receive message
        receive();

        // Start send message
        send();

        // Process message
        while (true) {
            queueOut.add(
                requestManager.execute(
                    queueIn.take(),
                    this
                )
            );
        }
    }

    private void send() {
        new Thread(() -> {
            while (true) {
                try {
                    out.writeObject(queueOut.take());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void receive() {
        new Thread(() -> {
            try {
                while (true) {
                    queueIn.add((Message) in.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Connection close");
                activeUser.remove(playerId);
            }
        }).start();
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerId() throws NullPointerException {
        if (playerId == null) {
            throw new NullPointerException("the clientHandler has no associated client (playerId in ClientHandler null)");
        }
        return playerId;
    }

    public boolean isConnected() {
        return playerId != null;
    }

    public DatabaseManagers getDatabaseManagers() {
        return databaseManagers;
    }

    public class DatabaseManagers {

        private final ManageUser manageUser;
        private final ManageGame manageGame;
        private final ManageChat manageChat;
        private final ManageMessage manageMessage;

        public DatabaseManagers() {

            Session session = new Manager().getSession();

            this.manageUser = new ManageUser(session);
            this.manageGame = new ManageGame(session);
            this.manageChat = new ManageChat(session);
            this.manageMessage = new ManageMessage(session);
        }

        public ManageUser getManageUser() {
            return manageUser;
        }

        public ManageGame getManageGame() {
            return manageGame;
        }

        public ManageChat getManageChat() {
            return manageChat;
        }

        public ManageMessage getManageMessage() {
            return manageMessage;
        }
    }

}
