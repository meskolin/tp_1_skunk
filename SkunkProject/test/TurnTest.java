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
		assertEquals("init_turn_not_null",null, new_turn.getLastRoll());
	}

//	@Test
//	public void test3()
//	{
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void test4()
//	{
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void test5()
//	{
//		fail("Not yet implemented");
//	}

}
