import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Round {

	ArrayList<Player> players;
	Kitty roundKitty=new Kitty();
	Round(ArrayList<Player> players){
		this.players = players;
	}
	
	public void playRound() {

		for (int i=0; i<players.size();i++) {
		StdOut.println("Play one Turn for player"+(i+1));
		Turn turn = new Turn(); 
		Player activePlayer=players.get(i);
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
		activePlayer.setChipCount(activePlayer.getChipCount()+turn.getChipChange());
		roundKitty.setChipCount(roundKitty.getChipCount()+turn.getKittyChange());
		StdOut.println("Player"+(i+1)+ " scored: "+turnScore+" for this turn.");
		StdOut.println("Player"+(i+1)+ " has: "+activePlayer.getChipCount()+" chips after this turn.");
		StdOut.println("Kitty currently has: "+roundKitty.getChipCount()+" chips after this turn.");
		}
	}
	
}
