import edu.princeton.cs.introcs.StdOut;

public class SkunkApp {
	public static void main(String[] args) {
		
		StdOut.println("Welcome to Skunk");
		
		Die die1=new Die(new int[] {1,2,3,2,1});
		Die die2=new Die(new int[] {2,3,4,2,3});
		Dice dice1=new Dice(die1, die2);
		for (int i=0; i<5;i++){
			die1.roll();
			die2.roll();
		StdOut.println(dice1.getLastRoll());
		}
	}

}
