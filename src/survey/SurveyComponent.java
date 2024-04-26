package survey;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import place.*;

public class SurveyComponent extends JPanel implements ActionListener{
	ArrayList<String> questionTitles;
	ArrayList<ArrayList<String>> responses;
	private JFrame frame;
	
	public SurveyComponent(JFrame frame, ArrayList<String> questionTitles, ArrayList<ArrayList<String>> responses, ArrayList<Place> places) {
//		super(new BorderLayout());
//		this.setLayout(null);
		this.frame = frame;
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
		
//		JTable table = new JTable();
		for(int i = 0; i < this.questionTitles.size(); i++) {
			JLabel label = new JLabel(this.questionTitles.get(i));
			JPanel tempPanel = new JPanel();
			label.setPreferredSize(new Dimension(500, 50));
			label.setSize(new Dimension(500, 50));
			label.setLocation(200, 100*i);
//			label.setLayout(null);
			tempPanel.add(label);
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
					} });
				checkbox.setLocation(200, 100*i + 50*j);
				checkbox.setPreferredSize(new Dimension(100, 25));
				checkbox.setSize(new Dimension(100, 25));
//				checkbox.setLayout(null);
				tempPanelInner.add(checkbox);
				tempPanel.add(tempPanelInner);
			}
				this.add(tempPanel);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
