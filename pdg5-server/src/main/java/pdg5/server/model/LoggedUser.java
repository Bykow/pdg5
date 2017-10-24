package pdg5.server.model;

import pdg5.server.model.ContextualClientHandler;

/**
 * LoggedUser represents a user currently logged in
 * on the server that can interact with it
 */
public class LoggedUser {
    private final Integer userId;
    private final String username;

    private final int privilege;

    private final ContextualClientHandler context;

    /**
     * Ctor
     *
     * @param u       the user we create a logged one from
     * @param context the context in which we execute this
     */
    public LoggedUser(User u, ContextualClientHandler context) {
        userId = u.getId_user();
        username = u.getUsername();
        privilege = u.getPrivilege();
        this.context = context;
    }

    /**
     * Get the context of this user
     *
     * @return the ContextualClientHandler used by this instance
     */
    public ContextualClientHandler getContext() {
        return context;
    }

    /**
     * Get the username of this user
     *
     * @return the username as a String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the user id of this user
     *
     * @return the id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Get the privilege of this user
     *
     * @return the privilege as an int
     */
    public int getPrivilege() {
        return privilege;
    }

}
