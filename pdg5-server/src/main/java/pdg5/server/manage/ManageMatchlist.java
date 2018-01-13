package pdg5.server.manage;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

import java.util.List;

/**
 * Manager to stock and load list of matches from a tournament from/to de database
 */
public class ManageMatchlist extends Manager {

   /**
    * add a list of match for a specified tournament to the database
    * 
    * @param tournament the tournament at wich we add the list of match
    * @param user the user linked to this list of match
    * @return the new list of match created
    */
    public Matchlist addMatchlist(Tournament tournament, User user) {
        return (Matchlist) addToDB(new Matchlist(tournament, user));
    }

    /**
     * return all the list of match contained in the database
     * 
     * @return all the list of match contained in the database
     */
    public List<Matchlist> listMatchlist() {
        return (List<Matchlist>) getListFromDB("FROM Matchlist");
    }

    /**
     * update informations of a list of match
     * 
     * @param matchlist the new informations of list of match
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int updateMatchlist(Matchlist matchlist) {
        return updateToDB(matchlist);
    }

    /**
     * delete a match list from the database
     * 
     * @param matchlist the list of match we wish to delete
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int deleteMatchlist(Matchlist matchlist) {
        return deleteMatchlist(matchlist);
    }

}
