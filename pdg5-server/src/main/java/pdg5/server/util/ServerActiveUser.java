package pdg5.server.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Class which is use to know which client is active, and let you to get a specific {@link ClientHandler} from a User
 *
 * @author Maxime Guillod
 */
public class ServerActiveUser {

    private BiMap<Integer, ClientHandler> map;
    private static boolean isAlreadyLaunch = false;

    public ServerActiveUser() {
        if (!isAlreadyLaunch) {
            System.out.println("START ServerActiveUser");
            isAlreadyLaunch = true;
            map = HashBiMap.create();
        } else {
            System.err.println("ERROR ServerActiveUser is already launch");
        }
    }

    /**
     * Add a specific user to our map.
     *
     * @param userId
     * @param clientHandler
     */
    public void add(Integer userId, ClientHandler clientHandler) {
        map.put(userId, clientHandler);
    }

    public boolean contains(Integer userId) {
        return map.containsKey(userId);
    }

    public boolean contains(ClientHandler clientHandler) {
        return map.containsValue(clientHandler);
    }

    /**
     * Remove a specific userId
     *
     * @param userId
     */
    public void remove(Integer userId) {
        map.remove(userId);
    }

    /**
     * Remove a specific {@link ClientHandler}
     *
     * @param clientHandler
     */
    public void remove(ClientHandler clientHandler) {
        map.inverse().remove(clientHandler);
    }

    /**
     * Get the {@link ClientHandler} from the userId
     *
     * @param userId
     * @return
     */
    public ClientHandler getClientHangler(Integer userId) {
        return map.get(userId);
    }

    /**
     * Get the userId from a {@link ClientHandler}
     *
     * @param clientHandler
     * @return
     */
    public Integer getUserId(ClientHandler clientHandler) {
        return map.inverse().get(clientHandler);
    }

}
