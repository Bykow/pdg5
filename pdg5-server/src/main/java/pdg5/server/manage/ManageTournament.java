package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import pdg5.server.persistent.Tournament;

public class ManageTournament extends Manager {
	
	public Tournament addTournament(String title) {
		return (Tournament) addToDB(new Tournament(title, new Date()));
	}
	
	public List<Tournament> listTournaments() {
		 return (List<Tournament>) getListFromDB("FROM Tournament");
	}
	
	public void updateTournament(Tournament tournament) {
		updateToDB(tournament);
	}
	
	public void deleteTournament(Tournament tournament) {
		deleteToDB(tournament);
	}

}
