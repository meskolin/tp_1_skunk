
public class Player {
	
	Player(String name){
		this.name = name;
	}

	public int chipCount = 50;
	public String name;
	public int getChipCount() {
		return chipCount;
	}
	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}
}

