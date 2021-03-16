package Testing;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import model.Document;
import model.Line;
import view.Text2SpeechEditorView;

public class SaveDocumentTest {

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
		test.getTextWords().setText("savedFile");
		test.getFrame().setTitle("Title: title - Author: author");
		test.getLabel().setText("Creation Day: 10/10/10 - Last Day Saved: 10/10/10");
		test.getButton("save").doClick();
		Line[] contents = test.getCurrentManager().getDocument().getLines();
		
		//System.out.println(contents.length);
		String Text = "";
		for(int i = 0;i<contents.length;i++) {
			Text += String.join(" ",contents[i].getWords());
		}
		assertEquals(true,Text.equals("savedFile"));
		
	}
}
