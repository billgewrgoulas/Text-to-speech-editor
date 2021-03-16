package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayCommand implements ActionListener{
	
	private ReplayManager replayManager;
	private manager manage;
	
	public ReplayCommand(manager man,ReplayManager replay) {
		
		this.replayManager = replay;
		this.manage = man;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		replayManager.replay();
		manage.manageReplayMenu();
		
	}

}
