package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Line;
import view.Text2SpeechEditorView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class EditDocumentTest {
	
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
		test.getTextWords().setText("before");
		test.getButton("edit").doClick();
		Line[] contents = test.getCurrentManager().getDocument().getLines();
		String Text = "";
		for(int i = 0;i<contents.length;i++) {
			Text += String.join(" ",contents[i].getWords());
		}
		test.getTextWords().setText("after");
		test.getButton("edit").doClick();
		Line[] contents1 = test.getCurrentManager().getDocument().getLines();
		String TextNew = "";
		for(int i = 0;i<contents.length;i++) {
			TextNew += String.join(" ",contents1[i].getWords());
		}
		assertEquals(true,!(Text.equals(TextNew)));
		assertEquals(true,TextNew.equals("after"));
	}
}
