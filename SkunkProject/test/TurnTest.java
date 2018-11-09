import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class TurnTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void turn_score_0_in_new_Turn()
	{
		Turn new_turn = new Turn();
		assertEquals("init_turn_score_not_0", 0, new_turn.getTurnScore());

	}
	@Test
	public void last_roll_created_in_new_Turn() {
		Turn new_turn= new Turn();
		assertEquals("init_turn_not_null",0, new_turn.getTurnScore());
	}
	@Test
	public void get_last_roll() {
		Turn new_turn=new Turn();
		assertEquals("last_roll",null, new_turn.getLastRoll());
	}
	@Test
	public void roll_again() {
		Turn new_turn=new Turn();
		new_turn.rollAgain();
		assertEquals("roll_added",1, new_turn.rollSequence.size()); 
	}
	
	@Test
	public void get_new_last_roll() {
		Turn new_turn=new Turn();
		new_turn.rollAgain();
		assertNotEquals("last_roll",null, new_turn.getLastRoll());
	}
	

	@Test
	public void testTurnScoringPoints() {
		Turn turn = new Turn();		
		Die die1 = new Die(new int[] {2});
		Die die2 = new Die(new int[] {3});
		Roll roll = new Roll();
		roll.setDice(die1, die2);		
		turn.setLastRoll(roll);	
		turn.scoreTurn();
		assertEquals(5, turn.getTurnScore());
		assertEquals(false, turn.ends());
	}
	
	@Test
	public void double_skunk_scores_zero() {
		Turn turn = new Turn();		
		Roll roll = mock(Roll.class);
		when(roll.isDoubleSkunk()).thenReturn(true);		
		turn.setLastRoll(roll);	
		turn.scoreTurn();
		assertEquals(true, turn.getLastRoll().isDoubleSkunk());
		assertEquals(0, turn.getTurnScore());
		assertEquals(true, turn.ends());
	}
	
	@Test
	public void single_skunk_scores_zero() {
		Turn turn = new Turn();		
		Roll roll = mock(Roll.class);
		when(roll.isSingleSkunk()).thenReturn(true);		
		turn.setLastRoll(roll);	
		turn.scoreTurn();
		assertEquals(true, turn.getLastRoll().isSingleSkunk());
		assertEquals(0, turn.getTurnScore());
		assertEquals(true, turn.ends());
	}
	
	@Test
	public void deuce_skunk_scores_zero() {
		Turn turn = new Turn();		
		Roll roll = mock(Roll.class);
		when(roll.isDeuceSkunk()).thenReturn(true);		
		turn.setLastRoll(roll);	
		turn.scoreTurn();
		assertEquals(true, turn.getLastRoll().isDeuceSkunk());
		assertEquals(0, turn.getTurnScore());
		assertEquals(true, turn.ends());
	}				

}
