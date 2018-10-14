
public class Roll
{
	private Dice dice;

	public Roll()
	{
		this.dice = new Dice();
		this.dice.roll(); // forgot this in class
	}

	public Dice getDice()
	{
		return this.dice;
	}

	public void setDice(Die die1, Die die2) // convenient for testing with
											// predictable Die
	{
		this.dice.setDie1(die1);
		this.dice.setDie2(die2);
		this.dice.roll(); // must roll after setting to guarantee initial values
	}

	public boolean isSingleSkunk()
	{
		if (isDoubleSkunk() || isDeuceSkunk())
			return false;
		else
			return this.dice.getDie1().getLastRoll() == 1 || this.dice.getDie2().getLastRoll() == 1;
	}

	public boolean isDeuceSkunk()
	{

		return this.dice.getDie1().getLastRoll() == 1 && this.dice.getDie2().getLastRoll() == 2
				|| this.dice.getDie2().getLastRoll() == 1 && this.dice.getDie1().getLastRoll() == 2;
	}

	public boolean isDoubleSkunk()
	{
		return (dice.getDie1().getLastRoll() == 1 && dice.getDie2().getLastRoll() == 1);
	}

}
