/**
 * 
 * Class holding data returned after the domain layer has taken some action
 * 
 */
public class ResultSummary {

	public int turnScore;
	public Roll lastRoll;
	
	public int roundScore;
	public String playerName;
	public State currentState;
	public State nextState;
	public String winnerName;
	public int winningScore;
	public int winningChipCount;
	
	public State getCurrentState() {
		return currentState;
	}	
	public Roll getLastRoll() {
		return lastRoll;
	}
	public State getNextState() {
		return nextState;
	}
	public String getPlayerName() {
		return playerName;
	}
	public int getRoundScore() {
		return roundScore;
	}
	public int getTurnScore() {
		return turnScore;
	}
	public String getWinnerName() {
		return winnerName;
	}
	public int getWinningChipCount() {
		return winningChipCount;
	}
	public int getWinningScore() {
		return winningScore;
	}
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	public void setLastRoll(Roll lastRoll) {
		this.lastRoll = lastRoll;
	}
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setRoll(Roll roll) {
		this.lastRoll = roll;	
	}
	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}
	public void setTurnScore(int turnScore) {
		this.turnScore = turnScore;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	public void setWinningChipCount(int winningChipCount) {
		this.winningChipCount = winningChipCount;
	}
	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}
}
