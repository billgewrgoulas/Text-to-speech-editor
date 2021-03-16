package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class EditDocument implements ActionListener{
	

	private manager manage;
	private ReplayManager replay;
	private Document document;
	private String currentText;

	public EditDocument(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	
	public EditDocument(manager man, Document doc, String current) {
		this.manage = man;
		this.document = doc;
		this.currentText = current;		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageEditMenu();
			document = manage.getDocument();
			currentText = document.getText();
			document.setLines(currentText);
			if(!(e==null)) {
				EditDocument copy = new EditDocument(manage,document,currentText);
				replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageEditMenu();
		document = manage.getDocument();
		document.setLines(currentText);
		manage.manageReplayEdit(currentText);
	}

}
