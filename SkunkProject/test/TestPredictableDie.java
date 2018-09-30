import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPredictableDie {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_predict1() {
		Die d=new Die(new int[] {1,2,3});
		d.roll();
		assertEquals(d.getLastRoll(),1);
	}

}
