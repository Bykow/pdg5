package pdg5.server.persistent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.sql.rowset.serial.SerialException;

/**
 * Game generated by hbm2java
 */
@Entity
@Table(name = "game", catalog = "pdg")
public class Game implements java.io.Serializable {

	private Integer id;
	private Tournament tournament;
	private User userByPlayer2;
	private User userByPlayer1;
	private String title;
	private Date created;
	private Date lastActivity;
	private String remainingLetters;
	private Blob gameState;
	private Set chats = new HashSet(0);

	public Game() {
	}

	public Game(User userByPlayer2, User userByPlayer1, String title, Date created, Date lastActivity) {
		this.userByPlayer2 = userByPlayer2;
		this.userByPlayer1 = userByPlayer1;
		this.title = title;
		this.created = created;
		this.lastActivity = lastActivity;
	}

	public Game(Tournament tournament, User userByPlayer2, User userByPlayer1, String title, Date created,
			Date lastActivity, Set chats) {
		this.tournament = tournament;
		this.userByPlayer2 = userByPlayer2;
		this.userByPlayer1 = userByPlayer1;
		this.title = title;
		this.created = created;
		this.lastActivity = lastActivity;
		this.chats = chats;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tournament")
	public Tournament getTournament() {
		return this.tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player2", nullable = false)
	public User getUserByPlayer2() {
		return this.userByPlayer2;
	}

	public void setUserByPlayer2(User userByPlayer2) {
		this.userByPlayer2 = userByPlayer2;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player1", nullable = false)
	public User getUserByPlayer1() {
		return this.userByPlayer1;
	}

	public void setUserByPlayer1(User userByPlayer1) {
		this.userByPlayer1 = userByPlayer1;
	}

	@Column(name = "title", nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "remaining_letters", nullable = false, length = 100)
	public String getRemainingLetters() {
		return this.remainingLetters;
	}

	public void setRemainingLetters(String remainingLetters) {
		this.remainingLetters = remainingLetters;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_activity", nullable = false, length = 19)
	public Date getLastActivity() {
		return this.lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
	public Set getChats() {
		return this.chats;
	}

	public void setChats(Set chats) {
		this.chats = chats;
	}
	
	@Column(name = "game_state", nullable = true)
	public Blob getGameState() {
		return this.gameState;
	}
	
	public void setGameState(Serializable gameState) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(gameState);
		  out.flush();
		  byte[] bytes = bos.toByteArray();
		  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		  setGameState(blob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			  try {
			    bos.close();
			  } catch (IOException ex) {
			    // ignore close exception
			  }
			}
	}

	public void setGameState(Blob gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Game))
		{
			return false;
		}
			
		Game b = (Game) obj;
		
		return id == b.getId() && 
				((tournament == null && b.getTournament() == null) || tournament.equals(b.getTournament())) &&
				((userByPlayer1 == null && b.getUserByPlayer1() == null ) || userByPlayer1.equals(b.getUserByPlayer1())) &&
				((userByPlayer2 == null && b.getUserByPlayer2() == null ) || userByPlayer2.equals(b.getUserByPlayer2())) &&
				((title == null && b.getTitle() == null ) || title.equals(b.getTitle())) &&
				((remainingLetters == null && b.getRemainingLetters() == null ) || remainingLetters.equals(b.getRemainingLetters())) &&
				((created == null && b.getCreated() == null ) || created.equals(b.getCreated())) &&
				((lastActivity == null && b.getLastActivity() == null ) || lastActivity.equals(b.getLastActivity())) &&
				chats.equals(b.getChats());
	}
	
	@Override
	public String toString() {
		return id + ", " + title + ", " +  tournament + ", " + userByPlayer1 + ", " + userByPlayer2 + ", " + created;
	}

}
