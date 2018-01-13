package pdg5.server.manage;

import pdg5.server.persistent.Friend;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

/**
 * Manager to stock and load friends from/to de database
 */
public class ManageFriend extends Manager {

   /**
    * add a new Friend relation to the database
    * 
    * @param fromUser the user adding a player in his friendlist
    * @param toUser the choosen friend player
    * @return the friend that has been stocked in the database
    */
    public Friend addFriend(User fromUser, User toUser) {
        return (Friend) addToDB(new Friend(fromUser, toUser, new Date()));
    }

    /**
     * return all the friend lists contained in the database
     * 
     * @return all the friend lists contained in the database
     */
    public List<Friend> listFriend() {
        return (List<Friend>) getListFromDB("FROM Friend");
    }

    /**
     * update the informations about a friend
     * 
     * @param friend the new friend informations status
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int updateFriend(Friend friend) {
        return updateToDB(friend);
    }

    /**
     * delete a friend relation in the database
     * 
     * @param friend the friend relation we wish to delete
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int deleteFriend(Friend friend) {
        return deleteFriend(friend);
    }
}
