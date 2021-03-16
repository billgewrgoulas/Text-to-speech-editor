package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class ReverseLineSpeech implements ActionListener {
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	private int line;

	public ReverseLineSpeech(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	
	public ReverseLineSpeech(manager man,Document doc,int l) {
		this.manage = man;
		this.document = doc;
		this.line = l;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageTransformMenu();
			document = manage.getDocument();
			line = manage.manageLine();
			document.playReverseLine(line);
			if(!(e==null)) {
				ReverseLineSpeech copy = new ReverseLineSpeech(manage,document,line);
				replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageTransformMenu();
		document.playReverseLine(line);
	}

}
