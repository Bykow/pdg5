package pdg5.server.process;

import pdg5.common.protocol.Friend;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.server.manage.ManageBlacklist;
import pdg5.server.manage.ManageFriend;
import pdg5.server.manage.ManageUser;
import pdg5.server.util.ClientHandler;

/**
 * Class who execute the specific action of a Friend request. Can be to add or
 * remove, favorite or blackList
 */
public class ProcessFriend implements GenericProcess {

    /**
     * manager of the socket where we received the Friend
     */
    private final ClientHandler clientHandler;
    /**
     * to store blacklist datas in the database
     */
    private ManageBlacklist manageBlacklist;
    /**
     * to store friends datas in the database
     */
    private ManageFriend manageFriend;
    /**
     * to store/get users datas in the database
     */
    private ManageUser manageUser;
    /**
     * the original Friend that the server received
     */
    private Friend friend;

    /**
     * Constructor
     *
     * @param friend        the original Friend that the server received
     * @param manageUser    the user manager used to manipulate users
     * @param clientHandler manager of the socket where we received the Friend
     */
    public ProcessFriend(Friend friend, ManageUser manageUser, ClientHandler clientHandler) {
        this.manageBlacklist = new ManageBlacklist();
        this.manageFriend = new ManageFriend();
        this.manageUser = manageUser;
        this.friend = friend;
        this.clientHandler = clientHandler;
    }

    /**
     * Apply the specified objective in friend to the database
     *
     * @return Noop if worked else an error message
     */
    @Override
    public Message execute() {
        switch (friend.getType()) {
            case ADD_FRIEND:
                manageFriend.addFriend(manageUser.getUserById(clientHandler.getPlayerId()), manageUser.getUserById(friend.getIdTargettedPlayer()));
                break;
            case REMOVE_FRIEND:
                manageFriend.listFriend()
                        .stream()
                        .filter((f) -> f.getUserByFromUser().getId() == clientHandler.getPlayerId()
                                && f.getUserByToUser().getId() == friend.getIdTargettedPlayer())
                        .findAny()
                        .map((f) -> manageFriend.deleteFriend(f));
                break;
            case ADD_BLACKLIST:
                manageBlacklist.addBlacklist(manageUser.getUserById(clientHandler.getPlayerId()), manageUser.getUserById(friend.getIdTargettedPlayer()));
                break;
            case REMOVE_BLACKLIST:
                manageBlacklist.listBlacklist()
                        .stream()
                        .filter((f) -> f.getUserByFromUser().getId() == clientHandler.getPlayerId()
                                && f.getUserByToUser().getId() == friend.getIdTargettedPlayer())
                        .findAny()
                        .map((f) -> manageBlacklist.deleteBlacklist(f));
                break;
            default:
                System.err.println("Le type de Friend est invalide");
                break;
        }
        return new Noop(Noop.Sender.SERVER);
    }

}
