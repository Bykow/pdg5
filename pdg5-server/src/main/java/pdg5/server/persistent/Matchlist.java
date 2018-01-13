package pdg5.server.persistent;

import javax.persistence.*;

/**
 * Matchlist generated by hbm2java
 */
@Entity
@Table(name = "matchlist", catalog = "pdg")
public class Matchlist extends AbstractData implements java.io.Serializable {

    private Integer id;
    private Tournament tournament;
    private User user;

    public Matchlist() {
    }

    public Matchlist(Tournament tournament, User user) {
        this.tournament = tournament;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TournamentID", nullable = false)
    public Tournament getTournament() {
        return this.tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matchlist)) {
            return false;
        }

        Matchlist b = (Matchlist) obj;

        return id == b.getId() &&
                tournament.equals(b.getTournament()) &&
                user.equals(b.getUser());
    }

    @Override
    public String toString() {
        return id + ", " + tournament + ", " + user;
    }

}
