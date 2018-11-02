import java.util.ArrayList;

public class Round {

	ArrayList<Player> players;
	Turn currentTurn;	
	Kitty roundKitty = new Kitty();
	final int WINNING_SCORE = 30;
	int activePlayerIndex = 0;
	int firstWinnerIndex = 0;

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
	
	public ResultSummary updateActivePlayer() {
		ResultSummary response = new ResultSummary();
		activePlayerIndex += 1;
		if (activePlayerIndex > players.size() - 1) {
			activePlayerIndex = 0;
		}	
		if (!roundDone()) {
			response.setNextState(State.PLAYING_TURN);
		} else {
			response.setNextState(State.LAST_CHANCE);
		}
		response.setPlayerName(this.players.get(activePlayerIndex).name);
		return response;
	}

	private ResultSummary playTurnStep(Player activePlayer, String input) {
		ResultSummary response = new ResultSummary();
		response.setNextState(State.PLAYING_TURN);		
		if(currentTurn == null) {
			currentTurn = new Turn();
		}
		
		int turnScore = currentTurn.getTurnScore();				
		
		if(input == null) {			
			response.setNextState(State.WAITING_FOR_INPUT);
		}		
		else if (input.equals("y")) {
			currentTurn.rollAgain();
			currentTurn.scoreTurn();
			response.setLastRoll(currentTurn.getLastRoll());
			
			turnScore = currentTurn.getTurnScore();
			if (currentTurn.ends()) {
				response.setNextState(State.TURN_DONE);
			} else {
				response.setNextState(State.WAITING_FOR_INPUT);
			}
		} else if (input.equals("n")) {
			response.setNextState(State.TURN_DONE);
		} else {
			response.setNextState(State.INVALID_RESPONSE);
		}						
		
		if(response.getNextState() == State.TURN_DONE) {
			if(currentTurn.getLastRoll().isDoubleSkunk()) {
				activePlayer.setRoundScore(0);
			} else {
				activePlayer.setRoundScore(activePlayer.getRoundScore() + turnScore);
			}
			activePlayer.setChipCount(activePlayer.getChipCount() + currentTurn.getChipChange());
			roundKitty.setChipCount(roundKitty.getChipCount() + currentTurn.getKittyChange());
			currentTurn = null;
		}
		response.setPlayerName(activePlayer.name);
		response.setRoundScore(activePlayer.roundScore);
		response.setTurnScore(turnScore);
		
		return response;
	}
	
	public ResultSummary playTurnStep(String input) {
		Player active = players.get(activePlayerIndex);				
		ResultSummary response = playTurnStep(active, input);
		response.setPlayerName(active.name);		
		return response;
	}

	public ResultSummary playLastChance(String input) { 
		ResultSummary response;
		if(activePlayerIndex != firstWinnerIndex) {
			Player active = players.get(activePlayerIndex);	
			response = playTurnStep(active, input);
		} else {			
			//last chances done!
			response = new ResultSummary();
			response.setNextState(State.DONE);
			Player winner = findWinner();
			moveChips(winner);
			response.setWinnerName(winner.name);
			response.setWinningChipCount(winner.getChipCount());
			response.setWinningScore(winner.getRoundScore());
		}				
		return response;
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
