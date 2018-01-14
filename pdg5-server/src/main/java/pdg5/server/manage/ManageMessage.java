package pdg5.server.manage;

import org.hibernate.Session;
import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;

/**
 * Manager to stock and load messages from/to the database
 */
public class ManageMessage extends Manager {

    /**
     * constructor
     */
    public ManageMessage() {
        super();
    }

    /**
     * Constructor
     *
     * @param session the session used by the manager to do transactions
     */
    public ManageMessage(Session session) {
        super(session);
    }

    /**
     * add a message in a specified chat from a specified user to the database
     *
     * @param content of the message
     * @param user    the user who sent the message
     * @param chat    the associated chat where is writen the message
     * @return the new message created
     */
    public Message addMessage(String content, User user, Chat chat) {
        return (Message) addToDB(new Message(chat, user, content, new Date()));
    }

    /**
     * return all the messages in the database
     *
     * @return all the messages in the database
     */
    public List<Message> listMessages() {
        return (List<Message>) getListFromDB("FROM Message");
    }

    /**
     * change informations of a specific message in the database
     *
     * @param message the new informations of the message
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int updateMessage(Message message) {
        return updateToDB(message);
    }

    /**
     * delete a specified message in the database
     *
     * @param message the message we wish to delete
     * @return Protocol.OK if the transaction succeeded or Protocol.Error otherwise
     */
    public int deleteMessage(Message message) {
        return deleteToDB(message);
    }
}
