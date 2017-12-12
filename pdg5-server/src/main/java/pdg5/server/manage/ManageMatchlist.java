package pdg5.server.manage;

import pdg5.server.persistent.Matchlist;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

import java.util.List;

public class ManageMatchlist extends Manager {
	
	public Matchlist addMatchlist(Tournament tournament, User user) {
		return (Matchlist) addToDB(new Matchlist(tournament, user));
	}
	
	public List<Matchlist> listMatchlist() {
		 return (List<Matchlist>) getListFromDB("FROM Matchlist");
	}
	
	public int updateMatchlist(Matchlist matchlist) {
		return updateToDB(matchlist);
	}
	
	public int deleteMatchlist(Matchlist matchlist) {
		return deleteMatchlist(matchlist);
	}

}
