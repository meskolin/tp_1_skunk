
public class ResultSummary {

	public int turnScore;
	public Roll lastRoll;
	
	public Roll getLastRoll() {
		return lastRoll;
	}
	public void setLastRoll(Roll lastRoll) {
		this.lastRoll = lastRoll;
	}
	public int roundScore;
	public String playerName;
	public State currentState;
	public State getCurrentState() {
		return currentState;
	}
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	public State nextState;
	
	public State getNextState() {
		return nextState;
	}
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}
	public int getTurnScore() {
		return turnScore;
	}
	public void setTurnScore(int turnScore) {
		this.turnScore = turnScore;
	}
	public int getRoundScore() {
		return roundScore;
	}
	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setRoll(Roll roll) {
		this.lastRoll = roll;
		
	}
	public String winnerName;
	public int winningScore;
	public int winningChipCount;
	
	public String getWinnerName() {
		return winnerName;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	public int getWinningScore() {
		return winningScore;
	}
	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}
	public int getWinningChipCount() {
		return winningChipCount;
	}
	public void setWinningChipCount(int winningChipCount) {
		this.winningChipCount = winningChipCount;
	}
}
