package pdg5.server.manage;

import pdg5.server.persistent.Blacklist;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

public class ManageBlacklist extends Manager {
	public Blacklist addBlacklist(User fromUser, User toUser) {
		return (Blacklist) addToDB(new Blacklist(fromUser, toUser, new Date()));
	}
	
	public List<Blacklist> listBlacklist() {
		 return (List<Blacklist>) getListFromDB("FROM Blacklist");
	}
	
	public int updateBlacklist(Blacklist blacklist) {
		return updateToDB(blacklist);
	}
	
	public int deleteBlacklist(Blacklist blacklist) {
		return deleteToDB(blacklist);
	}
}
