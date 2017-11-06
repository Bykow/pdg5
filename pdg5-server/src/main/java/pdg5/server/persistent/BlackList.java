package pdg5.server.persistent;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Blacklist generated by hbm2java
 */
@Entity
@Table(name = "blacklist", catalog = "pdg")
public class BlackList implements java.io.Serializable {

	private Integer id;
	private User userByToUser;
	private User userByFromUser;
	private Date lastMod;

	public BlackList() {
	}

	public BlackList(User userByToUser, User userByFromUser, Date lastMod) {
		this.userByToUser = userByToUser;
		this.userByFromUser = userByFromUser;
		this.lastMod = lastMod;
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
		if(!(obj instanceof BlackList))
		{
			return false;
		}
			
		BlackList b = (BlackList) obj;
		
		return id == b.getId() &&
				userByFromUser.equals(b.getUserByFromUser()) &&
				userByToUser.equals(b.getUserByToUser()) &&
				lastMod.equals(lastMod);
	}
	
	@Override
	public String toString() {
		return id + ", " + userByFromUser + ", " + userByToUser + ", " + lastMod;
	}

}
