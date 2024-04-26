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

public class SurveyComponent extends JPanel implements ActionListener{
	ArrayList<String> questionTitles = new ArrayList<String>();
	ArrayList<ArrayList<String>> responses = new ArrayList<ArrayList<String>>();
	ArrayList<Place> places = new ArrayList<Place>();
	private JFrame frame;
	
	public SurveyComponent(JFrame frame) {
//		super(new BorderLayout());
//		this.setLayout(null);
		this.frame = frame;
		
		this.generateQuestions();
		this.generateQuestionResponses();
		
		places.add(new Restaurant("test 1", "Montreal", true, "Moroccan", true, true, false));
		places.add(new Restaurant("test 2", "Toronto", false, "Canadian", true, false, true));
		places.add(new Restaurant("test 3", "Quebec City", true, "Other", false, true, true));
		places.add(new Restaurant("test 4", "Montreal", false, "Canadian", false, false, false));
		places.add(new Restaurant("test 5", "Quebec City", true, "Moroccan", true, false, true));
		
//		JTable table = new JTable();
		for(int i = 0; i < this.questionTitles.size(); i++) {
			JLabel label = new JLabel(this.questionTitles.get(i)+ "?");
			JPanel tempPanel = new JPanel();
			label.setPreferredSize(new Dimension(500, 50));
			label.setSize(new Dimension(500, 50));
			label.setLocation(200, 100*i);
			tempPanel.add(label, BorderLayout.WEST);
			for(int j = 0; j < this.responses.get(i).size(); j++) {
				JPanel tempPanelInner = new JPanel();
				String text = this.responses.get(i).get(j);
				JCheckBox checkbox = new JCheckBox();
				checkbox.setText(text);
				checkbox.addActionListener(new ActionListener() {
					boolean checked = false;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(!checked) {
							checked = true;
							System.out.println(text + " is checked");
							
						} else {
							checked = false;
							System.out.println(text + " is unchecked");
						}
						for(Place p: places) {
							p.checkCriteria(text, checked);	
						}
					} });
				checkbox.setLocation(200, 100*i + 50*j);
				checkbox.setPreferredSize(new Dimension(175, 25));
				checkbox.setSize(new Dimension(175, 25));
//				checkbox.setLayout(null);
				tempPanelInner.add(checkbox);
				tempPanel.add(tempPanelInner);
			}
				this.add(tempPanel);
		}
		
		JButton checkButton = new JButton("Check");
		checkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Place p: places) {
					System.out.println("Place " + p.getName() + " has score of " + p.getScore());
				}
			}
			
		});
		this.add(checkButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void generateQuestions() {
		try {
			ArrayList<String> questions = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\SurveyQuestions\\Questions.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			this.questionTitles = questions;
		} catch (Exception e) {
			System.out.println("Please add text to MrCanadaData\\\\SurveyQuestions\\\\Questions.txt");
		}
	}
	
	public void generateQuestionResponses() {
		for(String q : questionTitles) {
			try {
				ArrayList<String> fileResponses = new ArrayList<String>(Arrays.asList(Files
						.readString(Path
								.of(Path.of("MrCanadaData\\SurveyQuestions\\" + q + ".txt").toAbsolutePath().toString()))
						.split("\r\n")));
				this.responses.add(fileResponses);
			} catch (Exception e) {
				System.out.println("Please add text to MrCanadaData\\\\SurveyQuestions\\\\" + q + ".txt");
			}
		}
	}
	
	public void generatePlaces() {
		
	}
	
}
