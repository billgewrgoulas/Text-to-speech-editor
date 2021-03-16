package model;

import java.util.ArrayList;
import java.util.Collections;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Line {
	
	private TextToSpeechAPI audioManager;
	private ArrayList<String> words;
	private EncodingStrategy encodeStrategy;
	
	public Line(String txt) {
		
		String[] spaceText = txt.split("\\s+"); //separate the words of a line with space character
		words = new ArrayList<String>();
		for(int i = 0;i<spaceText.length;i++) {
			words.add(spaceText[i]);
		}
		
	}
	
	public void playLine() {
		
		String txt = String.join(" ", words);
		System.out.println(txt);
		audioManager.play(txt);
		
	}
	
	public void playReverseLine(){
		
		Collections.reverse(words); //reverse the words of a line so as the last word be first and so on
		
		String txt = String.join(" ", words); //the text that we give for voice play
		
		Collections.reverse(words); //reverse the words of a line so we have the line that we had before
		
		System.out.println(txt);
		audioManager.play(txt);
		
	}
	
	public void playEncodedLine() {
		
		String txt = String.join(" ", words);
		String encodedText = encodeStrategy.encode(txt);
		System.out.println(encodedText);
		audioManager.play(encodedText);
		
	}
	
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		
		this.encodeStrategy = strategy;
	}
	
	public void setTextToSpeechAPI(TextToSpeechAPI api) {
		
		this.audioManager = api;
	}
	
	public ArrayList<String> getWords() {
		return words;
	}

}
