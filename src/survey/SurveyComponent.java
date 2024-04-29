package survey;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import place.*;

public class SurveyComponent extends JPanel{
	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<Place> places = new ArrayList<Place>();
	private JPanel viewport;
	private ArrayList<String> questionNames;

	
	public SurveyComponent(JPanel viewport) {
		this.viewport = viewport;
		try {
			this.questionNames = new ArrayList<String>(Arrays.asList(Files
					.readString(Path.of(Path.of("MrCanadaData\\Question Names.txt")
							.toAbsolutePath().toString()))
					.split("\r\n")));
		} catch (Exception e) {
			System.out.println(
					"Please add text to " + "MrCanadaData\\Question Names.txt");
			this.questionNames = new ArrayList<String>();
		}
		for (String questionName : questionNames) {
			this.questions.add(new Question(this.viewport, questionName));
		}
		for (int i = 0; i < this.questions.size(); i++) {
			this.add(this.questions.get(i));
		}
	}
	public void open() {
		this.viewport.add(this);
	}
	public void close() {
		this.viewport.remove(this);
	}

	
}
