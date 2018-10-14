import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollTest
{
	private Roll roll;

	@Before
	public void setUp() throws Exception
	{
		this.roll = new Roll();

		// individual tests set the predictable dice
	}

	@Test
	public void test_new_roll_has_dice()
	{
		assertNotEquals("missing Dice in new Roll", null, roll.getDice());
	}

	@Test
	public void test_double_skunk_detected()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 1 });

		roll.setDice(die1, die2);

		assertTrue("double skunk not detected", roll.isDoubleSkunk());

	}

	@Test
	public void test_deuce_skunk_detected()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertTrue("deuce skunk not detected (1)", roll.isDeuceSkunk());

		roll.setDice(die2, die1);

		assertTrue("deuce skunk not detected (2)", roll.isDeuceSkunk());

	}

	@Test
	public void test_single_skunk_detected()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 3 });

		roll.setDice(die1, die2);

		assertTrue("single skunk not detected (1)", roll.isSingleSkunk());

		roll.setDice(die2, die1); // swap the dice and try again

		assertTrue("single skunk not detected (2)", roll.isSingleSkunk());

	}

	@Test
	public void test_not_single_skunk_when_deuce_skunk()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertFalse("deuce skunk reported as single skunk (1)", roll.isSingleSkunk());

		roll.setDice(die2, die1);

		assertFalse("deuce skunk reported as single skunk (2)", roll.isSingleSkunk());

	}

	@Test
	public void test_not_single_skunk_when_double_skunk()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 1 });

		roll.setDice(die1, die2);

		assertFalse("double skunk reported as single skunk", roll.isSingleSkunk());
	}

	@Test
	public void test_not_deuce_skunk_when_double_skunk()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 1 });

		roll.setDice(die1, die2);

		assertFalse("double skunk reported as deuce skunk", roll.isDeuceSkunk());
	}

	@Test
	public void test_not_double_skunk_when_deuce_skunk()
	{
		Die die1 = new Die(new int[]
		{ 1 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertFalse("deuce skunk reported as double skunk (1)", roll.isDoubleSkunk());

		roll.setDice(die2, die1);

		assertFalse("deuce skunk reported as double skunk (2)", roll.isDoubleSkunk());
	}

	@Test
	public void test_no_skunk_reported_as_double_skunk()
	{
		Die die1 = new Die(new int[]
		{ 3 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertFalse("no skunk reported as double skunk (1)", roll.isDoubleSkunk());

		roll.setDice(die2, die1);

		assertFalse("no skunk reported as double skunk (2)", roll.isDoubleSkunk());
	}

	@Test
	public void test_no_skunk_reported_as_deuce_skunk()
	{
		Die die1 = new Die(new int[]
		{ 3 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertFalse("no skunk reported as deuce skunk (1)", roll.isDeuceSkunk());

		roll.setDice(die2, die1);

		assertFalse("no skunk reported as deuce skunk (2)", roll.isDeuceSkunk());
	}

	@Test
	public void test_no_skunk_reported_as_single_skunk()
	{
		Die die1 = new Die(new int[]
		{ 3 });
		Die die2 = new Die(new int[]
		{ 2 });

		roll.setDice(die1, die2);

		assertFalse("no skunk reported as single skunk (1)", roll.isSingleSkunk());

		roll.setDice(die2, die1);

		assertFalse("no skunk reported as single skunk (2)", roll.isSingleSkunk());
	}

}
