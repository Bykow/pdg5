package pdg5.server.model;


import sun.misc.BASE64Encoder;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Represents a User in the DB
 */
@Entity
@Table(name = "users")
public class User {
    private final Calendar lastSeen = Calendar.getInstance();
    private Integer id_user;
    private String username;
    private byte[] passwordHash;
    private byte[] salt;
    private int privilege;

    /**
     * Get the id of the user
     *
     * @return the id
     */
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId_user() {
        return id_user;
    }

    /**
     * Set the id of the user
     *
     * @param id the new id
     */
    public void setId_user(Integer id) {
        id_user = id;
    }

    /**
     * Get the username of the user
     *
     * @return the username as a String
     */
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    /**
     * Get the password hash of the user
     *
     * @return the hash as a byte array
     */
    @Column(name = "password_hash")
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    /**
     * Set the password hash of the user
     *
     * @param passwordHash the new hash
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Get the salt of the user
     *
     * @return the salt as a byte array
     */
    @Column(name = "salt")
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Set the salt of the user
     *
     * @param salt the new salt
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * Get the privilege of the user
     *
     * @return the privilege as an int
     */
    @Column(name = "privilege")
    public int getPrivilege() {
        return privilege;
    }

    /**
     * Set the privilege of the user
     *
     * @param privilege the new privilege
     */
    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    /**
     * Get a String to print the user
     *
     * @return a String
     */
    public String toString() {
        return username + "{" + id_user + "}";
    }

    /**
     * Get a String to print the user with all info
     *
     * @return a String
     */
    public String showComplete() {
        BASE64Encoder b64e = new BASE64Encoder();
        String s = "";
        s += "Id:           " + id_user + "\r\n";
        s += "Username:     " + username + "\r\n";
        s += "PasswordHash: " + b64e.encode(passwordHash) + "\r\n";
        s += "Salt:         " + b64e.encode(salt) + "\r\n";
        s += "Privilege:    " + privilege + "\r\n";
        return s;
    }
}
