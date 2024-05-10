package survey;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.HTMLReaderComponent;
import place.Place;

public class Question extends HTMLReaderComponent {

	private JLabel label;
	private int questionNumber;
	private ArrayList<Response> responses;
	private ArrayList<String> responseNames;

	public Question(JPanel viewport, String questionName, String language) {
		super(questionName, questionName, viewport);
		this.responses = new ArrayList<Response>();
		this.label = new JLabel(this.getHtmlFileData());
		try {
			this.questionNumber = super.extractIntegerAfter("Question", questionName);
		} catch (Exception e) {
			System.out.println("No question name given");
		}
		this.label.setPreferredSize(new Dimension(500, 50));
		this.label.setSize(new Dimension(500, 50));
		this.label.setLocation(0, 0);
		this.setLocation(0, 100 * this.questionNumber);
		try {
			this.responseNames = new ArrayList<String>(Arrays.asList(Files
					.readString(Path.of(Path.of("MrCanadaData\\" + language + "Question" + this.questionNumber + "\\Response Names.txt")
							.toAbsolutePath().toString()))
					.split("\r\n")));
		} catch (Exception e) {
			System.out.println(
					"Please add text to MrCanadaData\\Question" + this.questionNumber + "\\Response Names.txt");
			this.responseNames = new ArrayList<String>();
		}
		for (String responseName : responseNames) {
			this.responses.add(new Response(this.getViewport(), this.questionNumber, responseName, language));
		}
		for (int i = 0; i < this.responses.size(); i++) {
			this.add(this.responses.get(i));
			this.responses.get(i).setPreferredSize(new Dimension(175, 75));
			this.responses.get(i).setSize(175, 75);
			this.responses.get(i).setLocation(500 + 175*i, 13);;
			this.add(this.responses.get(i));
		}
		this.setPreferredSize(new Dimension(1000, 50));
		this.setSize(new Dimension(1000, 50));
		this.add(this.label);
	}

}
