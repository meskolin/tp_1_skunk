import edu.princeton.cs.introcs.StdOut;

public class SkunkApp {
	public static void main(String[] args) {
		
		StdOut.println("Welcome to Skunk");
		Die d=new Die(new int[] {1,2,3,2,1});
		for (int i=0; i<5;i++){
			d.roll();
		
		StdOut.println(d.getLastRoll());
		}
	}

}
