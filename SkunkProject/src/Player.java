
public class Player {
	
	Player(String name){
		this.name = name;
	}

	private int chipCount = 50;
	private int roundScore = 0; 
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoundScore() {
		return roundScore;
	}
	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}	
	
	public int getChipCount() {
		return chipCount;
	}
	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}
}

