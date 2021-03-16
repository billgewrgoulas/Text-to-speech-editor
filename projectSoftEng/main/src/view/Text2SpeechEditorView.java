package view;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import commands.CommandsFactory;
import commands.ReplayManager;
import commands.manager;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

import javax.swing.JButton;
import javax.swing.JSlider;

public class Text2SpeechEditorView {
	
	private JTextArea text;
	private JFrame frame;
	
	private Document currentDocument;
	private CommandsFactory factory;
	private TextToSpeechAPIFactory speechFactory;
	private static Text2SpeechEditorView view;
	private ReplayManager replayManager;
	private TextToSpeechAPI speechManager;
	private EncodingStrategy EncodeStrategy;
	
	private int selectedLine;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnTransform;	
	
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnEdit;
	private JMenu mnEncode;
	private JMenu mnTune;
	private JMenu mnTuneEncoding;
	private JMenu mnTuneAudio;
	private JMenu mnVolume;
	private JSlider VolumeSlider;
	private JMenu mnPitch;
	private JSlider PitchSlider;
	private JMenu mnRate;
	private JSlider Rateslider;
	private JButton btnLineSpeech;
	private JButton btnLineEncoding;
	private JButton btnReverseLineSpeech;
	private JButton btnDocumentEncoding;
	private JButton btnReverseSpeech;
	private JButton btnSpeech;
	private JLabel lblNewLabel;
	private manager manage;
	private JButton btnCreateEmptyDocument;
	private JButton btnAtBash;
	private JButton btnRot13;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new Text2SpeechEditorView();
					view.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Text2SpeechEditorView() {
		
		initialize();
		
	}
	
	private void initialize() {
		
		frame = new JFrame("Editor");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		replayManager = new ReplayManager(); //replay manager of editor
		speechFactory = new TextToSpeechAPIFactory(); //default FREETTS
		speechManager = speechFactory.createTTSAPI("normal");
		currentDocument = new Document();
		manage = new manager(this,currentDocument); //manager of editor
		factory = new CommandsFactory(manage,replayManager);
		StrategiesFactory strategy = new StrategiesFactory();
		EncodeStrategy = strategy.createStrategy("atbash"); //default encoding strategy
		
		text = new JTextArea();
		text.setBounds(0, 49, 434, 212);
		frame.getContentPane().add(text);
		text.setEditable(false);
		text.addCaretListener(factory.createTextListener("text"));
		text.getDocument().addDocumentListener(factory.TextDocumentChange("text"));
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 30, 424, 22);
		frame.getContentPane().add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
	 
		btnCreateEmptyDocument = new JButton("CreateEmptyDocument"); //us1
		btnCreateEmptyDocument.addActionListener(factory.createCommand("create"));
		mnFile.add(btnCreateEmptyDocument);
		
		btnOpen = new JButton("Open"); //us4
		btnOpen.addActionListener(factory.createCommand("open"));
		mnFile.add(btnOpen);
		
		btnSave = new JButton("Save"); //us3
		btnSave.addActionListener(factory.createCommand("save"));
		mnFile.add(btnSave);
		btnSave.setEnabled(false);
		
		btnEdit = new JButton("Edit"); //us2
		btnEdit.addActionListener(factory.createCommand("Edit"));
		mnFile.add(btnEdit);
		btnEdit.setEnabled(false);
		
		JButton btnReplay = new JButton("Replay"); //us13
		btnReplay.addActionListener(factory.createCommand("replay"));
		mnFile.add(btnReplay);
		
		mnTransform = new JMenu("Transform");
		menuBar.add(mnTransform);
		btnSpeech = new JButton("Speech"); //us5
		btnSpeech.addActionListener(factory.createCommand("speech"));
		mnTransform.add(btnSpeech);
		btnSpeech.setEnabled(false);
		
		btnLineSpeech = new JButton("Line Speech"); //us6
		btnLineSpeech.addActionListener(factory.createCommand("lineTOspeech"));
		mnTransform.add(btnLineSpeech);
		btnLineSpeech.setEnabled(false);
		
		btnReverseSpeech = new JButton("Reverse Speech"); //us7
		btnReverseSpeech.addActionListener(factory.createCommand("ReverseSpeech"));
		mnTransform.add(btnReverseSpeech);
		btnReverseSpeech.setEnabled(false);
		
		btnReverseLineSpeech = new JButton("Reverse Line Speech"); //us8
		btnReverseLineSpeech.addActionListener(factory.createCommand("ReverseLineSpeech"));
		mnTransform.add(btnReverseLineSpeech);
		btnReverseLineSpeech.setEnabled(false);
		
		mnEncode = new JMenu("Encode");
		menuBar.add(mnEncode);
		btnLineEncoding = new JButton("Line Encoding"); //us10
		btnLineEncoding.addActionListener(factory.createCommand("lineEncoding"));
		mnEncode.add(btnLineEncoding);
		btnLineEncoding.setEnabled(false);
		
		btnDocumentEncoding = new JButton("Document Encoding"); //us9
		btnDocumentEncoding.addActionListener(factory.createCommand("documentEncoding"));
		mnEncode.add(btnDocumentEncoding);	
		btnDocumentEncoding.setEnabled(false);
		
		mnTune = new JMenu("Tune");
		menuBar.add(mnTune);
		
		mnTuneEncoding = new JMenu("Tune Encoding");
		mnTune.add(mnTuneEncoding);
		
		ActionListener same = factory.createCommand("tuneEncoding");
		
		btnAtBash = new JButton("AtBash");
		mnTuneEncoding.add(btnAtBash);
		btnAtBash.addActionListener(same);
		
		btnRot13 = new JButton("Rot13");
		mnTuneEncoding.add(btnRot13);
		btnRot13.addActionListener(same);
		
		mnTuneAudio = new JMenu("Tune Audio");
		mnTune.add(mnTuneAudio);
		
		mnVolume = new JMenu("Volume");
		mnTuneAudio.add(mnVolume);
		
		VolumeSlider = new JSlider();
		VolumeSlider.setValue(100);
		VolumeSlider.setPaintTrack(true); 
	    VolumeSlider.setPaintTicks(true); 
	    VolumeSlider.setPaintLabels(true); 
	  
	    // set spacing 
	    VolumeSlider.setMajorTickSpacing(50); 
	    VolumeSlider.setMinorTickSpacing(5);
		VolumeSlider.addChangeListener(factory.create("volume"));
		mnVolume.add(VolumeSlider);
		
		mnPitch = new JMenu("Pitch");
		mnTuneAudio.add(mnPitch);
		
		PitchSlider = new JSlider();
		PitchSlider.setValue(100);
		PitchSlider.setPaintTrack(true); 
	    PitchSlider.setPaintTicks(true); 
	    PitchSlider.setPaintLabels(true); 
	  
	    // set spacing 
	    PitchSlider.setMajorTickSpacing(50); 
	    PitchSlider.setMinorTickSpacing(5);
		PitchSlider.addChangeListener(factory.create("pitch"));
		mnPitch.add(PitchSlider);
		
		mnRate = new JMenu("Rate");
		mnTuneAudio.add(mnRate);
		
		Rateslider = new JSlider();
		Rateslider.setValue(150);
		Rateslider.setMaximum(150);
		Rateslider.setPaintTrack(true); 
	    Rateslider.setPaintTicks(true); 
	    Rateslider.setPaintLabels(true); 
	  
	    // set spacing 
	    Rateslider.setMajorTickSpacing(50); 
	    Rateslider.setMinorTickSpacing(5);
		Rateslider.addChangeListener(factory.create("rate"));
		
		mnRate.add(Rateslider);
		
		lblNewLabel = new JLabel(" ");
		lblNewLabel.setBounds(0, 0, 434, 19);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
	}
	
	
	public static Text2SpeechEditorView GetSingletonView() {
		
		return view;
		
	}
	
	public TextToSpeechAPI getCurrentTextToSpeechAPI() {
		
		return speechManager;
		
	}
	
	public EncodingStrategy getCurrentEncodeStrategy() {
		
		return EncodeStrategy;
		
	}
	
	public void setEncodingStrategy(EncodingStrategy strategy) {
		this.EncodeStrategy = strategy;
	}
	
	public void setCurrentTextToSpeechAPI(TextToSpeechAPI api) { //for the tests
		
		this.speechManager = api;
		
	}
	
	public static void SetText2SpeechEditorView(Text2SpeechEditorView editor) { //for the tests
		view  = editor;
	}
	
	public manager getCurrentManager() { //for the tests
		return manage;
	}
	
	public JFrame getFrame() { //for the set of title and author of our document
		return frame;
	}
	
	public JLabel getLabel() { //for the set of creation day and the last day the we saved the file 
		return lblNewLabel;
	}
	
	public JTextArea getTextWords() { //for the display of the text of our document
		return text;
	}
	
	public JSlider getSlider() {
		return VolumeSlider;
	}
	
	public JSlider getPitchSlider() {
		return PitchSlider;
	}
	
	public JSlider getRateSlider() {
		return Rateslider;
	}
	
	public JButton getButton(String s) { 
		if(s.equalsIgnoreCase("edit")) {
			return btnEdit;
		}else if(s.equalsIgnoreCase("save")) {
			return btnSave;
		}else if(s.equalsIgnoreCase("line")) {
			return btnLineSpeech;
		}else if(s.equalsIgnoreCase("lineEncoding")) {
			return btnLineEncoding;
		}else if(s.equalsIgnoreCase("ReverseLine")) {
			return btnReverseLineSpeech;
		}else if(s.equalsIgnoreCase("DocumentEncode")) {
			return btnDocumentEncoding;
		}else if(s.equalsIgnoreCase("ReverseSpeech")) {
			return btnReverseSpeech;
		}else if(s.equalsIgnoreCase("Speech")) {
			return btnSpeech;
		}else if(s.equalsIgnoreCase("create")) {
			return btnCreateEmptyDocument;
		}else if(s.equalsIgnoreCase("open")) {
			return btnOpen;
		}else if(s.equalsIgnoreCase("save")) {
			return btnSave;
		}else if(s.equalsIgnoreCase("atbash")) {
			return btnAtBash;
		}else if(s.equalsIgnoreCase("rot13")) {
			return btnRot13;
		}
		return null;	
	}
	
	public void setSaveEditButtons() {
		btnSave.setEnabled(false);
		btnEdit.setEnabled(false);
	}
	
	public void setLineButtons(boolean bool) {
		
		if(bool==true) {
			if(btnSpeech.isEnabled()) {
				btnLineEncoding.setEnabled(true);
				btnLineSpeech.setEnabled(true);
				btnReverseLineSpeech.setEnabled(true);
			}
		}else {
			btnLineEncoding.setEnabled(false);
			btnLineSpeech.setEnabled(false);
			btnReverseLineSpeech.setEnabled(false);
		}
	}
	
	public void setButtons() { //we check if in the current text area exists text and then 
								//we check if exists text in the line that user works
		
		if(!text.getText().isBlank()) {
			btnDocumentEncoding.setEnabled(true);
			btnReverseSpeech.setEnabled(true);
			btnSpeech.setEnabled(true);
			int linenum = 1;
		    int start = 0;
		    int end = 0;
			try {
		          
		        int caretpos = text.getCaretPosition();
		        linenum = text.getLineOfOffset(caretpos);
		        start = text.getLineStartOffset(linenum);
		        end = text.getLineEndOffset(linenum);

		      
			}
		    catch(Exception ex) { }
		        
		    try {
				if(text.getText(start,end-start).isBlank()) {
					btnLineEncoding.setEnabled(false);
					btnLineSpeech.setEnabled(false);
					btnReverseLineSpeech.setEnabled(false);
				}else {
					btnLineEncoding.setEnabled(true);
					btnLineSpeech.setEnabled(true);
					btnReverseLineSpeech.setEnabled(true);
				}
		    } catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void setMenu() {
		mnFile.setSelected(false);
		mnFile.setPopupMenuVisible(false);
	}
	
	public void setTransformMenu() {
		
		mnTransform.setSelected(false);
		mnTransform.setPopupMenuVisible(false);
		
	}
	
	public void setEncodeMenu() {
		
		mnEncode.setSelected(false);
		mnEncode.setPopupMenuVisible(false);
		
	}
	
	public void setMenuTune() {
		
		mnTuneEncoding.setSelected(false);
		mnTuneEncoding.setPopupMenuVisible(false);
		mnTune.setSelected(false);
		mnTune.setPopupMenuVisible(false);
		
	}
	
	
	public int getSelectedLine() { //line that user works
		
		return selectedLine;
		
	}
	
	public void setSelectedLine(int line) {
		
		selectedLine = line;
		
	}
}
