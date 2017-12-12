package pdg5.server.manage;

import java.util.Date;
import java.util.List;

import pdg5.server.persistent.Friend;
import pdg5.server.persistent.User;

public class ManageFriend extends Manager {
	public Friend addFriend(User fromUser, User toUser) {
		return (Friend) addToDB(new Friend(fromUser, toUser, new Date()));
	}
	
	public List<Friend> listFriend() {
		 return (List<Friend>) getListFromDB("FROM Friend");
	}
	
	public int updateFriend(Friend friend) {
		return updateToDB(friend);
	}
	
	public int deleteFriend(Friend friend) {
		return deleteFriend(friend);
	}
}
