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

import components.HTML;
import place.Place;

public class Question extends HTML {

	private JLabel label;
	private int questionNumber;
	private ArrayList<Response> responses;
	private ArrayList<String> responseNames;

	public Question(JPanel viewport, String questionName) {
		super("Question", questionName, viewport);
		this.responses = new ArrayList<Response>();
		this.label = new JLabel(this.getHtmlFileData());
		try {
			this.questionNumber = super.extractIntegerAfter("Response", questionName);
		} catch (Exception e) {
			System.out.println("No question name given");
			e.printStackTrace();
		}
		this.label.setPreferredSize(new Dimension(500, 50));
		this.label.setSize(new Dimension(500, 50));
		this.setLocation(0, 100 * this.questionNumber);
		try {
			this.responseNames = new ArrayList<String>(Arrays.asList(Files
					.readString(Path.of(Path.of("MrCanadaData\\Question" + this.questionNumber + "\\Response Names.txt")
							.toAbsolutePath().toString()))
					.split("\r\n")));
		} catch (Exception e) {
			System.out.println(
					"Please add text to " + "MrCanadaData\\Question" + this.questionNumber + "\\Questions.txt");
			this.responseNames = new ArrayList<String>();
		}
		for (String responseName : responseNames) {
			this.responses.add(new Response(this.getViewport(), this.questionNumber, responseName));
		}
		for (int i = 0; i < this.responses.size(); i++) {
			this.add(this.responses.get(i));
		}
		this.setPreferredSize(new Dimension(500, 50));
		this.setSize(new Dimension(500, 50));
		this.add(this.label);
	}

}
