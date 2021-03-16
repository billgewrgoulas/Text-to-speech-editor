package Testing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import view.Text2SpeechEditorView;

public class TuneEncodingTest {

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
		test.getButton("atbash").doClick();
		EncodingStrategy strategy = test.getCurrentManager().getCurrentEncodingStrategy();
		StrategiesFactory strategy1 = new StrategiesFactory();
		EncodingStrategy EncodeStrategy = strategy1.createStrategy("atbash");
		assertEquals(true,(strategy.getClass()).equals(EncodeStrategy.getClass()));
		test.getButton("rot13").doClick();
		EncodeStrategy = strategy1.createStrategy("rot13");
		EncodingStrategy strategy2 = test.getCurrentManager().getCurrentEncodingStrategy();
		assertEquals(true,(strategy2.getClass()).equals(EncodeStrategy.getClass()));
		assertEquals(true,!(strategy.getClass().equals(strategy2.getClass())));
		
	}

}
