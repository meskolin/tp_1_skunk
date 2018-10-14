import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

public class Turn
{
	private int turnScore;
	private Roll lastRoll;
	private ArrayList<Roll> rollSequence;
	public Turn() {
		this.turnScore=0;
		this.lastRoll=null;
		this.rollSequence=new ArrayList<>();
	}
	public int getTurnScore() {
		return this.turnScore;
	}
	public Roll getLastRoll() {
		return this.lastRoll;
	}
	public void rollAgain() {
		this.lastRoll=new Roll();
		rollSequence.add(this.lastRoll);
	}
	public void scoreTurn() {
		Turn turn = new Turn(); 
		if(turn.getLastRoll().isDoubleSkunk()) {
			turnScore=0;
		}
		else if(turn.getLastRoll().isDeuceSkunk()) {
			turnScore=0;
		}
		else if(turn.getLastRoll().isSingleSkunk()) {
			turnScore=0;
		}
		else
			turnScore+=lastRoll.getDice().getLastRoll();
	}
	public boolean ends() {
		return lastRoll.isDeuceSkunk()||lastRoll.isDoubleSkunk()||lastRoll.isSingleSkunk();
	}
}
