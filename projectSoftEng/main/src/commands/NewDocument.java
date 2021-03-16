package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Document;

public class NewDocument implements ActionListener{
	
	private manager manage;
	private ReplayManager replay;
	private String title;
	private String author;
	private String creationDay;
	private Document document;

	public NewDocument(manager man,ReplayManager rep) {
		
		this.manage = man;
		this.replay = rep;
		
	}
	
	public NewDocument(manager man, String t, String a, String Creation,Document doc) {
		
		this.manage = man;
		this.title = t;
		this.author = a;
		this.creationDay= Creation;
		this.document = doc;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e==null) {
			replayAction();
		}else {
			JFrame frame = new JFrame("Create");
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			
			JLabel lblTitle = new JLabel("Title");
			lblTitle.setBounds(50, 37, 46, 14);
			frame.getContentPane().add(lblTitle);
			
			JTextField txtTitle = new JTextField();
			txtTitle.setBounds(106, 34, 186, 20);
			frame.getContentPane().add(txtTitle);
			txtTitle.setColumns(10);
			
			JLabel lblAuthor = new JLabel("Author");
			lblAuthor.setBounds(39, 78, 46, 14);
			frame.getContentPane().add(lblAuthor);
			
			JTextField txtAuthor = new JTextField();
			txtAuthor.setBounds(106, 75, 186, 20);
			frame.getContentPane().add(txtAuthor);
			txtAuthor.setColumns(10);
			
			JButton btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					title = txtTitle.getText();
					author = txtAuthor.getText();
					if(!title.isEmpty() && !author.isEmpty()) {
						String frameTitle = "Title: " + title + " - ";
						String frameAuthor = "Author: " + author;
						LocalDate localDate = LocalDate.now();
						String frameCreationDay = "Creation Day: " + DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate);
						String t1 = frameTitle+frameAuthor;
						creationDay = DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate);
				        document = new Document();
				        manage.setDocument(document);
				        manage.manageNewDocument(t1,frameCreationDay);
				        document.setTitleAuthorDate(title, author,DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate));
				        if(!(e==null)) {
				        	NewDocument copy = new NewDocument(manage,title,author,creationDay,document);
				        	replay.action(copy);
				        }
				        frame.dispose();
					}
				}
			});
			btnCreate.setBounds(39, 117, 89, 23);
			frame.getContentPane().add(btnCreate);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manage.cancelMenu("the user cancelled the operation");
					frame.dispose();
				}
			});
			btnCancel.setBounds(280, 117, 89, 23);
			frame.getContentPane().add(btnCancel);
		}
		
	}
	
	public void replayAction() {
		String frameTitle = "Title: " + title + " - ";
		String frameAuthor = "Author: " + author;
		String frameCreationDay = "Creation Day: " + creationDay;
		String t1 = frameTitle+frameAuthor;
		manage.setDocument(document);
        manage.manageNewDocument(t1,frameCreationDay);
        document.setTitleAuthorDate(title, author,creationDay);
	}
	

}
