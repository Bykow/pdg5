package pdg5.server;

import java.util.List;

import org.junit.Test;

import pdg5.server.manage.manageBlackList;
import pdg5.server.manage.manageChat;
import pdg5.server.manage.manageFriend;
import pdg5.server.manage.manageGame;
import pdg5.server.manage.manageMatchList;
import pdg5.server.manage.manageMessage;
import pdg5.server.manage.manageTournament;
import pdg5.server.manage.manageUser;
import pdg5.server.persistent.BlackList;
import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Friend;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.MatchList;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

public class testGlobalManager {
	
	@Test
	public void testGlobal() {
		manageBlackList mbl = new manageBlackList();
		manageFriend mf = new manageFriend();
		manageGame mg = new manageGame();
		manageMatchList mml = new manageMatchList();
		manageTournament mt = new manageTournament();
		manageUser mu = new manageUser();
		
		System.out.println("***** Global tests ****");
		
		// create some users
		User usr1 = mu.addUser("Sauron@mordor.org","Sauron", "1234");
		User usr2 = mu.addUser("Saruman@isengard.org","saruman", "pass");
		User usr3 = mu.addUser("aragorn@gondor.net","aragorn", "0000");
		User usr4 = mu.addUser("gandalf@wizard.com","gandalf", "grey");
		
		// list users
		List<User> lu = mu.listUser();
		System.out.println("***** Initial users ****");
		for (User user : lu) {
			System.out.println(user);
		}
		
		// get a specific user
		System.out.println(mu.getUserByEmail("sauron@mordor.org"));
		System.out.println(mu.getUserByUsername("saruman"));
		
		// update user
		usr4.setPass("white");
		usr4.setEmail("gandalf.white@wizard.com");
		usr4.setUsername("XxGandalfxX");
		mu.updateUser(usr4);
		
		lu = mu.listUser();
		System.out.println("***** updated users ****");
		for (User user : lu) {
			System.out.println(user);
		}
		
		// create friendship
		Friend f1 = mf.addFriend(usr2.getID(), usr4.getID());
		Friend f2 = mf.addFriend(usr3.getID(), usr4.getID());
		Friend f3 = mf.addFriend(usr2.getID(), usr3.getID());
		
		// list friendships
		List<Friend> lf = mf.listFriend();
		System.out.println("***** Initial friendship ****");
		for (Friend friend : lf) {
			System.out.println(friend);
		}
		
		// update friendship
		f1.setToUser(usr1.getID());
		mf.updateFriend(f1);
		lf = mf.listFriend();
		System.out.println("***** updated friendship ****");
		for (Friend friend : lf) {
			System.out.println(friend);
		}
		
		// destroy friendship
		mf.deleteFriend(f3);
		mf.deleteFriend(f2);
		mf.deleteFriend(f1);
		lf = mf.listFriend();
		System.out.println("***** deleted friendship ****");
		for (Friend friend : lf) {
			System.out.println(friend);
		}
		
		// create black list
		BlackList b1 = mbl.addBlackList(usr4.getID(), usr2.getID());
		BlackList b2 = mbl.addBlackList(usr3.getID(), usr2.getID());
		BlackList b3 = mbl.addBlackList(usr3.getID(), usr1.getID());
		
		// list blacklist
		
		System.out.println("***** Initial blacklist ****");
		List<BlackList> lb = mbl.listBlackList();
		for (BlackList blackList : lb) {
			System.out.println(blackList);
		}
		
		// update blacklist
		b1.setToUser(usr1.getID());
		mbl.updateBlackList(b1);
		
		System.out.println("***** update blacklist ****");
		lb = mbl.listBlackList();
		for (BlackList blackList : lb) {
			System.out.println(blackList);
		}
		
		// destroy black list
		mbl.deleteBlackList(b2);
		mbl.deleteBlackList(b3);
		mbl.deleteBlackList(b1);
		
		System.out.println("***** destroy blacklist ****");
		lb = mbl.listBlackList();
		for (BlackList blackList : lb) {
			System.out.println(blackList);
		}
		
		// create tournament
		Tournament t1 = mt.addTournament("Mordor Championship");
		
		// list tournament
		List<Tournament> lt = mt.listTournaments();
		System.out.println("***** created tournament***");
		for (Tournament tournament : lt) {
			System.out.println(tournament);
		}
		
		// update tournament
		t1.setTitle("One Ring tournament");
		mt.updateTournament(t1);
		
		lt = mt.listTournaments();
		System.out.println("***** updated tournament ****");
		for (Tournament tournament : lt) {
			System.out.println(tournament);
		}
		
		// create matchlist
		MatchList ml1 = mml.addMatchList(t1.getID(), usr1.getID());
		MatchList ml2 = mml.addMatchList(t1.getID(), usr2.getID());
		MatchList ml3 = mml.addMatchList(t1.getID(), usr3.getID());
		
		// List matchlist
		List<MatchList> lm = mml.listMatchList();
		System.out.println("***** created matchlist ****");
		for (MatchList matchList : lm) {
			System.out.println(matchList);
		}
		
		// update matchlist
		ml3.setUser(usr4.getID());
		mml.updateMatchList(ml3);
		
		lm = mml.listMatchList();
		System.out.println("***** updated matchlist ****");
		for (MatchList matchList : lm) {
			System.out.println(matchList);
		}
		
		// destroy matchlist
		mml.deleteMatchList(ml3);
		mml.deleteMatchList(ml2);
		mml.deleteMatchList(ml1);
		
		lm = mml.listMatchList();
		System.out.println("***** deleted matchlist ****");
		for (MatchList matchList : lm) {
			System.out.println(matchList);
		}
		
		// create game
		Game g1 = mg.addGame("Wizard battle", usr2.getID(), usr4.getID());
		Game g2 = mg.addGame("Evil battle", usr1.getID(), usr2.getID(),t1.getID());
		
		List<Game> lg = mg.listGame();
		System.out.println("***** created games ****");
		for (Game game : lg) {
			System.out.println(game);
		}
		
		// update game
		g2.setTitle("Power battle");
		mg.updateGame(g2);
		
		lg = mg.listGame();
		System.out.println("***** updated games ****");
		for (Game game : lg) {
			System.out.println(game);
		}
		
		// destroy game
		mg.deleteGame(g1);
		mg.deleteGame(g2);
		
		lg = mg.listGame();
		System.out.println("***** deleted games ****");
		for (Game game : lg) {
			System.out.println(game);
		}
		
		// destroy tournament
		mt.deleteTournament(t1);
		
		lt = mt.listTournaments();
		System.out.println("***** deleted tournament ****");
		for (Tournament tournament : lt) {
			System.out.println(tournament);
		}
		
		// destroy user
		mu.deleteUser(usr1);
		mu.deleteUser(usr2);
		mu.deleteUser(usr3);
		mu.deleteUser(usr4);
		
		lu = mu.listUser();
		System.out.println("***** deleted users ****");
		for (User user : lu) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void testGlobalChat() {
		manageGame mg = new manageGame();
		manageTournament mt = new manageTournament();
		manageUser mu = new manageUser();
		manageChat mc = new manageChat();
		manageMessage mm = new manageMessage();
		
		System.out.println("***** Global Chat tests ****");
		
		// create user
		User usr1 = mu.addUser("Jean-Claude@tst.org","JC", "1234");
		User usr2 = mu.addUser("Jean-Michel@tst.org","JM", "pass");
		User usr3 = mu.addUser("Jean-Edouard@tst.com","JE", "grey");
		
		// create tournament
		Tournament t1 = mt.addTournament("keke Championship");
		
		// create game
		Game g1 = mg.addGame("JJ battle", usr2.getID(), usr3.getID());
		Game g2 = mg.addGame("Jean battle", usr1.getID(), usr2.getID(),t1.getID());
		
		// create chat
		Chat c1 = mc.addChatGame(g1.getID());
		Chat c2 = mc.addChatTournament(t1.getID());
		
		// list chat
		List<Chat> lc = mc.listChats();
		System.out.println("***** Initial chat ****");
		for (Chat chat : lc) {
			System.out.println(chat);
		}
		
		// update chat
		c1.setGame(g2.getID());
		lc = mc.listChats();
		System.out.println("***** update chat ****");
		for (Chat chat : lc) {
			System.out.println(chat);
		}
		
		// create message
		Message m1 = mm.addMessage("What's up", usr1.getID(), c1.getID());
		Message m2 = mm.addMessage("nothing, you ?", usr2.getID(), c1.getID());
		
		// list message
		System.out.println("***** Initial Message ****");
		List<Message> lm = mm.listMessages();
		for (Message message : lm) {
			System.out.println(message);
		}
		
		// update message
		m2.setContent("the sky");
		mm.updateMessage(m2);
		
		System.out.println("***** Initial Message ****");
		lm = mm.listMessages();
		for (Message message : lm) {
			System.out.println(message);
		}
		
		// destroy message
		mm.deleteMessage(m2);
		mm.deleteMessage(m1);
		
		// destroy chat
		mc.deleteChat(c1);
		mc.deleteChat(c2);
		
		// destroy everything else
		mg.deleteGame(g1);
		mg.deleteGame(g2);
		mt.deleteTournament(t1);
		mu.deleteUser(usr1);
		mu.deleteUser(usr2);
		mu.deleteUser(usr3);
	}

}
