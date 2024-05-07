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
//		frame.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		SurveyComponent surveyPanel = new SurveyComponent(panel);		
		
//		frame.pack();
		surveyPanel.open();
		frame.setSize(new Dimension(1080, 1000));
		JScrollPane scroll = new JScrollPane(surveyPanel);
		
		frame.add(scroll);
		
		frame.setVisible(true);

		frame.repaint();
	}

}
