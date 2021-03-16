package commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager{
	
	ArrayList<ActionListener> actions = new ArrayList<>();
	
	public ReplayManager() {
		
		
	}
	
	public void replay() {
		
		for(int i = 0; i < actions.size(); i++) {
			 actions.get(i).actionPerformed(null); //we call actionPerformed with null because now we want to execute the replayAction of the button
		}
		         	
	}
	
	public void action(ActionListener act) {
		actions.add(act);
	}
	

}
