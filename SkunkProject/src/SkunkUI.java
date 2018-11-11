import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkUI {

	public String getTurnInput() {
		showOutput("Do you want to roll? (y/n): ");
		return StdIn.readLine();
	}

	public void showOutput(String message) {
		StdOut.println(message);
	}

	public void showResult(ResultSummary result) {

		switch (result.getNextState()) {
		case INVALID_RESPONSE:
			showOutput("*************************");
			showOutput("You entered an invalid response. \n");
			break;
		case WAITING_FOR_INPUT:
			showOutput("*************************");
			showOutput("It is " + result.getPlayerName() + "'s turn");
			if (result.getLastRoll() != null) {
				showRoll(result.getLastRoll());
			}							
			showOutput("Turn Score:" + result.getTurnScore());
			showOutput("Round Score:" + result.getRoundScore());
			break;
		case TURN_DONE:
			showOutput("*************************");
			if (result.getLastRoll() != null) {
				showRoll(result.getLastRoll());
			}
			showOutput("Turn over for player " + result.getPlayerName());
			showOutput("Turn Score:" + result.getTurnScore());
			showOutput("Round Score:" + result.getRoundScore());
			break;
		case LAST_CHANCE:
			showOutput("*************************");
			StdOut.println("Now its the last chance to win!");
			break;
		case ROUND_DONE:
			showOutput("*************************");
			showOutput(
					"Player " + result.getRoundWinnerName() + " won the round with a score of " + result.getWinningScore());
			showOutput(result.getRoundWinnerName() + " has " + result.getWinningChipCount() + " chips");
			showOutput("Final chip counts: ");
			for (int i = 0; i < result.getPlayers().size(); i++) {
				showOutput(result.getPlayers().get(i).getName() + ": " + result.getPlayers().get(i).getChipCount());
			}
			break;
		case GAME_DONE:
			showOutput("*************************");
			showOutput("Player "+ result.getGameWinnerName() +" won the game. Congratulations!");
		default:
			break;
		}
	}

	private void showRoll(Roll roll) {
		showOutput("You rolled: " + roll.getDice().getDie1().getLastRoll() + " and "
				+ roll.getDice().getDie2().getLastRoll());
		if (roll.isSingleSkunk()) {
			showOutput("You rolled a single skunk! You lose your turn score :( ");
		} else if (roll.isDeuceSkunk()) {
			showOutput("You rolled a deuce skunk!  You lose your turn score :( ");
		} else if (roll.isDoubleSkunk()) {
			showOutput("You rolled a double skunk!  You lose your turn and round score :( ");
		}
	}

	public GameParams getGameParams() {
		showOutput("Hello! Welcome to 635 Skunk project!");
		showOutput("Please enter the number of players(2 or more): ");
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
