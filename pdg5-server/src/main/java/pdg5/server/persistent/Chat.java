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

	private Game game;
	private Tournament tournament;
	private Set messages = new HashSet(0);

	public Chat() {
	}

	public Chat(Game game, Tournament tournament, Set messages) {
		this.game = game;
		this.tournament = tournament;
		this.messages = messages;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game")
	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tournament")
	public Tournament getTournament() {
		return this.tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Chat))
		{
			return false;
		}
			
		Chat b = (Chat) obj;
		
		return id == b.getId() && 
				((game == null && b.getGame() == null ) || game.equals(b.getGame())) &&
				((tournament == null && b.getTournament() == null ) || tournament.equals(b.getTournament())) &&
				messages.equals(b.getMessages());
	}
	
	@Override
	public String toString() {
		return id + ", " + game + ", " + tournament;
	}

}
