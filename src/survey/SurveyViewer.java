package survey;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import page.Page;
import place.*;

public class SurveyViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayList<String> questions = new ArrayList<>();
		ArrayList<ArrayList<String>> responses = new ArrayList<>();
		ArrayList<Place> places = new ArrayList<>();
		
		SurveyPanel surveyPanel = new SurveyPanel(frame, questions, responses, places);
//		surveyPanel.setLayout(null);
		surveyPanel.setPreferredSize(new Dimension(500, 500));
		surveyPanel.setLocation(100, 100);
		frame.pack();
		frame.setSize(new Dimension(500, 500));
		JScrollPane scroll = new JScrollPane(surveyPanel);
		frame.add(scroll);
		frame.setVisible(true);
		frame.repaint();
	}

}
