
public class SkunkApp {

	static boolean choice = false;

	public static void main(String[] args) {
		SkunkUI ui = new SkunkUI();
		GameParams params = ui.getGameParams();
		Controller control = new Controller(params);
		ResultSummary result;

		ui.showStartGameMessage(params);
		while (control.currentState != State.GAME_DONE) {
			// Do one tick and show the result
			if (control.currentState == State.PLAYING_TURN || control.currentState == State.LAST_CHANCE) {
				choice = ui.getTurnInput();
			}
			result = control.doNextStep(choice);
			ui.showResult(result);				
		}
	}
}
