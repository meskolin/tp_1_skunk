import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Controller {

	Round round;
	String lastResponse;	
	public State currentState = State.NOT_STARTED;
	public ScoreBoard scoreBoard;
	  
	public String getInput() {
		return StdIn.readLine();
	}
	
	public void showOutput(String message) {
		StdOut.println(message);
	}
	
	public void handleEvents() {
		switch(currentState) {
			case NOT_STARTED:
				startGame();
				currentState = State.PLAYING_TURN;
			case PLAYING_TURN:
				currentState = round.playRoundStep(lastResponse);
				lastResponse = null;
				break;
			case TURN_DONE:
				//todo change when impl round
				currentState = State.DONE;
				break;
			case WAITING_FOR_INPUT:
				StdOut.println("Do you wanna roll? y or n (ENTER=>y) \n");
				lastResponse = getInput();
				currentState = State.PLAYING_TURN;
				break;
			case DONE:				
				break;
		default:
			break;				
		}		
	}
	
	public void startGame() {
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
		Kitty kitty = new Kitty();
		scoreBoard = new ScoreBoard(playerList, kitty);
		round = new Round(playerList, scoreBoard);
	}			
	
}
