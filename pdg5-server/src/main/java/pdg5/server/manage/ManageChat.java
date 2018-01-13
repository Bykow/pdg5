package pdg5.server.manage;

import pdg5.server.persistent.Chat;
import pdg5.server.persistent.Game;
import pdg5.server.persistent.Tournament;

import java.util.List;

public class ManageChat extends Manager {
    public Chat addChatTournament(Tournament tournament) {
        Chat chat = new Chat();
        chat.setTournament(tournament);
        return (Chat) addToDB(tournament);
    }

    public Chat addChatGame(Game game) {
        Chat chat = new Chat();
        chat.setGame(game);
        return (Chat) addToDB(game);
    }

    public List<Chat> listChats() {
        return (List<Chat>) getListFromDB("FROM Chat");
    }

    public int updateChat(Chat chat) {
        return updateToDB(chat);
    }

    public int deleteChat(Chat chat) {
        return deleteToDB(chat);
    }
}
