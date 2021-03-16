package Testing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Line;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class SpeechTest {

	@Test
	public void test() {
		Constructor<Text2SpeechEditorView> constructor = null;
		try {
			constructor = Text2SpeechEditorView.class.getDeclaredConstructor();
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        constructor.setAccessible(true);
        Text2SpeechEditorView test = null;
		try {
			test = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Text2SpeechEditorView.SetText2SpeechEditorView(test);
		TextToSpeechAPIFactory speechFactory = new TextToSpeechAPIFactory();
		TextToSpeechAPI speechManager = speechFactory.createTTSAPI("fake");
		test.setCurrentTextToSpeechAPI(speechManager);
		
		//Speech
		String text = "New file for speech" + "\n";
		String text1 = "Document speech";
		String textSpeech = text+text1;
		test.getCurrentManager().getDocument().setLines(textSpeech);
		test.getButton("speech").setEnabled(true);
		test.getButton("speech").doClick();
		Line[] contents = test.getCurrentManager().getDocument().getLines();
		String speechText = "";
		for(int i = 0;i<contents.length;i++) {
			speechText += String.join(" ",contents[i].getWords()) + " ";
		}
		assertEquals(true,speechText.equals(speechManager.getplayText()));
		
		//Line Speech
		test.getButton("line").setEnabled(true);
		int line = 1;
		test.setSelectedLine(line);
		test.getButton("line").doClick();
		Line contents1 = test.getCurrentManager().getDocument().getLine(line);
		String lineSpeechText = "";
		lineSpeechText += String.join(" ",contents1.getWords());
		assertEquals(true,lineSpeechText.equals(speechManager.getplayText()));
		
		//Reverse Speech
		test.getButton("reversespeech").setEnabled(true);
		test.getButton("reversespeech").doClick();
		Line[] contents2 = test.getCurrentManager().getDocument().getLines();
		String reverseSpeechText = "";
		ArrayList<String> string;
		for(int i = contents2.length-1;i>=0;i--) {
			string = contents2[i].getWords();
			Collections.reverse(string);
			reverseSpeechText += String.join(" ", string) + " ";
		}
		assertEquals(true,reverseSpeechText.equals(speechManager.getplayText()));
		
		//Reverse Line Speech
		int line1 = 1;
		test.setSelectedLine(line1);
		test.getButton("reverseLine").setEnabled(true);
		test.getButton("reverseLine").doClick();
		Line contents3 = test.getCurrentManager().getDocument().getLine(line1);
		String reverseLineSpeechText = "";
		ArrayList<String> string1 = contents3.getWords();
		Collections.reverse(string1);
		reverseLineSpeechText += String.join(" ",string1);
		assertEquals(true,reverseLineSpeechText.equals(speechManager.getplayText()));
		
		//Document Encoding
		test.getCurrentManager().getDocument().setLines(textSpeech);
		test.getButton("documentEncode").setEnabled(true);
		test.getButton("documentEncode").doClick();
		Line[] contents4 = test.getCurrentManager().getDocument().getLines();
		String documentEncodingText = "";
		StrategiesFactory strategy = new StrategiesFactory();
		EncodingStrategy EncodeStrategy = strategy.createStrategy("atbash");
		for(int i = 0;i<contents4.length;i++) {
			String txt = String.join(" ",contents4[i].getWords());
			String encodedText = EncodeStrategy.encode(txt);
			documentEncodingText += encodedText + " ";
		}
		assertEquals(true,documentEncodingText.equals(speechManager.getplayText()));
		
		//Line Encoding
		int line2 = 0;
		test.setSelectedLine(line2);
		test.getButton("lineEncoding").setEnabled(true);
		test.getButton("lineEncoding").doClick();
		Line contents5 = test.getCurrentManager().getDocument().getLine(line2);
		String lineEncodingText = "";
		StrategiesFactory strategy1 = new StrategiesFactory();
		EncodingStrategy EncodeStrategy1 = strategy1.createStrategy("atbash");
		String txt = String.join(" ",contents5.getWords());
		String encodedText = EncodeStrategy1.encode(txt);
		lineEncodingText += encodedText;
		assertEquals(true,lineEncodingText.equals(speechManager.getplayText()));
	}

}
