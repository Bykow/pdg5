package pdg5.server.persistent;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tournament generated by hbm2java
 */
@Entity
@Table(name = "tournament", catalog = "pdg")
public class Tournament extends AbstractData implements java.io.Serializable {

    /**
     * title of the tournament
     */
    private String title;

    /**
     * date when was created the tournament
     */
    private Date created;

    /**
     * chats associated at the tournament
     */
    private Set chats = new HashSet(0);

    /**
     * matchlists contained in this tournament
     */
    private Set matchlists = new HashSet(0);

    /**
     * games played in this tournament
     */
    private Set games = new HashSet(0);

    /**
     * Constructor
     */
    public Tournament() {
    }

    /**
     * Constructor
     *
     * @param title   of the tournament
     * @param created date when was created the tournament
     */
    public Tournament(String title, Date created) {
        this.title = title;
        this.created = created;
    }

    /**
     * @param title      of the tournament
     * @param created    date when was created the tournament
     * @param chats      associated at the tournament
     * @param matchlists contained in this tournament
     * @param games      played in this tournament
     */
    public Tournament(String title, Date created, Set chats, Set matchlists, Set games) {
        this.title = title;
        this.created = created;
        this.chats = chats;
        this.matchlists = matchlists;
        this.games = games;
    }

    /**
     * return the title of the tournament
     *
     * @return the title of the tournament
     */
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return this.title;
    }

    /**
     * modify the title of the tournament
     *
     * @param title the new title of the tournament
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return the date when was created the tournament
     *
     * @return the date when was created the tournament
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 19)
    public Date getCreated() {
        return this.created;
    }

    /**
     * modify the date when was created the tournament
     *
     * @param created the new date when was created the tournament
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * return the chats associated at the tournament
     *
     * @return the chats associated at the tournament
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set getChats() {
        return this.chats;
    }

    /**
     * modify the chats associated at the tournament
     *
     * @param chats the new chats associated at the tournament
     */
    public void setChats(Set chats) {
        this.chats = chats;
    }

    /**
     * return the matchlists contained in this tournament
     *
     * @return the matchlists contained in this tournament
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set getMatchlists() {
        return this.matchlists;
    }

    /**
     * modify the matchlists contained in this tournament
     *
     * @param matchlists the new matchlists contained in this tournament
     */
    public void setMatchlists(Set matchlists) {
        this.matchlists = matchlists;
    }

    /**
     * return the games played in this tournament
     *
     * @return the games played in this tournament
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    public Set getGames() {
        return this.games;
    }

    /**
     * modify the games played in this tournament
     *
     * @param games the new games played in this tournament
     */
    public void setGames(Set games) {
        this.games = games;
    }

    /**
     * check if an object is equivalent to this instance
     *
     * @param obj the object we are checking the equality
     * @return true if they are identicals
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tournament)) {
            return false;
        }

        Tournament b = (Tournament) obj;

        return id == b.getId()
                && chats.equals(b.getChats())
                && ((created == null && b.getCreated() == null) || created.equals(b.getCreated()))
                && games.equals(b.getGames())
                && matchlists.equals(b.getMatchlists())
                && ((title == null && b.getTitle() == null) || title.equals(b.getTitle()));
    }

    /**
     * override the print of this class. Usefull for debug
     *
     * @return a string with the id, the title and the date when was created the game
     */
    @Override
    public String toString() {
        return id + ", " + title + ", " + created;
    }

}
