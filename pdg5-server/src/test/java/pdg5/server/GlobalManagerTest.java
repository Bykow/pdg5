//package pdg5.server;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import pdg5.server.manage.ManageBlacklist;
//import pdg5.server.manage.ManageChat;
//import pdg5.server.manage.ManageFriend;
//import pdg5.server.manage.ManageGame;
//import pdg5.server.manage.ManageMatchlist;
//import pdg5.server.manage.ManageMessage;
//import pdg5.server.manage.ManageTournament;
//import pdg5.server.manage.ManageUser;
//import pdg5.server.manage.Manager;
//import pdg5.server.persistent.Blacklist;
//import pdg5.server.persistent.Chat;
//import pdg5.server.persistent.Friend;
//import pdg5.server.persistent.Game;
//import pdg5.server.persistent.Matchlist;
//import pdg5.server.persistent.Message;
//import pdg5.server.persistent.Tournament;
//import pdg5.server.persistent.User;
//
//public class GlobalManagerTest {
//
//	@Test
//	public void testGlobal() {
//		ManageBlacklist mbl = new ManageBlacklist();
//		ManageFriend mf = new ManageFriend();
//		ManageGame mg = new ManageGame();
//		ManageMatchlist mml = new ManageMatchlist();
//		ManageTournament mt = new ManageTournament();
//		ManageUser mu = new ManageUser();
//		ManageChat mc = new ManageChat();
//		ManageMessage mm = new ManageMessage();
//
//		System.out.println("***** Global tests ****");
//
//		// create some users
//		User usr1 = mu.addUser("Sauron@mordor.org","Sauron", "1234");
//		User usr2 = mu.addUser("Saruman@isengard.org","saruman", "pass");
//		User usr3 = mu.addUser("aragorn@gondor.net","aragorn", "0000");
//		User usr4 = mu.addUser("gandalf@wizard.com","gandalf", "grey");
//
//		// list users
//		List<User> lu = mu.listUser();
//		System.out.println("***** Initial users ****");
//		for (User user : lu) {
//			System.out.println(user);
//		}
//
//		// get a specific user
//		System.out.println(mu.getUserByEmail("sauron@mordor.org"));
//		System.out.println(mu.getUserByUsername("saruman"));
//
//		// update user
//		usr4.setPass("white");
//		usr4.setEmail("gandalf.white@wizard.com");
//		usr4.setUsername("XxGandalfxX");
//		mu.updateUser(usr4);
//
//		lu = mu.listUser();
//		System.out.println("***** updated users ****");
//		for (User user : lu) {
//			System.out.println(user);
//		}
//
//		// create friendship
//		Friend f1 = mf.addFriend(usr2, usr4);
//		Friend f2 = mf.addFriend(usr3, usr4);
//		Friend f3 = mf.addFriend(usr2, usr3);
//
//		// list friendships
//		List<Friend> lf = mf.listFriend();
//		System.out.println("***** Initial friendship ****");
//		for (Friend friend : lf) {
//			System.out.println(friend);
//		}
//
//		// update friendship
//		f1.setUserByToUser(usr1);
//		mf.updateFriend(f1);
//		lf = mf.listFriend();
//		System.out.println("***** updated friendship ****");
//		for (Friend friend : lf) {
//			System.out.println(friend);
//		}
//
//		// destroy friendship
//		mf.deleteFriend(f3);
//		mf.deleteFriend(f2);
//		mf.deleteFriend(f1);
//		lf = mf.listFriend();
//		System.out.println("***** deleted friendship ****");
//		for (Friend friend : lf) {
//			System.out.println(friend);
//		}
//
//		// create black list
//		Blacklist b1 = mbl.addBlacklist(usr4, usr2);
//		Blacklist b2 = mbl.addBlacklist(usr3, usr2);
//		Blacklist b3 = mbl.addBlacklist(usr3, usr1);
//
//		// list Blacklist
//
//		System.out.println("***** Initial Blacklist ****");
//		List<Blacklist> lb = mbl.listBlacklist();
//		for (Blacklist Blacklist : lb) {
//			System.out.println(Blacklist);
//		}
//
//		// update Blacklist
//		b1.setUserByToUser(usr1);
//		mbl.updateBlacklist(b1);
//
//		System.out.println("***** update Blacklist ****");
//		lb = mbl.listBlacklist();
//		for (Blacklist Blacklist : lb) {
//			System.out.println(Blacklist);
//		}
//
//		// destroy black list
//		mbl.deleteBlacklist(b2);
//		mbl.deleteBlacklist(b3);
//		mbl.deleteBlacklist(b1);
//
//		System.out.println("***** destroy Blacklist ****");
//		lb = mbl.listBlacklist();
//		for (Blacklist Blacklist : lb) {
//			System.out.println(Blacklist);
//		}
//
//		// create tournament
//		Tournament t1 = mt.addTournament("Mordor Championship");
//
//		// list tournament
//		List<Tournament> lt = mt.listTournaments();
//		System.out.println("***** created tournament***");
//		for (Tournament tournament : lt) {
//			System.out.println(tournament);
//		}
//
//		// update tournament
//		t1.setTitle("One Ring tournament");
//		mt.updateTournament(t1);
//
//		lt = mt.listTournaments();
//		System.out.println("***** updated tournament ****");
//		for (Tournament tournament : lt) {
//			System.out.println(tournament);
//		}
//
//		// create matchlist
//		Matchlist ml1 = mml.addMatchlist(t1, usr1);
//		Matchlist ml2 = mml.addMatchlist(t1, usr2);
//		Matchlist ml3 = mml.addMatchlist(t1, usr3);
//
//		// List matchlist
//		List<Matchlist> lm = mml.listMatchlist();
//		System.out.println("***** created matchlist ****");
//		for (Matchlist matchList : lm) {
//			System.out.println(matchList);
//		}
//
//		// update matchlist
//		ml3.setUser(usr4);
//		mml.updateMatchlist(ml3);
//
//		lm = mml.listMatchlist();
//		System.out.println("***** updated matchlist ****");
//		for (Matchlist matchList : lm) {
//			System.out.println(matchList);
//		}
//
//		// destroy matchlist
//		mml.deleteMatchlist(ml3);
//		mml.deleteMatchlist(ml2);
//		mml.deleteMatchlist(ml1);
//
//		lm = mml.listMatchlist();
//		System.out.println("***** deleted matchlist ****");
//		for (Matchlist matchList : lm) {
//			System.out.println(matchList);
//		}
//
//		// create game
//		Game g1 = mg.addGame("Wizard battle", usr2, usr4);
//		Game g2 = mg.addGame("Evil battle", usr1, usr2,t1);
//
//		List<Game> lg = mg.listGame();
//		System.out.println("***** created games ****");
//		for (Game game : lg) {
//			System.out.println(game);
//		}
//
//		// update game
//		g2.setTitle("Power battle");
//		mg.updateGame(g2);
//
//		lg = mg.listGame();
//		System.out.println("***** updated games ****");
//		for (Game game : lg) {
//			System.out.println(game);
//		}
//
//		// create chat
//		Chat c1 = mc.addChatGame(g1);
//		Chat c2 = mc.addChatTournament(t1);
//
//		// list chat
//		List<Chat> lc = mc.listChats();
//		System.out.println("***** Initial chat ****");
//		for (Chat chat : lc) {
//			System.out.println(chat);
//		}
//
//		// update chat
//		c1.setGame(g2);
//		lc = mc.listChats();
//		System.out.println("***** update chat ****");
//		for (Chat chat : lc) {
//			System.out.println(chat);
//		}
//
//		// create message
//		Message m1 = mm.addMessage("What's up", usr1, c1);
//		Message m2 = mm.addMessage("nothing, you ?", usr2, c1);
//
//		// list message
//		System.out.println("***** Initial Message ****");
//		List<Message> lmm = mm.listMessages();
//		for (Message message : lmm) {
//			System.out.println(message);
//		}
//
//		// update message
//		m2.setContent("the sky");
//		mm.updateMessage(m2);
//
//		System.out.println("***** Initial Message ****");
//		lmm = mm.listMessages();
//		for (Message message : lmm) {
//			System.out.println(message);
//		}
//
//		// destroy message
//		mm.deleteMessage(m2);
//		mm.deleteMessage(m1);
//
//		// destroy chat
//		mc.deleteChat(c1);
//		mc.deleteChat(c2);
//
//		// destroy game
//		mg.deleteGame(g1);
//		mg.deleteGame(g2);
//
//		lg = mg.listGame();
//		System.out.println("***** deleted games ****");
//		for (Game game : lg) {
//			System.out.println(game);
//		}
//
//		// destroy tournament
//		mt.deleteTournament(t1);
//
//		lt = mt.listTournaments();
//		System.out.println("***** deleted tournament ****");
//		for (Tournament tournament : lt) {
//			System.out.println(tournament);
//		}
//
//		// destroy user
//		mu.deleteUser(usr1);
//		mu.deleteUser(usr2);
//		mu.deleteUser(usr3);
//		mu.deleteUser(usr4);
//
//		lu = mu.listUser();
//		System.out.println("***** deleted users ****");
//		for (User user : lu) {
//			System.out.println(user);
//		}
//
//		Manager.closeConversation();
//	}
//}
