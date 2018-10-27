
public class Player {
	
	Player(String name){
		this.name = name;
	}

	public int chipCount = 50;
	public int roundScore = 0; 
	
	public int getRoundScore() {
		return roundScore;
	}
	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}

	public String name;
	
	public int getChipCount() {
		return chipCount;
	}
	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}
}

