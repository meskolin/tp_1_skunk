import edu.princeton.cs.introcs.StdOut;
public class Die
{
	private int lastRoll;
	private boolean isPredictable;
	private int[] predictable;
	private int nextPredict;
	
	public Die()
	{
		this.roll();
	}
	public Die(int[] predictable) {
		this.isPredictable=true;
		this.predictable=predictable;
		this.nextPredict=0;//index of next value to use as roll
	}
	public int getLastRoll() // getter or accessor method
	{

		return this.lastRoll;
	}
	protected void setLastRoll(int lastRoll) // setter or mutator method
	{
		this.lastRoll = lastRoll;
	}
	public void roll() // note how this changes Die's state, but doesn't return anything
	{
		if(isPredictable) {
			this.lastRoll=predictable[nextPredict];
			nextPredict++;
		}
		else		
			setLastRoll((int) (Math.random() * 6 + 1));
	}
	
	@Override
	public String toString() // this OVERRIDES the default Object.toString()
	{
		return "Die: " + this.getLastRoll();
	}
/*	public static void main(String[] args) {
		Die d=new Die(new int[] {1,2,3,2,1});
		for (int i=0; i<5;i++){
			d.roll();
		
		StdOut.println(d.getLastRoll());
		}
	}
	
*/
}
