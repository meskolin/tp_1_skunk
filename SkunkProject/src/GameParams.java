import java.util.ArrayList;

/**
 * GameParams represents all information needed to initialize a game
 *
 */
public class GameParams {	
	ArrayList<Player> players;

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public GameParams(ArrayList<Player> players) {
		this.players = players;
	}
}
