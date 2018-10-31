import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp {

	public static void main(String[] args)
	{
			Controller control = new Controller();
			while(control.currentState != State.DONE) {
				control.handleEvents();
			}
	
	}
}
