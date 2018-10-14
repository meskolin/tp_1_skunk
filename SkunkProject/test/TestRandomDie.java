import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRandomDie {
	private Die die;
	@Before
	public void setUp() throws Exception {
		die=new Die();
	}

	@Test
	public void test_range_of_1000_rolls() {
		
		for (int i=0; i<1000; i++){
			die.roll();
			assertTrue("die is outside [1...6]", 1<=die.getLastRoll()&&die.getLastRoll()<=6);
		}
	}
	@Test
	public void test_initial_value_before_roll() {
		assertTrue("initial die is outside [1...6]", 1<=die.getLastRoll()&&die.getLastRoll()<=6);
	}

}
