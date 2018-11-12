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
	
	public ResultSummary doNextStep(boolean keepRolling) {
		ResultSummary result = new ResultSummary();	
		
		switch (currentState) {
		case PLAYING_TURN:
			result = round.playTurnStep(keepRolling);
			break;
		case TURN_DONE:
			round.updateActivePlayer();
			result = round.determineNextState();
			break;
		case LAST_CHANCE:
			result = round.playTurnStep(keepRolling);
			break;
		case ROUND_DONE:
			if(!game.gameDone()) {
				round = new Round(players);		
				result.setActivePlayerName(players.get(0).getName());
				result.setNextState(State.PLAYING_TURN);
			} else {
				result.setGameWinnerName(game.getWinner());
				result.setNextState(State.GAME_DONE);
			}
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
