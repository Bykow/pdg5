package pdg5.server.persistent;

import javax.persistence.*;
import java.util.Date;

/**
 * Friend generated by hbm2java
 */
@Entity
@Table(name = "friend", catalog = "pdg")
public class Friend extends AbstractData implements java.io.Serializable {

    private User userByToUser;
    private User userByFromUser;
    private Date lastMod;

    public Friend() {
    }

    public Friend(User userByToUser, User userByFromUser, Date lastMod) {
        this.userByToUser = userByToUser;
        this.userByFromUser = userByFromUser;
        this.lastMod = lastMod;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toUser", nullable = false)
    public User getUserByToUser() {
        return this.userByToUser;
    }

    public void setUserByToUser(User userByToUser) {
        this.userByToUser = userByToUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromUser", nullable = false)
    public User getUserByFromUser() {
        return this.userByFromUser;
    }

    public void setUserByFromUser(User userByFromUser) {
        this.userByFromUser = userByFromUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_mod", nullable = false, length = 19)
    public Date getLastMod() {
        return this.lastMod;
    }

    public void setLastMod(Date lastMod) {
        this.lastMod = lastMod;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Friend)) {
            return false;
        }

        Friend b = (Friend) obj;

        return id == b.getId()
            && userByFromUser.equals(b.getUserByFromUser())
            && userByToUser.equals(b.getUserByToUser())
            && lastMod.equals(b.getLastMod());
    }

    @Override
    public String toString() {
        return id + ", " + userByFromUser + ", " + userByToUser + ", " + lastMod;
    }

}
