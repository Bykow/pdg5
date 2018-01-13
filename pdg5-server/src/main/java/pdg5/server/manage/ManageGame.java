package pdg5.server.manage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

/**
 * Manager to store and load games from/to the database
 */
public class ManageGame extends Manager {

   /**
    * Constructor
    */
    public ManageGame() {
        super();
    }

    /**
     * Constructor
     * 
     * @param session the session used by the manager to do transactions
     */
    public ManageGame(Session session) {
        super(session);
    }

    /**
     * add a tournament game and all it's informations to the database
     * 
     * @param title of the game
     * @param player1 one of the players
     * @param player2 the other player
     * @param tournament the tournament informations to link with this game
     * @param remainingLetters the letters left in the stack
     * @return the game added to the database
     */
    public Game addGame(String title, User player1, User player2, Tournament tournament, String remainingLetters) {
        Game game = new Game();
        game.setTitle(title);
        game.setUserByPlayer1(player1);
        game.setUserByPlayer2(player2);
        game.setTournament(tournament);
        game.setCreated(new Date());
        game.setLastActivity(new Date());
        game.setRemainingLetters(remainingLetters);

        return (Game) addToDB(game);
    }

    /**
     * add a game and all it's informations to the database
     * 
     * @param title of the game
     * @param player1 one of the players
     * @param player2 the other player
     * @param remainingLetters the letters left in the stack
     * @return the game added to the database
     */
    public Game addGame(String title, User player1, User player2, String remainingLetters) {
        Game game = new Game();
        game.setTitle(title);
        game.setUserByPlayer1(player1);
        game.setUserByPlayer2(player2);
        game.setCreated(new Date());
        game.setLastActivity(new Date());
        game.setRemainingLetters(remainingLetters);

        return (Game) addToDB(game);
    }

    /**
     * return a list of all games contained in the database
     * 
     * @return a list of all games contained in the database
     */
    public List<Game> listGame() {
        return (List<Game>) getListFromDB("FROM Game");
    }

    /**
     * return a list of all games of a User
     * 
     * @param user the User we wish to get the games
     * @return a list of all games of a User
     */
    public List<Game> getGamesByUser(User user) {
        Session session = getSession();
        Transaction tx = null;
        List<Game> games = null;

        try {
            tx = session.beginTransaction();
            games = session.createQuery("FROM Game WHERE player1 =:p1 OR player2 =:p2")
                .setParameter("p1", user.getId())
                .setParameter("p2", user.getId())
                .list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return games;
    }

    /**
     * update informations about a game in the database
     * 
     * @param game the game with new information
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int updateGame(Game game) {
        return updateToDB(game);
    }

    /**
     * delete a game informations in the database
     * 
     * @param game the game we wish to delete the informations in the database
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int deleteGame(Game game) {
        return deleteToDB(game);
    }
}
