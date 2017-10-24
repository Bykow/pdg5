package pdg5.server.util;

import pdg5.server.model.LoggedUser;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Used to manage user sesssions
 */
public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    private final Map<String, LoggedUser> loggedUsers = new ConcurrentHashMap<>();
    private final Map<String, LoggedUser> available = new ConcurrentHashMap<>();

    /**
     * Ctor
     */
    private SessionManager() {
    }

    /**
     * Singleton pattern here we come
     * @return the singleton instance
     */
    public static SessionManager getInstance() {
        return instance;
    }

    /**
     * Checks whether the user with the given username is logged in
     *
     * @param username the username
     * @return true if logged in, false otherwise
     */
    public boolean hasLoggedUser(String username) {
        return loggedUsers.containsKey(username.toUpperCase());
    }

    /**
     * Get an instance of LoggedUser corresponding to the user with
     * the given username
     *
     * @param username the username
     * @return an instance of LoggedUser
     */
    public LoggedUser getLoggedUser(String username) {
        return loggedUsers.get(username.toUpperCase());
    }

    /**
     * Add a LoggedUser instance to the pool of connected users
     *
     * @param u the new LoggedUser
     */
    public void addLoggedUser(LoggedUser u) {
        loggedUsers.put(u.getUsername(), u);
    }

    /**
     * Remove a LoggedUser instance from the pool of connected
     * users
     *
     * @param u the LoggedUser to remove
     */
    public void removeLoggedUser(LoggedUser u) {
        removeAvailable(u);
        loggedUsers.remove(u.getUsername(), u);
    }

    /**
     * Add a LoggedUser as available
     *
     * @param u the LoggedUser
     */
    public void addAvailable(LoggedUser u) {
        available.put(u.getUsername(), u);
    }

    /**
     * Remove a LoggedUser from available ones
     *
     * @param u the LoggedUser
     */
    public void removeAvailable(LoggedUser u) {
        available.remove(u.getUsername(), u);
    }

    /**
     * Get the list of available users
     *
     * @return a Collection of LoggedUser instances
     */
    public Collection<LoggedUser> getAvailable() {
        return available.values();
    }

    /**
     * Get a LoggedUser instance corresponding to the given username
     * from the available pool
     *
     * @param username the username
     * @return a LoggedUser instance
     */
    public LoggedUser getAvailableLoggedUser(String username) {
        return available.get(username.toUpperCase());
    }
}
