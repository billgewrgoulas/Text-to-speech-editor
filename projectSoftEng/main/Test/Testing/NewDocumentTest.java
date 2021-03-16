package Testing;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import commands.CommandsFactory;
import commands.NewDocument;
import commands.ReplayManager;
import commands.manager;
import model.Line;
import view.Text2SpeechEditorView;

public class NewDocumentTest {

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
		System.out.println(test.getTextWords().getText());
		assertEquals(true,!(test.getTextWords().getText().equals("")));
		NewDocument creation = new NewDocument(test.getCurrentManager(),"title","author","10/10/10",test.getCurrentManager().getDocument());
		creation.actionPerformed(null);
		assertEquals(true,test.getTextWords().getText().equals(""));
		
	}
}
