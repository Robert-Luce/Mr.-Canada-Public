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
	ArrayList<ArrayList<String>> responses = new ArrayList<ArrayList<String>>();
	ArrayList<Place> places = new ArrayList<Place>();

	
	public SurveyComponent(JFrame frame) {
//		super(new BorderLayout());
//		this.setLayout(null);

		
		this.generateQuestions();
		this.generateQuestionResponses();
		this.generatePlaces();
		
//		JTable table = new JTable();
		for(int i = 0; i < this.questions.size(); i++) {
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
		ArrayList<String> placeNames = new ArrayList<String>();
		try {
			ArrayList<String> ps = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Places English\\PlacesList.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			placeNames.addAll(ps);
		} catch (Exception e) {
			System.out.println("Please add text to MrCanadaData\\\\SurveyQuestions\\\\PlacesList.txt");
		}
		
		for(String placeName : placeNames) {
			ArrayList<String> placeInfo = new ArrayList<String>();
			try {
				ArrayList<String> info = new ArrayList<String>(Arrays.asList(Files
						.readString(Path
								.of(Path.of("MrCanadaData\\Places English\\" + placeName + ".txt").toAbsolutePath().toString()))
						.split("\r\n")));
				placeInfo.addAll(info);
			} catch (Exception e) {
				System.out.println("Please add text to MrCanadaData\\\\Places English\\\\" + placeName + ".txt");
			}
			
			
			if(placeInfo.get(0).equals("Restaurant")) {
				
				buildRestaurant(placeInfo, placeName);
				
			}
			
			
			
		}
		
	}

	private void buildRestaurant(ArrayList<String> placeInfo, String placeName) {
		boolean isAccessible = false;
		boolean isVegetarian = false;
		boolean isHalal = false;
		boolean isVegan = false;
		
		if(placeInfo.get(2).equals("Accessible")) {
			isAccessible = true;
		}
		if(placeInfo.get(4).equals("Vegetarian")) {
			isVegetarian = true;
		}
		if(placeInfo.get(5).equals("Halal")) {
			isHalal = true;
		}
		if(placeInfo.get(6).equals("Vegan")) {
			isVegan = true;
		}
		
		Restaurant r = new Restaurant(placeName, placeInfo.get(1), isAccessible, placeInfo.get(3), isVegetarian, isHalal, isVegan);
		
		places.add(r);
	}
	
}
