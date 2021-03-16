package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.EncodingStrategy;
import model.Document;

public class LineEncoding implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	private int line;
	private EncodingStrategy strategy;

	public LineEncoding(manager man,ReplayManager rep) {
		this.manage = man;
		this.replay = rep;
	}
	public LineEncoding(manager man,Document doc,int l,EncodingStrategy st) {
		this.manage = man;
		this.document = doc;
		this.line = l;
		this.strategy = st;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			manage.manageEncodeMenu();
			document = manage.getDocument();
			line = manage.manageLine();
			document.playEncodedLine(line);
			if(!(e==null)) {
				LineEncoding copy = new LineEncoding(manage,document,line,manage.getCurrentEncodingStrategy());
				replay.action(copy);
			}
		}
			
	}
	
	public void replayAction() {
		manage.manageEncodeMenu();
		document.tuneEncodingStrategy(strategy);
		document.playEncodedLine(line);
	}

}
