package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class DocumentToSpeech implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	
	public DocumentToSpeech(manager man,ReplayManager rep) {
		
		this.manage = man;
		this.replay = rep;
		
	}
	
	public DocumentToSpeech(manager man,Document doc) {
		this.manage = man;
		this.document = doc;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageTransformMenu();
			document = manage.getDocument();
			document.playContents();
			if(!(e==null)) {
				DocumentToSpeech copy = new DocumentToSpeech(manage,document);
	        	replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageTransformMenu();
		document.playContents();
	}
}
