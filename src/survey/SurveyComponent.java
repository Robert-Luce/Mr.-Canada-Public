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

import components.ButtonComponent;
import components.SurveyButtonComponent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import place.*;

public class SurveyComponent extends JPanel {
	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<Place> places = new ArrayList<Place>();
	private JPanel viewport;
	private ArrayList<String> questionNames;
	private SurveyButtonComponent checkButton;

	public SurveyComponent(JPanel viewport, String language) {
		this.viewport = viewport;
		try {
			this.questionNames = new ArrayList<String>(Arrays.asList(Files
					.readString(Path.of(Path.of("MrCanadaData\\" + language + "Question Names.txt")
							.toAbsolutePath().toString()))
					.split("\r\n")));
		} catch (Exception e) {
			System.out.println(
					"Error reading MrCanadaData\\" + language + "Question Names.txt");
			this.questionNames = new ArrayList<String>();
		}
		for (String questionName : questionNames) {
			this.questions.add(new Question(this.viewport, questionName));
		}
		for (int i = 0; i < this.questions.size(); i++) {
			this.add(this.questions.get(i));
		}
		
		PlaceManager placeManager = new PlaceManager(places, viewport, language);
		placeManager.generatePlaces();
//		placeManager.assessLocations();
		this.checkButton = new SurveyButtonComponent(viewport, placeManager);
		this.add(this.checkButton);
		this.setPreferredSize(new Dimension(1080, 1000));
		this.setSize(1080, 1000);
		this.setLocation(0, 0);
		this.setLayout(null);
		
	}

	public void open() {
		this.viewport.addMouseListener(this.checkButton.getListener());
		this.viewport.add(this);
		this.viewport.repaint();
		this.viewport.revalidate();
	}

	public void close() {
		this.viewport.removeMouseListener(this.checkButton.getListener());
		this.viewport.remove(this);
		this.viewport.repaint();
		this.viewport.revalidate();
	}

}
