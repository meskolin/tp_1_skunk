import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestController {

	private ArrayList<Player> players;
	Controller control;

	@Before
	public void setUp() throws Exception
	{		
		players = new ArrayList<>();
		players.add(new Player("Test player 1"));
		players.add(new Player("Test player 2"));
		players.add(new Player("Test player 3"));
		control = new Controller(new GameParams(players));
	}
	
	@Test
	public void testControllerStart() {
		assertEquals(State.PLAYING_TURN, control.currentState);
	}
		
	@Test
	public void testControllerGetsInputPlaysTurn() {
		control.currentState = State.PLAYING_TURN; 
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.TURN_DONE);
		when(round.playTurnStep(true)).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep(true);
		assertEquals(State.TURN_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerTurnDoneToRoundDone() {
		control.currentState = State.TURN_DONE; 
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.ROUND_DONE);
		when(round.determineNextState()).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep(false);
		assertEquals(State.ROUND_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerRoundDoneToGameDone() {
		control.currentState = State.ROUND_DONE; 
		players.get(0).setChipCount(100);
		
		ResultSummary result = control.doNextStep(false);
		assertEquals(State.GAME_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerStartNewRound() {
		control.currentState = State.ROUND_DONE; 		
		ResultSummary result = control.doNextStep(true);
		assertEquals(State.PLAYING_TURN, result.getNextState());
	}
	
	@Test
	public void testControllerLastChanceToRoundDone() {
		control.currentState = State.LAST_CHANCE; 		
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.ROUND_DONE);
		when(round.playTurnStep(true)).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep(true);
		assertEquals(State.ROUND_DONE, result.getNextState());
	}
}
