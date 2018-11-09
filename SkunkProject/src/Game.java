import java.util.ArrayList;

public class Game {
	ArrayList<Player> players;
	final int WINNING_Chip = 100;
	Game(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean gameDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getChipCount() >= WINNING_Chip) {
				return true;
			}
		}
		return false;
	}
	
	public String getWinner() {
		String winner = "";
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getChipCount() >= WINNING_Chip) {
				winner += players.get(i).getName();
			}	
		}
		return winner;
	}
}


