import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

public class Turn
{
	private int turnScore;
	public int chipChange=0;
	public int kittyChange=0;
	private Roll lastRoll;

	protected ArrayList<Roll> rollSequence;
	public Turn() {
		this.turnScore=0;
		this.lastRoll=null;
		this.rollSequence=new ArrayList<>();
	}
	public int getTurnScore() {
		return this.turnScore;
	}
	public int getChipChange() {
		return this.chipChange;
	}
	public int getKittyChange() {
		return this.kittyChange;	
	}
	
	public void setLastRoll(Roll lastRoll) {
		this.lastRoll = lastRoll;
	}
	
	public Roll getLastRoll() {
		return this.lastRoll;
	}
	public void rollAgain() {
		this.lastRoll=new Roll();
		rollSequence.add(this.lastRoll);
	
	}
	public void scoreTurn() {
		if(this.getLastRoll().isDoubleSkunk()) {
			chipChange-=4;
			kittyChange+=4;
			turnScore=0;
		}
		else if(this.getLastRoll().isDeuceSkunk()) {
			chipChange-=2;
			kittyChange+=2;
			turnScore=0;
		}
		else if(this.getLastRoll().isSingleSkunk()) {
			chipChange-=1;
			kittyChange+=1;
			turnScore=0;
		}
		else
			turnScore+=lastRoll.getDice().getLastRoll();	
	}
	
	public boolean ends() {
		return lastRoll.isDeuceSkunk()||lastRoll.isDoubleSkunk()||lastRoll.isSingleSkunk();
	}
	
}
