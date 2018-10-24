import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp implements Reporter
{

	public static void main(String[] args)
	{
		StdOut.println("Hello! Welcome to 635 Skunk project!");
		StdOut.println("Please enter the number of players: ");
		int numPlayer=StdIn.readInt();
		ArrayList<String> playerList=new ArrayList<>(numPlayer);
		for (int i=0; i<numPlayer; i++) {
			StdOut.println("Please enter Player"+(i+1)+"'s name:");
			String playerName=StdIn.readLine();
			playerList.add(playerName);
			StdOut.println(playerList);
		}
		
		for (int i=0; i<numPlayer;i++) {
		StdOut.println("Play one Turn for player"+(i+1));
		String player = "player"+(i+1);
		Turn turn = new Turn(); 
		
		while(true) {
			StdOut.println("Your current turn score is " + turn.getTurnScore());
			StdOut.println("Do you wanna roll? y or n (ENTER=>y)");
			String response=StdIn.readLine();
		
			if (response.equals("y")) {
				turn.rollAgain();
				turn.scoreTurn();			
				StdOut.println("You rolled a "+turn.getLastRoll().getDice().getDie1().getLastRoll() + " and a " +
						turn.getLastRoll().getDice().getDie2().getLastRoll());
				
				if (turn.ends()) {
					StdOut.println("Your turn is over :( ");
					break;
				}
			}
			else if (response.equals("n")) {
				StdOut.println("You declined a roll.");
				break;
			}
			else {
				StdOut.println("You entered an invalid response.");
				break;
			}
		}
		int turnScore=turn.getTurnScore();
		StdOut.println("Player"+(i+1)+ " scored: "+turnScore+" for this turn.");
		}
	
	}
	@Override
		public void showMessage(String msg) 
		{
			StdOut.println(msg);
		}
}
