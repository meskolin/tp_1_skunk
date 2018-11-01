import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp {

	
	static String input = null;	

	public static void main(String[] args)
	{
			SkunkUI ui = new SkunkUI();	
			GameParams params = ui.getGameParams();
			Controller control = new Controller(params);
			ResultSummary result;				
			
			while(control.currentState != State.DONE) {
				//Do one tick and show the result
				result = control.handleEvent(input);				
				ui.showResult(result);
				input = null;
				if(control.currentState == State.WAITING_FOR_INPUT) {
					input = ui.getTurnInput();
				} else {
					//Reset input
					//input = null;					
				}
			}
	
	}
}
