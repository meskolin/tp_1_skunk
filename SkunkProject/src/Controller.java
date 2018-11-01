import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Controller {

	Round round;
	public State currentState = State.NOT_STARTED;
	ArrayList<Player> players;
	Kitty kitty;

	public Controller(GameParams params) {
		players = params.players;
		kitty = new Kitty();
		round = new Round(players);
		currentState = State.PLAYING_TURN;
	}
	
	public ResultSummary handleEvent(String input) {
		ResultSummary result = new ResultSummary();
		switch (currentState) {
		case WAITING_FOR_INPUT:
			if (input != null) {
				result.setNextState(State.PLAYING_TURN);
				currentState = result.getNextState();
			} else {
				// state stays the same
				break;
			}
		case PLAYING_TURN:
			result = round.playRoundStep(input);
			currentState = result.getNextState();
			break;
		case TURN_DONE:
			result = round.updateActivePlayer();	
			currentState = result.getNextState();
			break;
		case LAST_CHANCE:
			result = round.playLastChance(input);
			currentState = result.getNextState();
		case DONE:
			break;
		default:
			break;
		}
		return result;
	}		
	
}
