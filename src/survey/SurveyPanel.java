package survey;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import place.*;

public class SurveyPanel extends JPanel implements ActionListener{
	ArrayList<String> questionTitles;
	ArrayList<ArrayList<String>> responses;
	
	public SurveyPanel(ArrayList<String> questionTitles, ArrayList<ArrayList<String>> responses, ArrayList<Place> places) {
		this.questionTitles = questionTitles;
		this.responses = responses;
		
		this.questionTitles.add("What city are you interested in?");
		this.questionTitles.add("What type of cuisine are you looking for?");

		
		this.responses.add(new ArrayList<String>());
		this.responses.get(0).add("Montreal");
		this.responses.get(0).add("Toronto");
		this.responses.get(0).add("Quebec");
		this.responses.add(new ArrayList<String>());
		this.responses.get(1).add("Moroccan");
		this.responses.get(1).add("Canadian");
		
		for(int i = 0; i < this.questionTitles.size(); i++) {
			JLabel label = new JLabel(this.questionTitles.get(i));
			this.add(label, BorderLayout.SOUTH);
			for(int j = 0; j < this.responses.get(i).size(); j++) {
				String text = this.responses.get(i).get(j);
				JCheckBox checkbox = new JCheckBox();
				checkbox.setText(text);
				checkbox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(text);
					} });
				this.add(checkbox, BorderLayout.NORTH);
			
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
