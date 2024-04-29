package survey;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.HTML;
import place.Place;

public class Question extends HTML {
	
	private JLabel label;
	private int questionNumber;
	private ArrayList<Response> responses ;
	
	public Question(String fileName, JPanel viewport, int questionNumber) {
		super(fileName, "Question"+questionNumber, viewport);
		this.responses = new ArrayList<Response>();
		this.label = new JLabel(this.getHtmlFileData());
		this.questionNumber = questionNumber;
		this.label.setPreferredSize(new Dimension(500, 50));
		this.label.setSize(new Dimension(500, 50));
		this.setLocation(0, 100*this.questionNumber);
		for(int i = 0; i < this.responses.size(); i++) {
			this.add(this.responses.get(i));
		}
		this.setPreferredSize(new Dimension(500, 50));
		this.setSize(new Dimension(500, 50));
		this.add(this.label);
	}
	
}
