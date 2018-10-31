import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

public class Round {

	ArrayList<Player> players;
	Turn currentTurn;
	int activePlayerIndex = 0;
	Kitty roundKitty = new Kitty();
	final int WINNING_SCORE = 50;
	private ScoreBoard scoreBoard;

	Round(ArrayList<Player> players, ScoreBoard board) {
		this.players = players;
		this.scoreBoard = board;
	}

	public boolean roundDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() >= WINNING_SCORE) {
				return true;
			}
		}
		return false;
	}

	private State playTurnStep(Player activePlayer, String input) {
		State nextState = State.PLAYING_TURN;		
		if(currentTurn == null) {
			currentTurn = new Turn();
			scoreBoard.startTurn(activePlayer, currentTurn.getTurnScore());
		}
		
		int turnScore = currentTurn.getTurnScore();				
		
		if(input == null) {			
			nextState =  State.WAITING_FOR_INPUT;
		}		
		else if (input.equals("y")) {
			currentTurn.rollAgain();
			currentTurn.scoreTurn();
			scoreBoard.showRoll(currentTurn.getLastRoll());
			
			turnScore = currentTurn.getTurnScore();
			if (currentTurn.ends()) {
				StdOut.println("Your turn is over :( \n");				
				nextState = State.TURN_DONE;
			} else {
				scoreBoard.showTurnStatus(currentTurn.getTurnScore(), activePlayer.getRoundScore() + turnScore);
				nextState = State.WAITING_FOR_INPUT;
			}
		} else if (input.equals("n")) {
			StdOut.println("You declined a roll. \n");
			nextState = State.TURN_DONE;
		} else {
			StdOut.println("You entered an invalid response. \n");
			nextState = State.WAITING_FOR_INPUT;
		}
		
		if(nextState == State.TURN_DONE) {			
			activePlayer.setRoundScore(activePlayer.getRoundScore() + turnScore);
			activePlayer.setChipCount(activePlayer.getChipCount() + currentTurn.getChipChange());
			roundKitty.setChipCount(roundKitty.getChipCount() + currentTurn.getKittyChange());
			this.scoreBoard.showTurnScoreForPlayer(activePlayer, turnScore);
			currentTurn = null;
		}
		
		return nextState;
	}

	public State playRoundStep(String input) {
		State next;
		if (!roundDone()) {
			next = singleTurn(input);
		} else {
			finishRound(input);
			next = State.DONE;
		}
		return next;
	}
	
	public State singleTurn(String input) {				
		Player active = players.get(activePlayerIndex);				
		State next = playTurnStep(active, input);
		if(next == State.TURN_DONE) {
			activePlayerIndex += 1;
			if(activePlayerIndex > players.size() -1) {
				activePlayerIndex = 0;
			}
			next = State.PLAYING_TURN;
		}
		return next;
	}

	private void finishRound(String input) {
		// Last chance!!
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() < WINNING_SCORE) {
				StdOut.println("Now player " + players.get(i).name + "'s last chance to win.");
				playTurnStep(players.get(i), input);
			}
		}
		Player winner = findWinner();
		moveChips(winner);

		StdOut.println("Player " + winner.name + " won the round with a score of " + winner.getRoundScore());
		StdOut.println(winner.name + " has " + winner.getChipCount() + " chips");

	}

	private Player findWinner() {
		Player winner = players.get(0);
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
