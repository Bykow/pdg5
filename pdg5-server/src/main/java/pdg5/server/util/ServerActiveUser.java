package pdg5.server.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class which is use to know which client is active, and let you to get a specific {@link ClientHandler} from a User
 *
 * @author Maxime Guillod
 */
public class ServerActiveUser {

    private Map<Integer, ClientHandler> map;
    private static boolean isAlreadyLaunch = false;

    public ServerActiveUser() {
        if (!isAlreadyLaunch) {
            System.out.println("START ServerActiveUser");
            isAlreadyLaunch = true;
            map = new LinkedHashMap<>();
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

    public void remove(Integer userId) {
        map.remove(userId);
    }

    public ClientHandler getClientHangler(Integer userId) {
        return map.get(userId);
    }

}
