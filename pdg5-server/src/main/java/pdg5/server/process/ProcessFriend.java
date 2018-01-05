package pdg5.server.process;

import pdg5.common.protocol.Friend;
import pdg5.common.protocol.Friend.TYPE;
import pdg5.common.protocol.Message;
import pdg5.common.protocol.Noop;
import pdg5.server.manage.ManageBlacklist;
import pdg5.server.manage.ManageFriend;
import pdg5.server.manage.ManageUser;

/**
 * @author Jimmy Verdasca
 * 
 * Class who execute the specific action of a Friend request.
 * Can be to add or remove, favorite or blackList
 */
public class ProcessFriend implements GenericProcess {
   private ManageBlacklist manageBlacklist;
   private ManageFriend manageFriend;
   private ManageUser manageUser;
   private Friend friend;

   /**
    * Constructor
    * 
    * @param friend the Friend protocole we handle
    * @param manageUser the user manager used to manipulate users
    */
   public ProcessFriend(Friend friend, ManageUser manageUser) {
      this.manageBlacklist = new ManageBlacklist();
      this.manageFriend = new ManageFriend();
      this.manageUser = manageUser;
      this.friend = friend;
   }

   /**
    * Apply the specified objective in friend to the database
    * 
    * @return nothing if worked else an error message
    */
   @Override
   public Message execute() {
      switch(friend.getType()) {
         case ADD_FRIEND:
            manageFriend.addFriend(manageUser.getUserById(friend.getIdPlayer()), manageUser.getUserById(friend.getIdTargettedPlayer()));
            break;
         case REMOVE_FRIEND:
            manageFriend.listFriend()
                    .stream()
                    .filter((f) -> f.getUserByFromUser().getId() == friend.getIdPlayer()
                            && f.getUserByToUser().getId() == friend.getIdTargettedPlayer())
                    .findAny()
                    .map((f) -> manageFriend.deleteFriend(f));
            break;
         case ADD_BLACKLIST:
            manageBlacklist.addBlacklist(manageUser.getUserById(friend.getIdPlayer()), manageUser.getUserById(friend.getIdTargettedPlayer()));
            break;
         case REMOVE_BLACKLIST:
            manageBlacklist.listBlacklist()
                    .stream()
                    .filter((f) -> f.getUserByFromUser().getId() == friend.getIdPlayer()
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
