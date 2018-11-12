import java.util.ArrayList;

public class Round {

	ArrayList<Player> players;
	Turn currentTurn;
	/*
	 * Flag representing if round is in last chance stage
	 */
	boolean lastChance = false;
	Kitty roundKitty = new Kitty();
	
	/*
	 * Score at which a round is considered done
	 */
	final int WINNING_SCORE = 100;
	
	/*
	 * Index of the currently active player
	 */
	int activePlayerIndex = 0;
	/*
	 * Index of the first player to reach the winning score for the round
	 */
	int firstWinnerIndex = -1;

	Round(ArrayList<Player> players) {
		this.players = players;
		//Start round score at zero
		for(int i =0 ; i< players.size(); i++) {
			players.get(i).setRoundScore(0);
		}
	}

	public Player findWinner() {
		int max = 0;
		Player winner = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getRoundScore() > max) {
				winner = players.get(i);
				firstWinnerIndex = i;
				max = winner.getRoundScore();
			}
		}		
		return winner;
	}
	
	public void moveChips(Player winner) {
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

	public ResultSummary playTurnStep(boolean keepRolling) {		
		Player active = players.get(activePlayerIndex);				
		ResultSummary response = playTurnStep(active, keepRolling);
		response.setActivePlayerName(active.getName());		
		return response;
	}
	
	/*
	 * Plays a turn for the active player until the turn is done or input is required
	 * 
	 */
	private ResultSummary playTurnStep(Player activePlayer, boolean keepRolling) {
		ResultSummary response = new ResultSummary();

		if(currentTurn == null) {
			currentTurn = new Turn();
		}							
		if (keepRolling) {
			currentTurn.rollAgain();
			currentTurn.scoreTurn();
			response.setLastRoll(currentTurn.getLastRoll());			
			if (currentTurn.ends()) {
				response.setNextState(State.TURN_DONE);
			} else {
				response.setNextState(State.PLAYING_TURN);
			}
		} else {
			response.setNextState(State.TURN_DONE);
		} 		
				
		int turnScore = currentTurn.getTurnScore();
		if(response.getNextState() == State.TURN_DONE) {
			finishTurn(activePlayer);			
		}
		response.setActivePlayerName(activePlayer.getName());
		response.setRoundScore(activePlayer.getRoundScore());
		response.setTurnScore(turnScore);
				
		return response;
	}

	/*
	 * Finish the turn by adding up chips and checking for round winner
	 */
	private void finishTurn(Player activePlayer) {
		if((currentTurn.getLastRoll() != null) 
				&& (currentTurn.getLastRoll().isDoubleSkunk())) {
			activePlayer.setRoundScore(0);
		} else {
			activePlayer.setRoundScore(activePlayer.getRoundScore() + currentTurn.getTurnScore());
		}
		activePlayer.setChipCount(activePlayer.getChipCount() + currentTurn.getChipChange());
		roundKitty.setChipCount(roundKitty.getChipCount() + currentTurn.getKittyChange());
		currentTurn = null;
		
		if(!lastChance && playerHasWinningScore(activePlayer)) {
			firstWinnerIndex = activePlayerIndex;
			lastChance = true;
		}
	}
	
	/*
	 * Check if current player has reached a winning round score
	 */
	private boolean playerHasWinningScore(Player player) {
		if (player.getRoundScore() >= WINNING_SCORE) {
			return true;
		}
		return false;
	}
	
	public boolean roundDone() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getRoundScore() >= WINNING_SCORE) {				
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Returns true if each eligible player has finished the last chance round 
	 */
	public boolean lastChanceDone() {
		if( lastChance && (activePlayerIndex == firstWinnerIndex)) {
			return true;
		}
		return false;
	}

	/*
	 * Advance the active player index to the next in the list
	 */
	public void updateActivePlayer() {	
		activePlayerIndex += 1;
		if (activePlayerIndex > players.size() - 1) {
			activePlayerIndex = 0;
		}	
	}
	
	/*
	 * Get the next state based on round status
	 */
	public ResultSummary determineNextState(){
		ResultSummary response = new ResultSummary();
		if (!roundDone()) {
			response.setNextState(State.PLAYING_TURN);
			response.setRoundScore(this.players.get(activePlayerIndex).getRoundScore());
		} else if (lastChance == true && !lastChanceDone()){
			response.setNextState(State.LAST_CHANCE);
		} else {
			response.setNextState(State.ROUND_DONE);
			Player winner = findWinner();
			moveChips(winner);
			response.setRoundWinnerName(winner.getName());
			response.setWinningChipCount(winner.getChipCount());
			response.setWinningScore(winner.getRoundScore());
			response.setPlayers(players);			
		}
		response.setActivePlayerName(this.players.get(activePlayerIndex).getName());
		return response;
	}

}
