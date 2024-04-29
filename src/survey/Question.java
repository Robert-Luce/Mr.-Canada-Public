package survey;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.HTML;

public class Question extends HTML {
	
	private JLabel label;
	private JPanel panel;
	private int questionNumber;

	public Question(String fileName, JPanel viewport, int questionNumber) {
		super(fileName, "Question"+questionNumber, viewport);
		this.label = new JLabel(this.getHtmlFileData());
		this.panel = new JPanel();
		this.questionNumber = questionNumber;
		this.label.setPreferredSize(new Dimension(500, 50));
		this.label.setSize(new Dimension(500, 50));
		this.panel.add(label, BorderLayout.WEST);
		this.panel.setLocation(0, 200);
		this.setLocation(0, 100*this.questionNumber);
		this.setPreferredSize(new Dimension(500, 50));
		this.setSize(new Dimension(500, 50));
		this.add(this.panel);
		this.add(this.label);
	}
	
}
