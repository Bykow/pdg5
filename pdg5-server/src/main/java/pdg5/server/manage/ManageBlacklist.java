package pdg5.server.manage;

import pdg5.server.persistent.Blacklist;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

/**
 * Manager to stock and load the blacklist from/to de database
 */
public class ManageBlacklist extends Manager {

   /**
    * add a BlackList to the database
    * 
    * @param fromUser the user adding a player in his blacklist
    * @param toUser the choosen blacklisted player
    * @return a BlackList that will be stocked in the database
    */
    public Blacklist addBlacklist(User fromUser, User toUser) {
        return (Blacklist) addToDB(new Blacklist(fromUser, toUser, new Date()));
    }

    /**
     * return all the blacklist contained in the database
     * 
     * @return all the blacklist contained in the database
     */
    public List<Blacklist> listBlacklist() {
        return (List<Blacklist>) getListFromDB("FROM Blacklist");
    }

    /**
     * updatew an existing BlackList in the database
     * 
     * @param blacklist the new blacklist informations
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int updateBlacklist(Blacklist blacklist) {
        return updateToDB(blacklist);
    }

    /**
     * delete a blacklist from the database
     * 
     * @param blacklist the blacklist we want to delete
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int deleteBlacklist(Blacklist blacklist) {
        return deleteToDB(blacklist);
    }
}
