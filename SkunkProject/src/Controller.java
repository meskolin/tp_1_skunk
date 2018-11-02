import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Controller {

	Round round;
	public State currentState = State.NOT_STARTED;
	ArrayList<Player> players;

	public Controller(GameParams params) {
		players = params.players;
		round = new Round(players);
		currentState = State.PLAYING_TURN;
	}
	
	public ResultSummary handleEvent(String input) {
		ResultSummary result = new ResultSummary();		
		switch (currentState) {
		case INVALID_RESPONSE:
			//Try to get input again
			result.setNextState(State.WAITING_FOR_INPUT);
			break;
		case WAITING_FOR_INPUT:
			if (input != null) {
				result.setNextState(State.PLAYING_TURN);
			} else {
				// state stays the same
				result.setNextState(State.WAITING_FOR_INPUT);				
				break;
			}
		case PLAYING_TURN:
			result = round.playTurnStep(input);
			break;
		case TURN_DONE:
			result = round.updateActivePlayer();	
			break;
		case LAST_CHANCE:
			result = round.playLastChance(input);
		case DONE:
			break;
		default:
			break;
		}
		//Advance to next state
		result.setCurrentState(currentState);
		currentState = result.getNextState();

		return result;
	}		
	
}
