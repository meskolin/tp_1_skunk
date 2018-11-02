import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

public class Game {
	ArrayList<Player> players;
	final int WINNING_Chip = 100;
	Game(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean gameDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getChipCount() >= WINNING_Chip) {
				StdOut.println("Player "+players.get(i).name+" won the game. Congratulations!");
				return true;
			}
		}
		return false;
	}

	public void playGame() {
		while (!gameDone()) {
				Round round=new Round(players);
				round.playRound();
				
			}
	}
}


