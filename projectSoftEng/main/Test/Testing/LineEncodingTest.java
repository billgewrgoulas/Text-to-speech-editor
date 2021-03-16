package Testing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Line;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class LineEncodingTest {

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
		test.getButton("lineEncoding").setEnabled(true);
		int line = 0;
		test.setSelectedLine(line);
		test.getButton("lineEncoding").doClick();
		Line contents = test.getCurrentManager().getDocument().getLine(line);
		String Text = "";
		StrategiesFactory strategy = new StrategiesFactory();
		EncodingStrategy EncodeStrategy = strategy.createStrategy("atbash");
		//for(int i = 0;i<contents.length;i++) {
		String txt = String.join(" ",contents.getWords());
		String encodedText = EncodeStrategy.encode(txt);
		Text += encodedText;
		assertEquals(true,Text.equals(speechManager.getplayText()));
	}

}
