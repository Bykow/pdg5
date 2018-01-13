package pdg5.server.persistent;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "pdg", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
    ,
		@UniqueConstraint(columnNames = "username")})
public class User extends AbstractData implements java.io.Serializable {

   /**
    * email of the user
    */
    private String email;
    
    /**
     * username of the user
     */
    private String username;
    
    /**
     * password of the user
     */
    private String pass;
    
    /**
     * friends of the user
     */
    private Set friendsForToUser = new HashSet(0);
    
    /**
     * users for who the user is friend
     */
    private Set friendsForFromUser = new HashSet(0);
    
    /**
     * messages the user sent
     */
    private Set messages = new HashSet(0);
    
    /**
     * matchlist where the user participate
     */
    private Set matchlists = new HashSet(0);
    
    /**
     * blacklist users of this user
     */
    private Set blacklistsForToUser = new HashSet(0);
    
    /**
     * users who blacklisted this user
     */
    private Set blacklistsForFromUser = new HashSet(0);
    
    /**
     * games where this user is player 2
     */
    private Set gamesForPlayer2 = new HashSet(0);
    
    /**
     * games where this user is player 1
     */
    private Set gamesForPlayer1 = new HashSet(0);

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor
     * 
     * @param email of the user
     * @param username of the user
     */
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    /**
     * Constructor
     * 
     * @param email of the user
     * @param username of the user
     * @param pass password of the user
     * @param friendsForToUser friends of the user
     * @param friendsForFromUser users for who the user is friend
     * @param messages the user sent
     * @param matchlists matchlist where the user participate
     * @param blacklistsForToUser blacklist users of this user
     * @param blacklistsForFromUser User who blacklisted this user
     * @param gamesForPlayer2 games where this user is player 2
     * @param gamesForPlayer1 games where this user is player 1
     */
    public User(String email, String username, String pass, Set friendsForToUser, Set friendsForFromUser, Set messages,
        Set matchlists, Set blacklistsForToUser, Set blacklistsForFromUser, Set gamesForPlayer2,
        Set gamesForPlayer1) {
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.friendsForToUser = friendsForToUser;
        this.friendsForFromUser = friendsForFromUser;
        this.messages = messages;
        this.matchlists = matchlists;
        this.blacklistsForToUser = blacklistsForToUser;
        this.blacklistsForFromUser = blacklistsForFromUser;
        this.gamesForPlayer2 = gamesForPlayer2;
        this.gamesForPlayer1 = gamesForPlayer1;
    }

    /**
     * return the email of the user
     * 
     * @return the email of the user
     */
    @Column(name = "email", unique = true, nullable = false, length = 60)
    public String getEmail() {
        return this.email;
    }

    /**
     * modify the email of the user
     * 
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * return the username of the user
     * 
     * @return the username of the user
     */
    @Column(name = "username", unique = true, nullable = false, length = 60)
    public String getUsername() {
        return this.username;
    }

    /**
     * modify the username of the user
     * 
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * return the password of the user
     * 
     * @return the password of the user
     */
    @Column(name = "pass", length = 256)
    public String getPass() {
        return this.pass;
    }

    /**
     * modify the password of the user
     * 
     * @param pass the new password of the user
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * return the friends of the user
     * 
     * @return the friends of the user
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByToUser")
    public Set getFriendsForToUser() {
        return this.friendsForToUser;
    }

    /**
     * modify the friends of the user
     * 
     * @param friendsForToUser the new friends friends of the user
     */
    public void setFriendsForToUser(Set friendsForToUser) {
        this.friendsForToUser = friendsForToUser;
    }

    /**
     * return the users for who the user is friend
     * 
     * @return the users for who the user is friend
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByFromUser")
    public Set getFriendsForFromUser() {
        return this.friendsForFromUser;
    }

    /**
     * modify the users for who the user is friend
     * 
     * @param friendsForFromUser the new users for who the user is friend
     */
    public void setFriendsForFromUser(Set friendsForFromUser) {
        this.friendsForFromUser = friendsForFromUser;
    }

    /**
     * return the messages the user sent
     * 
     * @return the messages the user sent
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set getMessages() {
        return this.messages;
    }

    /**
     * modify the messages the user sent
     * 
     * @param messages the new messages the user sent
     */
    public void setMessages(Set messages) {
        this.messages = messages;
    }

    /**
     * return the matchlist where the user participate
     * 
     * @return the matchlist where the user participate
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set getMatchlists() {
        return this.matchlists;
    }

    /**
     * modify the matchlist where the user participate
     * 
     * @param matchlists the new matchlist where the user participate
     */
    public void setMatchlists(Set matchlists) {
        this.matchlists = matchlists;
    }

    /**
     * return the blacklist user's of this user
     * 
     * @return the blacklist user's of this user
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByToUser")
    public Set getBlacklistsForToUser() {
        return this.blacklistsForToUser;
    }

    /**
     * modify the blacklist user's of this user
     * 
     * @param blacklistsForToUser the new blacklist user's of this user
     */
    public void setBlacklistsForToUser(Set blacklistsForToUser) {
        this.blacklistsForToUser = blacklistsForToUser;
    }

    /**
     * return the users who blacklisted this user
     * 
     * @return the users who blacklisted this user
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByFromUser")
    public Set getBlacklistsForFromUser() {
        return this.blacklistsForFromUser;
    }

    /**
     * modify the users who blacklisted this user
     * 
     * @param blacklistsForFromUser the new users who blacklisted this user
     */
    public void setBlacklistsForFromUser(Set blacklistsForFromUser) {
        this.blacklistsForFromUser = blacklistsForFromUser;
    }

    /**
     * return the games where this user is player 2
     * 
     * @return the games where this user is player 2
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByPlayer2")
    public Set getGamesForPlayer2() {
        return this.gamesForPlayer2;
    }

    /**
     * modify the games where this user is player 2
     * 
     * @param gamesForPlayer2 the new games where this user is player 2
     */
    public void setGamesForPlayer2(Set gamesForPlayer2) {
        this.gamesForPlayer2 = gamesForPlayer2;
    }

    /**
     * return the games where this user is player 1
     * 
     * @return the games where this user is player 1
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByPlayer1")
    public Set getGamesForPlayer1() {
        return this.gamesForPlayer1;
    }

    /**
     * modify the games where this user is player 1
     * 
     * @param gamesForPlayer1 the new games where this user is player 1
     */
    public void setGamesForPlayer1(Set gamesForPlayer1) {
        this.gamesForPlayer1 = gamesForPlayer1;
    }

    /**
     * check if an object is equivalent to this instance
     * 
     * @param obj the object we are checking the equality
     * @return true if they are identicals
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User b = (User) obj;

        return id == b.getId()
            && ((username == null && b.getUsername() == null) || username.equals(b.getUsername()))
            && ((pass == null && b.getPass() == null) || pass.equals(b.getPass()))
            && email.equals(b.getEmail());
    }

    /**
     * override the print of this class. Usefull for debug
     * 
     * @return a string with the id, the username and the email
     */
    @Override
    public String toString() {
        return id + ", " + username + ", " + email;
    }
}
