package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;

public class TuneEncoding implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private Document document;
	private EncodingStrategy encodeStrategy;

	public TuneEncoding(manager man,ReplayManager rep) {
		
		this.manage = man;
		this.replay = rep;
	}
	
	public TuneEncoding(manager man,Document doc,EncodingStrategy encode) {
		this.manage = man;
		this.document = doc;
		this.encodeStrategy = encode;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			String choice = e.getActionCommand(); //here we get the button that pressed(atbash or rot13) which is the encoding strategy choice
			manage.manageTuneEncoding();
			document = manage.getDocument();
			StrategiesFactory strategy = new StrategiesFactory();
			encodeStrategy = strategy.createStrategy(choice);
			document.tuneEncodingStrategy(encodeStrategy);
			manage.manageEncodeStrategy(encodeStrategy);
			if(!(e==null)) {
				TuneEncoding copy = new TuneEncoding(manage,document,encodeStrategy);
				replay.action(copy);
			}
		}
		
		
	}
	
	public void replayAction() {
		manage.manageTuneEncoding();
		document.tuneEncodingStrategy(encodeStrategy);
		manage.manageEncodeStrategy(encodeStrategy);
	}

}
