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
	public void testControllerHandlesInvalidResponse() {
		control.currentState = State.INVALID_RESPONSE; 
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.WAITING_FOR_INPUT, result.getNextState());
	}
	
	@Test
	public void testControllerGetsInputPlaysTurn() {
		control.currentState = State.WAITING_FOR_INPUT; 
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.TURN_DONE);
		when(round.playTurnStep("y")).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep("y");
		assertEquals(State.TURN_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerHandlesWaitForInputNull() {
		control.currentState = State.WAITING_FOR_INPUT; 
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.WAITING_FOR_INPUT, result.getNextState());
	}
	
	@Test
	public void testControllerTurnDoneToRoundDone() {
		control.currentState = State.TURN_DONE; 
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.ROUND_DONE);
		when(round.determineNextState()).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.ROUND_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerRoundDoneToGameDone() {
		control.currentState = State.ROUND_DONE; 
		players.get(0).setChipCount(100);
		
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.GAME_DONE, result.getNextState());
	}
	
	@Test
	public void testControllerStartNewRound() {
		control.currentState = State.ROUND_DONE; 		
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.PLAYING_TURN, result.getNextState());
	}
	
	@Test
	public void testControllerLastChanceToRoundDone() {
		control.currentState = State.LAST_CHANCE; 		
		Round round = mock(Round.class);
		ResultSummary mockResult = new ResultSummary();
		mockResult.setNextState(State.ROUND_DONE);
		when(round.playTurnStep(null)).thenReturn(mockResult);
		control.round = round;
		
		ResultSummary result = control.doNextStep(null);
		assertEquals(State.ROUND_DONE, result.getNextState());
	}
}
