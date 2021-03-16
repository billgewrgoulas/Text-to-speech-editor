package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class ReverseSpeech implements ActionListener {
	
	private manager manage;
	private ReplayManager replay;
	private Document document;

	public ReverseSpeech(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	
	public ReverseSpeech(manager man,Document doc) {
		this.manage = man;
		this.document = doc;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageTransformMenu();
			document = manage.getDocument();
			document.playReverseContents();
			if(!(e==null)) {
				ReverseSpeech copy = new ReverseSpeech(manage,document);
				replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageTransformMenu();
		document.playReverseContents();
	}

}
