package Testing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class TuneAudioTest {

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
		test.getSlider().setValue(70);
		test.getRateSlider().setValue(70);
		test.getPitchSlider().setValue(70);
		assertEquals(0,Float.compare((float)0.7, speechManager.getVolume())); //if are same then the output is 0
		assertEquals(0,Float.compare((float)70, speechManager.getRate()));
		assertEquals(0,Float.compare((float)70, speechManager.getPitch()));
	}

}
