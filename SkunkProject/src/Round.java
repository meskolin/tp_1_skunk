import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Round {

	ArrayList<Player> players;
	Kitty roundKitty = new Kitty();
	final int WINNING_SCORE = 20;

	Round(ArrayList<Player> players) {
		this.players = players;
	}

	// TODO let other players roll one more time
	public boolean roundDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() >= WINNING_SCORE) {
				return true;
			}
		}
		return false;
	}

	private void playTurn(Player activePlayer) {
		StdOut.println("Play one Turn for player" + (activePlayer.name));
		Turn turn = new Turn();
		while (true) {
			StdOut.println("Your current turn score is " + turn.getTurnScore());
			StdOut.println("Do you wanna roll? y or n (ENTER=>y)");
			String response = StdIn.readLine();

			if (response.equals("y")) {
				turn.rollAgain();
				turn.scoreTurn();
				StdOut.println("You rolled a " + turn.getLastRoll().getDice().getDie1().getLastRoll() + " and a "
						+ turn.getLastRoll().getDice().getDie2().getLastRoll());

				if (turn.ends()) {
					StdOut.println("Your turn is over :( ");
					break;
				}
			} else if (response.equals("n")) {
				StdOut.println("You declined a roll.");
				break;
			} else {
				StdOut.println("You entered an invalid response.");
				break;
			}
		}
		int turnScore = turn.getTurnScore();
		activePlayer.setRoundScore(activePlayer.getRoundScore() + turnScore);
		activePlayer.setChipCount(activePlayer.getChipCount() + turn.getChipChange());
		roundKitty.setChipCount(roundKitty.getChipCount() + turn.getKittyChange());
		StdOut.println("Player" + (activePlayer.name) + " scored: " + turnScore + " for this turn.");

		StdOut.println(
				"Player" + (activePlayer.name) + " scored: " + activePlayer.getRoundScore() + " for this round.");

		StdOut.println(
				"Player" + (activePlayer.name) + " has: " + activePlayer.getChipCount() + " chips after this turn.");
		StdOut.println("Kitty currently has: " + roundKitty.getChipCount() + " chips after this turn.");

	}

	public void playRound() {
		while (!roundDone()) {
			for (int i = 0; i < players.size(); i++) {
				Player active = players.get(i);
				playTurn(active);
				if (active.getRoundScore() >= WINNING_SCORE) {
					break;
				}
			}
		}
		// Last chance!!
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() < WINNING_SCORE) {
				playTurn(players.get(i));
			}
		}

	}

}
