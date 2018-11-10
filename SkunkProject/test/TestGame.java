import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestGame {

	private ArrayList<Player> players;

	@Before
	public void setUp() throws Exception
	{		
		players = new ArrayList<>();
		players.add(new Player("Test player 1"));
		players.add(new Player("Test player 2"));
		players.add(new Player("Test player 3"));
	}
	
	@Test
	public void testFindWinner() {
		Game game = new Game(players);
		players.get(2).setChipCount(game.WINNING_Chip);
		String winner = game.getWinner();
		assertEquals("Test player 3", winner);
	}
	
	@Test
	public void testGameDone() {
		Game game = new Game(players);
		players.get(0).setChipCount(game.WINNING_Chip);
		assertEquals(true, game.gameDone());
	}
	
	@Test
	public void testGameNotDone() {
		Game game = new Game(players);
		assertEquals(false, game.gameDone());
	}
}
