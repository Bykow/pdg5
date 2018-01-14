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
 * manager of the socket of one client
 * it creates the threads necessary to send, receive and execute requests
 * 
 * it contains too an inner class who contains 
 * all the managers instances to store/get datas in the database
 */
public class ClientHandler implements Runnable {

   /**
    * the socket associated at this client
    */
    private SSLSocket socket;
    
    /**
     * output stream to serialize requests
     */
    private ObjectOutputStream out;
    
    /**
     * input stream to deserialize requests
     */
    private ObjectInputStream in;
    
    /**
     * request manager to handle correctly each type of requests
     */
    private ServerRequestManager requestManager;
    
    /**
     * the queue of requests sent from the client
     */
    private MessageQueue queueIn;
    
    /**
     * the queue of requests we want to send to the client
     */
    private MessageQueue queueOut;
    
    /**
     * manager of the user who are connected
     */
    private ServerActiveUser activeUser;
    
    /**
     * next unique id free
     */
    private static int id;
    
    /**
     * unique id of the client associated to this socket, null if noone
     */
    private Integer playerId;

    /**
     * class containing all the managers instances
     */
    private DatabaseManagers databaseManagers;

    /**
     * Constructor
     * 
     * @param socket the socket associated at this client
     * @param activeUser manager of the user who are connected
     * @param gameController gameController where the games and game logique are stored (necessary to build a correct ServerRequestManager)
     */
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

    /**
     * add a protocol.Message to send to the client
     * 
     * @param message protocol.Message we wish to send 
     */
    public void addToQueue(Message message) {
        queueOut.add(message);
    }

    /**
     * start this ClientHandler so that he begins to send, receive and execute requests
     */
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

    /**
     * thread-loop to send messages to the client
     */
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

    /**
     * thread-loop to receive messages from the client
     */
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

    /**
     * modify the unique id of the client associated to this instance
     * 
     * @param playerId the new unique id of the client associated to this instance
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * return the unique id of the client associated to this instance
     * 
     * @return the unique id of the client associated to this instance
     * @throws NullPointerException if the playerId has not be initialized yet with setPlayerId
     */
    public Integer getPlayerId() throws NullPointerException {
        if (playerId == null) {
            throw new NullPointerException("the clientHandler has no associated client (playerId in ClientHandler null)");
        }
        return playerId;
    }

    /**
     * return true if the playerId has been initialized yet with setPlayerId, false otherwise
     * 
     * @return true if the playerId has been initialized yet with setPlayerId, false otherwise
     */
    public boolean isConnected() {
        return playerId != null;
    }

    /**
     * return the class containing all the managers instances
     * 
     * @return the class containing all the managers instances
     */
    public DatabaseManagers getDatabaseManagers() {
        return databaseManagers;
    }

    /**
     * inner class containing all the managers instances
     */
    public class DatabaseManagers {

       /**
        * to store/get users datas in the database
        */
        private final ManageUser manageUser;
        
        /**
         * to store/get games datas in the database
         */
        private final ManageGame manageGame;
        
        /**
         * to store/get chats datas in the database
         */
        private final ManageChat manageChat;
        
        /**
         * to store/get messages datas in the database
         */
        private final ManageMessage manageMessage;

        /**
         * Constructor
         */
        public DatabaseManagers() {

            Session session = new Manager().getSession();

            this.manageUser = new ManageUser(session);
            this.manageGame = new ManageGame(session);
            this.manageChat = new ManageChat(session);
            this.manageMessage = new ManageMessage(session);
        }

        /**
         * return the manager who to store/get users datas in the database
         * 
         * @return the manager who to store/get users datas in the database
         */
        public ManageUser getManageUser() {
            return manageUser;
        }

        /**
         * return the manager who to store/get games datas in the database
         * 
         * @return the manager who to store/get games datas in the database
         */
        public ManageGame getManageGame() {
            return manageGame;
        }

        /**
         * return the manager who to store/get users chats in the database
         * 
         * @return the manager who to store/get chats datas in the database
         */
        public ManageChat getManageChat() {
            return manageChat;
        }

        /**
         * return the manager who to store/get users messages in the database
         * 
         * @return the manager who to store/get users messages in the database
         */
        public ManageMessage getManageMessage() {
            return manageMessage;
        }
    }

}
