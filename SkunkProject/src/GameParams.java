import java.util.ArrayList;

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
