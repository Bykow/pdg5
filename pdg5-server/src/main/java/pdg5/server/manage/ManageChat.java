package pdg5.server.manage;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;

import java.util.List;
import org.hibernate.Session;

/**
 * Manager to stock and load the Chats from/to the database
 */
public class ManageChat extends Manager {

   /**
    * Constructor
    */
    public ManageChat() {
        super();
    }

    /**
     * Constructor
     * 
     * @param session the session used by the manager to do transactions
     */
    public ManageChat(Session session) {
        super(session);
    }

    /**
     * add a new chat for a specified tournament
     * 
     * @param tournament the tournament we wish to add a chat to
     * @return the chat created and added to the database
     */
    public Chat addChatTournament(Tournament tournament) {
        Chat chat = new Chat();
        chat.setTournament(tournament);
        return (Chat) addToDB(chat);
    }

    /**
     * add a new chat for a specified game
     * 
     * @param game the game we wish to add a chat to
     * @return the chat created and added to the database
     */
    public Chat addChatGame(Game game) {
        Chat chat = new Chat();
        chat.setGame(game);
        return (Chat) addToDB(chat);
    }

    /**
     * return all the chats of the database
     * 
     * @return all the chats of the database
     */
    public List<Chat> listChats() {
        return (List<Chat>) getListFromDB("FROM Chat");
    }

    /**
     * update an existing chat with informations of a new one
     * @param chat the chat with updated informations
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int updateChat(Chat chat) {
        return updateToDB(chat);
    }

    /**
     * delete a chat in the database
     * @param chat the chat we wish to delete
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int deleteChat(Chat chat) {
        return deleteToDB(chat);
    }
}
