import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TurnTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void create_new_Turn()
	{
		Turn new_turn = new Turn();
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
	public void skunk_scores_zero() {
		Turn new_turn = new Turn();
		for (int i=0;i<100;i++) {
		new_turn.rollAgain();
		if (new_turn.getLastRoll().isDoubleSkunk()) {
			new_turn.scoreTurn();
			assertEquals("double_skunk_scores_0", 0, new_turn.getTurnScore());
			assertTrue("is_a_skunk", new_turn.ends());
		}
		else if(new_turn.getLastRoll().isDeuceSkunk()) {
			new_turn.scoreTurn();
			assertEquals("deuce_skunk_scores_0", 0, new_turn.getTurnScore());
			assertTrue("is_a_skunk", new_turn.ends());
		}
		else if(new_turn.getLastRoll().isSingleSkunk()) {
			new_turn.scoreTurn();
			assertEquals("single_skunk_scores_0", 0, new_turn.getTurnScore());
			assertTrue("is_a_skunk", new_turn.ends());
		}
		else {
			new_turn.scoreTurn();
			assertNotEquals("not_a_skunk", 0, new_turn.getTurnScore());
			assertFalse("is_not_skunk", new_turn.ends());
		}
		}
		
	}
	
	

}
