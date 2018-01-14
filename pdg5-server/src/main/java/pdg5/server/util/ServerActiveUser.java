package pdg5.server.util;

import pdg5.common.protocol.Message;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * manager of the user who are connected
 */
public class ServerActiveUser {

    /**
     * a map where the key is a unique id of a connected player and the value his ClientHandler
     */
    private final Map<Integer, ClientHandler> map;

    /**
     * Constructor
     */
    public ServerActiveUser() {
        map = new LinkedHashMap<>();
    }

    /**
     * add a newly connected client to the map
     *
     * @param user          unique id of the newly connected player
     * @param clientHandler the client handler who will manage him
     * @throws ClientAlreadyConnected if we try to add a client multiple times to the map
     */
    public void add(Integer user, ClientHandler clientHandler) throws ClientAlreadyConnected {
        if (map.containsKey(user)) {
            throw new ClientAlreadyConnected("The client is already logged in");
        }
        map.put(user, clientHandler);
    }

    /**
     * check if a client is in the map with his unique id
     *
     * @param user unique id of the client
     * @return true if he is in the map
     */
    public boolean contains(Integer user) {
        return map.containsKey(user);
    }

    /**
     * remove a connected client to the map
     *
     * @param user unique id of the client
     */
    public void remove(Integer user) {
        map.remove(user);
    }

    /**
     * return the client handler of a specified unique id of a client
     *
     * @param user unique id of a connected client
     * @return the ClientHandler of the client, or null if he isn't in the map
     */
    public ClientHandler getClientHandler(Integer user) {
        return map.get(user);
    }

    /**
     * send a protocol.Message to a client
     *
     * @param user unique id of the client
     * @param m    Protocol.Message we wish to send him
     */
    public void giveToClientHandler(Integer user, Message m) {
        ClientHandler client = getClientHandler(user);
        if (client != null) {
            client.addToQueue(m);
        }
    }

    /**
     * return the managers of one client (managers needs to be run in a single thread)
     *
     * @param user unique id of the client
     * @return a class containing all the created instance of possible managers for a client
     */
    public ClientHandler.DatabaseManagers getDatabaseManagers(Integer user) {
        return getClientHandler(user).getDatabaseManagers();
    }
}
