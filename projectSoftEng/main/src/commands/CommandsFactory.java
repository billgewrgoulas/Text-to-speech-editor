package commands;

import java.awt.event.ActionListener;

import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;


public class CommandsFactory {
	
	private SaveDocument saveDocument;
	private ReplayCommand replayCommand;
	private NewDocument newDocument;
	private OpenDocument openDocument;
	private EditDocument editDocument;
	private LineToSpeech lineToSpeech;
	private DocumentToSpeech documentToSpeech;
	private ReverseSpeech reverseSpeech;
	private ReverseLineSpeech reverseLineSpeech;
	private DocumentEncoding documentEncoding;
	private LineEncoding lineEncoding;
	private TuneEncoding tuneEncoding;
	
	private TuneVoice voiceListener;
	private TunePitch pitchListener;
	private TuneRate rateListener;
	
	private TextListener textListener;
	
	private DocumentChange documentChange;
	
	private manager manage;
	private ReplayManager replay;
	
	public CommandsFactory(manager man,ReplayManager rep) {
		
		this.manage = man;
		this.replay = rep;
		
	}
	
	public ActionListener createCommand(String x) {
		if(x.equalsIgnoreCase("save")) {
			saveDocument = new SaveDocument(manage,replay);
			return saveDocument;
		}else if(x.equalsIgnoreCase("open")) {
			openDocument = new OpenDocument(manage,replay);
			return openDocument;
		}else if(x.equalsIgnoreCase("edit")) {
			editDocument = new EditDocument(manage,replay);
			return editDocument;
		}else if(x.equalsIgnoreCase("create")) {
			newDocument = new NewDocument(manage,replay);
			return newDocument;
		}else if(x.equalsIgnoreCase("lineToSpeech")) {
			lineToSpeech = new LineToSpeech(manage,replay);
			return lineToSpeech;
		}else if(x.equalsIgnoreCase("speech")) {
			documentToSpeech = new DocumentToSpeech(manage,replay);
			return documentToSpeech;
		}else if(x.equalsIgnoreCase("reverseSpeech")) {
			reverseSpeech = new ReverseSpeech(manage,replay);
			return reverseSpeech;
		}else if(x.equalsIgnoreCase("reverseLineSpeech")) {
			reverseLineSpeech = new ReverseLineSpeech(manage,replay);
			return reverseLineSpeech;
		}else if(x.equalsIgnoreCase("lineEncoding")) {
			lineEncoding = new LineEncoding(manage,replay);
			return lineEncoding;
		}else if(x.equalsIgnoreCase("documentEncoding")) {
			documentEncoding = new DocumentEncoding(manage,replay);
			return documentEncoding;
		}else if(x.equalsIgnoreCase("tuneEncoding")) {
			tuneEncoding = new TuneEncoding(manage,replay);
			return tuneEncoding;
		}else if(x.equalsIgnoreCase("replay")) {
			replayCommand = new ReplayCommand(manage,replay);
			return replayCommand;
		}
		return null;
		
	}
	
	public ChangeListener create(String x) {
		
		if(x.equalsIgnoreCase("volume")) {
			voiceListener = new TuneVoice(manage);
			return voiceListener;
		}else if(x.equalsIgnoreCase("pitch")) {
			pitchListener = new TunePitch(manage);
			return pitchListener;
		}else if(x.equalsIgnoreCase("rate")) {
			rateListener = new TuneRate(manage);
			return rateListener;
		}
		return null;
	}
	
	public CaretListener createTextListener(String x) {
		
		if(x.equalsIgnoreCase("text")) {
			textListener = new TextListener(manage);
			return textListener;
		}
		return null;
	}
	
	public DocumentListener TextDocumentChange(String x) {
		
		if(x.equalsIgnoreCase("text")) {
			documentChange = new DocumentChange(manage);
			return documentChange;
		}
		return null;
		
	}

}
