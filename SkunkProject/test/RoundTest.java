import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RoundTest
{
	private Round round;
	private ArrayList<Player> players;
	private Die skunk = new Die(new int[] {1});

	@Before
	public void setUp() throws Exception
	{		
		players = new ArrayList<>();
		players.add(new Player("Test player 1"));
		players.add(new Player("Test player 2"));
		players.add(new Player("Test player 3"));
		this.round = new Round(players);
	}

	@Test
	public void testPlayTurnStepCurrentTurnNull()
	{
		assertEquals(round.currentTurn, null);
		ResultSummary result = this.round.playTurnStep(true);
		assertNotNull(round.currentTurn);
	}
	
	@Test
	public void testPlayTurnStepKeepPlaying()
	{
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		assertNotNull(round.currentTurn);
		when(turn.ends()).thenReturn(false);
		when(turn.getTurnScore()).thenReturn(4);
		ResultSummary result = this.round.playTurnStep(true);		
		assertEquals(State.PLAYING_TURN, result.getNextState());
		assertEquals(4, result.getTurnScore());
	}
	
	@Test
	public void testPlayTurnStepDoubleSkunk()
	{
		Roll roll = new Roll();
		roll.setDice(skunk, skunk);
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		assertNotNull(round.currentTurn);
		when(turn.ends()).thenReturn(true);
		when(turn.getLastRoll()).thenReturn(roll);
		ResultSummary result = this.round.playTurnStep(true);
		assertEquals(State.TURN_DONE, result.getNextState());
		assertEquals(players.get(round.activePlayerIndex).getRoundScore(), 0);
	}
	
	@Test
	public void testPlayTurnStepTurnEnds()
	{
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		assertNotNull(round.currentTurn);		
		when(turn.ends()).thenReturn(true);
		ResultSummary result = this.round.playTurnStep(true);		
		assertEquals(State.TURN_DONE, result.getNextState());
	}
	

	@Test
	public void testPlayTurnStepDecline()
	{
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		ResultSummary result = this.round.playTurnStep(false);		
		assertEquals(State.TURN_DONE, result.getNextState());
	}
		
	@Test
	public void testPlayLastChance()
	{
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		round.activePlayerIndex = 1;
		round.firstWinnerIndex = 0;
		round.players.get(0).setRoundScore(round.WINNING_SCORE);
		when(turn.ends()).thenReturn(true);	
		round.lastChance = true;
		ResultSummary result = this.round.playTurnStep(true);		
		assertEquals(State.TURN_DONE, result.getNextState());
	}
	
	@Test
	public void testPlayLastChanceDone()
	{
		Turn turn = mock(Turn.class);	
		round.currentTurn = turn;
		round.activePlayerIndex = 0;
		round.firstWinnerIndex = round.activePlayerIndex;
		round.players.get(0).setRoundScore(round.WINNING_SCORE);
		when(turn.ends()).thenReturn(true);	
		round.lastChance = true;
		ResultSummary result = this.round.determineNextState();
		assertEquals(State.ROUND_DONE, result.getNextState());
	}
	
	@Test
	public void testFindWinner()
	{
		round.players.get(1).setRoundScore(round.WINNING_SCORE);
		Player winner = round.findWinner();
		assertEquals(winner, players.get(1));
		assertEquals(round.firstWinnerIndex, 1);
	}
	
	@Test
	public void testMoveChips()
	{
		//Test winner gets 5 chips from players with non-zero score
		round.players.get(0).setRoundScore(round.WINNING_SCORE - 1);
		round.players.get(2).setRoundScore(round.WINNING_SCORE - 1);
		round.players.get(1).setRoundScore(round.WINNING_SCORE);
		Player winner = round.findWinner();
		round.moveChips(winner);
		assertEquals(60, winner.getChipCount());
		assertEquals(45, round.players.get(0).getChipCount());
		assertEquals(45, round.players.get(2).getChipCount());
	}
	
	@Test
	public void testMoveChipsRoundScore0()
	{
		//Test winner gets 10 chips from players with losing score
		round.players.get(1).setRoundScore(round.WINNING_SCORE);
		Player winner = round.findWinner();
		round.moveChips(winner);
		assertEquals(winner.getChipCount(), 70);
		assertEquals(round.players.get(0).getChipCount(), 40);
		assertEquals(round.players.get(2).getChipCount(), 40);
	}
	
	@Test
	public void testUpdateActivePlayer()
	{
		assertEquals(round.activePlayerIndex, 0);
		round.updateActivePlayer();
		assertEquals(round.activePlayerIndex, 1);	
	}
	
	@Test
	public void testDetermineStateLastChance()
	{
		round.lastChance = true;
		round.players.get(0).setRoundScore(round.WINNING_SCORE);
		assertEquals(round.activePlayerIndex, 0);
		round.playTurnStep(true);
		round.updateActivePlayer();
		ResultSummary result = round.determineNextState();
		assertEquals(State.LAST_CHANCE, result.getNextState());
	}
	
	@Test
	public void testDetermineStateLastChanceDone()
	{
		round.lastChance = true;
		round.firstWinnerIndex = 0;
		round.players.get(0).setRoundScore(round.WINNING_SCORE + 1);
		round.activePlayerIndex = 2;
		
		round.updateActivePlayer();
		ResultSummary result = round.determineNextState();		
		assertEquals(round.WINNING_SCORE + 1, result.getWinningScore());
		assertEquals("Test player 1", result.getRoundWinnerName());
		assertEquals(70, result.getWinningChipCount());
		assertEquals(State.ROUND_DONE, result.getNextState());
	}


}
