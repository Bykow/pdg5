package pdg5.server.manage;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

import java.util.List;

/**
 * Manager to stock and load list of matches from a tournament from/to the database
 */
public class ManageMatchlist extends Manager {

    /**
     * add a list of matches for a specified tournament to the database
     *
     * @param tournament the tournament which we add the list of matches to
     * @param user       the user linked to this list of match
     * @return the new list of matches created
     */
    public Matchlist addMatchlist(Tournament tournament, User user) {
        return (Matchlist) addToDB(new Matchlist(tournament, user));
    }

    /**
     * return all the list of matches contained in the database
     *
     * @return all the list of matches contained in the database
     */
    public List<Matchlist> listMatchlist() {
        return (List<Matchlist>) getListFromDB("FROM Matchlist");
    }

    /**
     * update informations of a list of matches
     *
     * @param matchlist the new informations of list of match
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int updateMatchlist(Matchlist matchlist) {
        return updateToDB(matchlist);
    }

    /**
     * delete a match list from the database
     *
     * @param matchlist the list of matches we wish to delete
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int deleteMatchlist(Matchlist matchlist) {
        return deleteToDB(matchlist);
    }

}
