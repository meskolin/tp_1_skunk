import java.util.ArrayList;

public class Controller {

	Round round;
	Game game;
	public State currentState = State.NOT_STARTED;
	ArrayList<Player> players;

	public Controller(GameParams params) {
		players = params.players;
		game = new Game(players);
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
			break;
		case ROUND_DONE:
			if(!game.gameDone()) {
				round = new Round(players);
				result.setNextState(State.PLAYING_TURN);
			} else {
				result.setGameWinnerName(game.getWinner());
				result.setNextState(State.GAME_DONE);
			}
			break;
		case GAME_DONE:
			break;
		default:
			break;
		}
		
		result.setCurrentState(currentState);
		//Advance to next state
		currentState = result.getNextState();

		return result;
	}		
	
}
