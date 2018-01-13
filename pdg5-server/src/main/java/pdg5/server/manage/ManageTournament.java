package pdg5.server.manage;

import pdg5.server.persistent.Tournament;

import java.util.Date;
import java.util.List;

/**
 * Manager to stock and load tournaments from/to de database
 */
public class ManageTournament extends Manager {

   /**
    * add a new tournament to the database
    * 
    * @param title of the tournament
    * @return the new created tournament
    */
    public Tournament addTournament(String title) {
        return (Tournament) addToDB(new Tournament(title, new Date()));
    }

    /**
     * return all the tournament in the database
     * 
     * @return all the tournament in the database
     */
    public List<Tournament> listTournaments() {
        return (List<Tournament>) getListFromDB("FROM Tournament");
    }

    /**
     * update informations of a specified tournament
     * 
     * @param tournament the new informations of the tournament
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int updateTournament(Tournament tournament) {
        return updateToDB(tournament);
    }

    /**
     * delete a specified tournament contained in the database
     * 
     * @param tournament the tournament we wish to delete
     * @return Protocol.OK if the transaction succeed or Protocol.Error else
     */
    public int deleteTournament(Tournament tournament) {
        return deleteToDB(tournament);
    }

}
