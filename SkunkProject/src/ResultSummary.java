import java.util.ArrayList;

/**
 * 
 * Class holding data returned after the domain layer has taken some action
 * 
 */
public class ResultSummary {

	private int turnScore;
	private Roll lastRoll;
	
	private int roundScore;
	
	private State currentState;
	private State nextState;
	
	private ArrayList<Player> players;
	
	private String activePlayerName;
	private String roundWinnerName;
	private String gameWinnerName;
	
	private int winningScore;
	private int winningChipCount;
	
	public String getGameWinnerName() {
		return gameWinnerName;
	}
	public void setGameWinnerName(String gameWinnerName) {
		this.gameWinnerName = gameWinnerName;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public State getCurrentState() {
		return currentState;
	}	
	public Roll getLastRoll() {
		return lastRoll;
	}
	public State getNextState() {
		return nextState;
	}
	public String getActivePlayerName() {
		return activePlayerName;
	}
	public int getRoundScore() {
		return roundScore;
	}
	public int getTurnScore() {
		return turnScore;
	}
	public String getRoundWinnerName() {
		return roundWinnerName;
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
	public void setActivePlayerName(String playerName) {
		this.activePlayerName = playerName;
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
	public void setRoundWinnerName(String winnerName) {
		this.roundWinnerName = winnerName;
	}
	public void setWinningChipCount(int winningChipCount) {
		this.winningChipCount = winningChipCount;
	}
	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}
}
