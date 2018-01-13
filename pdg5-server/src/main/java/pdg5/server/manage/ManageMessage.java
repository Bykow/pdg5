package pdg5.server.manage;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Message;
import pdg5.server.persistent.User;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;

public class ManageMessage extends Manager {

    public ManageMessage() {
        super();
    }

    public ManageMessage(Session session) {
        super(session);
    }

    public Message addMessage(String content, User user, Chat chat) {
        return (Message) addToDB(new Message(chat, user, content, new Date()));
    }

    public List<Message> listMessages() {
        return (List<Message>) getListFromDB("FROM Message");
    }

    public int updateMessage(Message message) {
        return updateToDB(message);
    }

    public int deleteMessage(Message message) {
        return deleteToDB(message);
    }
}
