package model;

import java.util.ArrayList;
import java.util.Collections;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;
import view.Text2SpeechEditorView;

public class Document {
	
	private Line[] contents;
	private TextToSpeechAPI audioManager;
	private EncodingStrategy encodeStrategy;
	
	private String title;
	private String author;
	private String dateCreated;
	private String lastDateSaved;
	
	private String Text;
	
	public Document() {
		
		
	}
	
	public void setTitleAuthorDate(String title,String author,String dateCreated) {
		
		this.title = title;
		this.author = author;
		this.dateCreated = dateCreated;
		
	}
	
	public void setLastDateSaved(String lastDateSaved) {
		
		this.lastDateSaved = lastDateSaved;
		
	}
	
	public void setLines(String txt) {
		
		String[] lines = txt.split("\\r?\\n"); //separate the lines of the text with \n character
		contents = new Line[lines.length];
		Text2SpeechEditorView x = Text2SpeechEditorView.GetSingletonView();
		this.audioManager = x.getCurrentTextToSpeechAPI();
		this.encodeStrategy = x.getCurrentEncodeStrategy();
		for(int i = 0;i<lines.length;i++) { 
			contents[i] = new Line(lines[i]);
			contents[i].setTextToSpeechAPI(audioManager);
			contents[i].tuneEncodingStrategy(encodeStrategy);
		}
		
	}
	
	public void playContents() { //we take the Line objects from class Line and then make them a string
								 //because it seems that is more efficient way for the play(with voice) of all the text
		
		Text = "";
		for(int i = 0;i<contents.length;i++) {
			Text += String.join(" ",contents[i].getWords()) + " ";
		}
		System.out.println(Text);
		audioManager.play(Text);
		
	}
	
	public void playReverseContents() {
		
		Text = "";
		ArrayList<String> string;
		for(int i = contents.length-1;i>=0;i--) {
			string = contents[i].getWords();
			Collections.reverse(string);
			Text += String.join(" ", string) + " ";
			Collections.reverse(string);
		}
		System.out.println(Text);
		audioManager.play(Text);
        	
	}
	
	public void playEncodedContents() {
		
		Text = "";
		for(int i=0;i<contents.length;i++) {
			String txt = String.join(" ", contents[i].getWords());
			String encodedText = encodeStrategy.encode(txt);
			Text += encodedText + " ";
		}
		System.out.println(Text);
		audioManager.play(Text);
		
	}
	
	public void playLine(int x) {
		
		contents[x].playLine();
		
	}
	
	public void playReverseLine(int x) {
		
		contents[x].playReverseLine();
		
	}
	
	public void playEncodedLine(int x) {
		
		contents[x].playEncodedLine();
		
	}
	
	public void tuneEncodingStrategy(EncodingStrategy encode) {
		this.encodeStrategy = encode;
		if(contents!=null) {
			for(int i=0;i<contents.length;i++) {
			
				contents[i].tuneEncodingStrategy(encodeStrategy);			
			}
		}
		
	}
	
	public void tuneAudioManager(TextToSpeechAPI api) {
		this.audioManager = api;
		if(contents!=null) {
			for(int i=0;i<contents.length;i++) {
				contents[i].setTextToSpeechAPI(api);			
			}
		}
		
	}
	
	public String getdateCreated() {
		return dateCreated;
	}
	
	public Line[] getLines() { //for the tests
		return contents;
	}
	
	public Line getLine(int line) { //for the tests
		return contents[line];
	}
	
	public String getText() {
		Text2SpeechEditorView x = Text2SpeechEditorView.GetSingletonView();
		return x.getTextWords().getText();
	}

}
