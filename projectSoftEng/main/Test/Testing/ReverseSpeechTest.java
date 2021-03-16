package Testing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import model.Line;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class ReverseSpeechTest {

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
		String text = "New file for speech" + "\n";
		String text1 = "Document speech";
		String textSpeech = text+text1;
		test.getCurrentManager().getDocument().setLines(textSpeech);
		test.getButton("reversespeech").setEnabled(true);
		test.getButton("reversespeech").doClick();
		Line[] contents = test.getCurrentManager().getDocument().getLines();
		String Text = "";
		ArrayList<String> string;
		for(int i = contents.length-1;i>=0;i--) {
			string = contents[i].getWords();
			Collections.reverse(string);
			Text += String.join(" ", string) + " ";
		}
		assertEquals(true,Text.equals(speechManager.getplayText()));
	}

}
