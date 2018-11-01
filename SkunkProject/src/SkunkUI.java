import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkUI {

	public String getInput() {
		return StdIn.readLine();
	}

	public String getTurnInput() {
		showOutput("Do you want to roll? (y/n): ");
		return StdIn.readLine();
	}

	public void showOutput(String message) {
		StdOut.println(message);
	}

	public void showResult(ResultSummary result) {
		showOutput("*************************");
		// Show turn related stuff
		showOutput("Active Player:" + result.playerName);
		if (result.getLastRoll() != null) {
			showOutput("You rolled: " + result.getLastRoll().getDice().getDie1().getLastRoll() + " and "
					+ result.getLastRoll().getDice().getDie2().getLastRoll());
			if(result.getLastRoll().isSingleSkunk()) {
				showOutput("You rolled a single skunk! You lose your turn score :( ");
			} else if (result.getLastRoll().isDeuceSkunk()) {
				showOutput("You rolled a deuce skunk!  You lose your turn score :( ");
			} else if( result.getLastRoll().isDoubleSkunk()) {
				showOutput("You rolled a double skunk!  You lose your turn and round score :( ");
			}
			
		}
		showOutput("Turn Score:" + result.turnScore);
		showOutput("Round Score:" + result.roundScore);
		//Debug
		//showOutput("Next state:" + result.nextState);

		if (result.nextState == State.TURN_DONE) {
			showOutput("Turn over for player " + result.playerName);
		}
		
		if(result.nextState == State.LAST_CHANCE) {
			showOutput("Now its the last chance to win!");
		}
		
		if(result.nextState == State.DONE) {
			showOutput("Player " + result.getWinnerName() + " won the round with a score of " + result.getWinningScore());
			showOutput(result.getWinnerName() + " has " + result.getWinningChipCount() + " chips");
		}
		//Todo show kitty chips and round chips
	}

	public GameParams getGameParams() {
		showOutput("Hello! Welcome to 635 Skunk project!");
		showOutput("Please enter the number of players(>1): ");
		int numPlayer = Integer.parseInt(StdIn.readLine());
		ArrayList<Player> playerList = new ArrayList<>(numPlayer);

		for (int i = 0; i < numPlayer; i++) {
			showOutput("Please enter Player " + (i + 1) + "'s name:");
			String playerName = StdIn.readLine();
			playerList.add(new Player(playerName));
		}

		GameParams params = new GameParams(playerList);
		return params;
	}

}
