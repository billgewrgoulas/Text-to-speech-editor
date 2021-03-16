package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.EncodingStrategy;
import model.Document;

public class DocumentEncoding implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	private EncodingStrategy strategy;

	public DocumentEncoding(manager man,ReplayManager rep){
		this.manage = man;
		this.replay = rep;
	}
	
	public DocumentEncoding(manager man,Document doc,EncodingStrategy st) {
		this.manage = man;
		this.document = doc;
		this.strategy = st;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageEncodeMenu();
			document = manage.getDocument();
			document.playEncodedContents();
			if(!(e==null)) {
				DocumentEncoding copy = new DocumentEncoding(manage,document,manage.getCurrentEncodingStrategy());
				replay.action(copy);
			}
		}
		
	}
	
	public void replayAction() {
		manage.manageEncodeMenu();
		document.tuneEncodingStrategy(strategy);
		document.playEncodedContents();
	}

}
