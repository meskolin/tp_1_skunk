import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

public class ScoreBoard {

	Kitty kitty;
	ScoreBoard(ArrayList<Player> players, Kitty kitty){
		this.kitty = kitty;
	}
	
	public void startTurn(Player activePlayer, int turnScore) {		
		StdOut.println("Play one Turn for player: " + (activePlayer.name) + "\n");		
	}
	
	public void showTurnStatus(int turnScore, int roundScore) {
		StdOut.println("Your current turn score is " + turnScore + "\n");
		StdOut.println("Your current round score is " + turnScore + "\n");
	}
	
	public void showTurnScoreForPlayer(Player activePlayer, int turnScore) {
		StdOut.println("Player " + (activePlayer.name) + " scored: " + turnScore + " for this turn. \n");

		StdOut.println(
				"Player " + (activePlayer.name) + " scored: " + activePlayer.getRoundScore() + " for this round. \n");

		StdOut.println(
				"Player " + (activePlayer.name) + " has: " + activePlayer.getChipCount() + " chips after this turn. \n");		
		StdOut.println("Kitty currently has: " + kitty.getChipCount() + " chips after this turn. \n");

	}
	
	public void showRoll(Roll roll) {
		StdOut.println("You rolled a " + roll.getDice().getDie1().getLastRoll() + " and a "
				+ roll.getDice().getDie2().getLastRoll() + "\n");

	}
	
	public void showRoundScore() {
		
	}
	
	public void showGameResult() {
		
	}
}
