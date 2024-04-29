package survey;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import components.HTML;
import place.Place;

public class Response extends HTML {

	private JCheckBox checkbox;
	private int responseNumber;

	public Response(String fileName, String filePath, JPanel viewport, int questionNumber, int responseNumber) {
		super(fileName, "Question" + questionNumber + "\\Response" + responseNumber, viewport);
		this.checkbox = new JCheckBox();
		this.checkbox.setText(this.getHtmlFileData());
		this.responseNumber = responseNumber;
		checkbox.setLocation(200 + this.responseNumber, 0);
		checkbox.setPreferredSize(new Dimension(175, 25));
		checkbox.setSize(new Dimension(175, 25));
		this.add(this.checkbox);
	}
}
