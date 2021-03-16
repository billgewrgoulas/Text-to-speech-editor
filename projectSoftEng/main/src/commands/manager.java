package commands;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import encodingstrategies.EncodingStrategy;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import view.Text2SpeechEditorView;

public class manager {

	private Document document;
	private Text2SpeechEditorView edit;
	
	public manager(Text2SpeechEditorView editor,Document doc) {
		this.edit = editor;
		this.document = doc;
	}
	
	public Document getDocument(){
		return document;
	}
	
	public void setDocument(Document doc) {
		this.document = doc;
	}
	
	public void manageEditMenu() {
		edit.getTextWords().setEditable(true);
		edit.setMenu();
		edit.setButtons();
	}
	
	public void manageReplayEdit(String text) {
		edit.getTextWords().setText(text);
	}
	
	public void manageNewDocument(String title,String creationDay) {
	     edit.getTextWords().setEditable(true);
	     edit.getTextWords().setText("");
	     edit.getButton("edit").setEnabled(true);
		 edit.getButton("save").setEnabled(true);
		 edit.getFrame().setTitle(title);
		 edit.getLabel().setText(creationDay);
	}
	
	public void manageOpenDocument(String title,String dateCreate,String dateLastSave,String text) {
		 edit.getFrame().setTitle(title);
         String newTextJLabel = "Creation day: " + dateCreate + " - " + "Last Day Saved: " + dateLastSave;
         edit.getLabel().setText(newTextJLabel);
         edit.getTextWords().setText(text);
         edit.getTextWords().setEditable(true);
         edit.setSaveEditButtons();
         edit.setButtons();
	}
	
	public void manageEncodeMenu() {
		edit.setEncodeMenu();
	}
	
	public void manageTransformMenu() {
		edit.setTransformMenu();
	}
	
	public void manageReplayMenu() {
		edit.setMenu();
	}
	
	public int manageLine() {
		return edit.getSelectedLine();
	}
	
	public int managePitchValue() {
		return edit.getPitchSlider().getValue();
	}
	
	public int manageRateSlider() {
		return edit.getRateSlider().getValue();
	}
	public int manageVoiceSlider() {
		return edit.getSlider().getValue();
	}
	
	public TextToSpeechAPI manageTextToSpeechAPI() {
		return edit.getCurrentTextToSpeechAPI();
	}
	
	public void setButtons() {
		edit.getButton("save").setEnabled(true);
		edit.getButton("edit").setEnabled(true);
		edit.getButton("documentEncode").setEnabled(false);
		edit.getButton("ReverseSpeech").setEnabled(false);
		edit.getButton("Speech").setEnabled(false);
		edit.getButton("lineEncoding").setEnabled(false);
		edit.getButton("ReverseLine").setEnabled(false);
		edit.getButton("line").setEnabled(false);
	}
	
	public void manageLineButtons(boolean bool,int line) {
		edit.setLineButtons(bool);
		edit.setSelectedLine(line);
	}
	
	public void manageTuneEncoding() {
		edit.setMenuTune();
	}
	
	public void manageEncodeStrategy(EncodingStrategy strategy) {
		edit.setEncodingStrategy(strategy);
	}
	
	public EncodingStrategy getCurrentEncodingStrategy() {
		return edit.getCurrentEncodeStrategy();
	}
	
	public String manageFrameText() {
		return edit.getFrame().getTitle(); 
	}
	
	public String manageLabelText() {
		return edit.getLabel().getText();
	}
	
	public JLabel manageJLabel() {
		return edit.getLabel();
	}
	
	public JTextArea manageTextArea() {
		return edit.getTextWords();
	}
	
	public void manageButtonSave() {
		edit.getButton("save").setEnabled(false);
	}
	
	public void cancelMenu(String evt) {
		JOptionPane.showMessageDialog(edit.getFrame(), evt);
	}
	
}
