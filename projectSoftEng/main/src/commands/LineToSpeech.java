package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class LineToSpeech implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	private int line;

	public LineToSpeech(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	
	public LineToSpeech(manager man,Document doc,int l) {
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
			document.playLine(line);
			if(!(e==null)) {
				LineToSpeech copy = new LineToSpeech(manage,document,line);
				replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageTransformMenu();
		document.playLine(line);
	}

}
