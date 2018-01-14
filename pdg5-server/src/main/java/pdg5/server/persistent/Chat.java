package pdg5.server.persistent;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Chat generated by hbm2java
 */
@Entity
@Table(name = "chat", catalog = "pdg")
public class Chat extends AbstractData implements java.io.Serializable {

    /**
     * game associated to this chat
     */
    private Game game;

    /**
     * tournament associated to this chat
     */
    private Tournament tournament;

    /**
     * set of message contained in this chat
     */
    private Set<Message> messages = new HashSet(0);

    /**
     * Constructor
     */
    public Chat() {
    }

    /**
     * Constructor
     *
     * @param game       game associated to this chat
     * @param tournament tournament associated to this chat
     * @param messages   set of message contained in this chat
     */
    public Chat(Game game, Tournament tournament, Set<Message> messages) {
        this.game = game;
        this.tournament = tournament;
        this.messages = messages;
    }

    /**
     * return the game associated to this chat
     *
     * @return the game associated to this chat
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game")
    public Game getGame() {
        return this.game;
    }

    /**
     * modify the game associated to this chat
     *
     * @param game the new game associated to this chat
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * return the tournament associated to this chat
     *
     * @return the tournament associated to this chat
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament")
    public Tournament getTournament() {
        return this.tournament;
    }

    /**
     * modify the tournament associated to this chat
     *
     * @param tournament the new tournament associated to this chat
     */
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    /**
     * return the set of message contained in this chat
     *
     * @return the set of message contained in this chat
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    public Set<Message> getMessages() {
        return this.messages;
    }

    /**
     * modify the set of message contained in this chat
     *
     * @param messages the new set of message contained in this chat
     */
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    /**
     * check if an object is equivalent to this instance
     *
     * @param obj the object we are checking the equality
     * @return true if they are identical
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Chat)) {
            return false;
        }

        Chat b = (Chat) obj;

        return id == b.getId()
                && ((game == null && b.getGame() == null) || game.equals(b.getGame()))
                && ((tournament == null && b.getTournament() == null) || tournament.equals(b.getTournament()))
                && messages.equals(b.getMessages());
    }

    /**
     * override the print of this class
     *
     * @return a string with the unique id, the game and the tournament associated
     */
    @Override
    public String toString() {
        return id + ", " + game + ", " + tournament;
    }

}
