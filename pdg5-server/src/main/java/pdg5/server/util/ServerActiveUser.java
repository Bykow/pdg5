package pdg5.server.util;

import java.util.LinkedHashMap;
import java.util.Map;
import pdg5.common.protocol.Message;

/**
 * @author Maxime Guillod
 */
public class ServerActiveUser {

    private final Map<Integer, ClientHandler> map;

    public ServerActiveUser() {
        map = new LinkedHashMap<>();
    }

    public void add(Integer user, ClientHandler clientHandler) throws ClientAlreadyConnected {
        if(map.containsKey(user)){
            throw new ClientAlreadyConnected("The client is already logged in");
        }
        map.put(user, clientHandler);
    }

    public boolean contains(Integer user) {
        return map.containsKey(user);
    }

    public void remove(Integer user) {
        map.remove(user);
    }

    public ClientHandler getClientHandler(Integer user) {
        return map.get(user);
    }
    
    public void giveToClientHandler(Integer user, Message m){
        ClientHandler client = getClientHandler(user);
        if(client != null){
            client.addToQueue(m);
        }
    }

}
