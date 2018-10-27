import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Round {

	ArrayList<Player> players;
	Kitty roundKitty = new Kitty();
	final int WINNING_SCORE = 50;

	Round(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean roundDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() >= WINNING_SCORE) {
				return true;
			}
		}
		return false;
	}

	private void playTurn(Player activePlayer) {
		StdOut.println("Play one Turn for player: " + (activePlayer.name));
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
		StdOut.println("Player " + (activePlayer.name) + " scored: " + turnScore + " for this turn.");

		StdOut.println(
				"Player " + (activePlayer.name) + " scored: " + activePlayer.getRoundScore() + " for this round.");

		StdOut.println(
				"Player " + (activePlayer.name) + " has: " + activePlayer.getChipCount() + " chips after this turn.");
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
				StdOut.println("Now player "+players.get(i).name+"'s last chance to win.");
				playTurn(players.get(i));
			}
		}
		Player winner = findWinner();
		moveChips(winner);
	
		StdOut.println("Player " + winner.name + " won the round with a score of " + winner.getRoundScore());
		StdOut.println(winner.name+" has " + winner.getChipCount() + " chips");
	}
	
	private Player findWinner() {
		Player winner =players.get(0);
		for (int i = 0; i < players.size() - 1; i++) {
			winner = players.get(i);
			if (players.get(i + 1).getRoundScore() > players.get(i).getRoundScore()) {
				winner = players.get(i + 1);
			}
		}
		return winner;
	}
	
	private void moveChips(Player winner) {
		// winner gets kitty chips
		winner.setChipCount(winner.getChipCount() + roundKitty.getChipCount());
		
		// winner collects chips from other players
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != winner) {
				if (players.get(i).getRoundScore() == 0) {
					players.get(i).setChipCount(players.get(i).getChipCount() - 10);
					winner.setChipCount(winner.getChipCount() + 10);
				} else {
					players.get(i).setChipCount(players.get(i).getChipCount() - 5);
					winner.setChipCount(winner.getChipCount() + 5);
				}
			}
		}
		
	}

}
