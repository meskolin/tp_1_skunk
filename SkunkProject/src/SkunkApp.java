import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp {

	public static void main(String[] args)
	{
		StdOut.println("Hello! Welcome to 635 Skunk project!");
		StdOut.println("Please enter the number of players: ");
		int numPlayer = Integer.parseInt((StdIn.readLine()));
		ArrayList<Player> playerList=new ArrayList<>(numPlayer);
		
		for (int i=0; i < numPlayer; i++) {
			StdOut.println("Please enter Player "+(i+1)+"'s name:");
			StdOut.println();
			String playerName = StdIn.readLine();
			playerList.add(new Player(playerName));
		}
		Game game = new Game(playerList);
		game.playGame();
	}
}
