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

	public Response(JPanel viewport, int questionNumber, String responseName) {
		super("Response", "Question" + questionNumber + "\\" + responseName, viewport);
		this.responseNumber = Integer.getInteger(responseName);
		this.checkbox = new JCheckBox();
		this.checkbox.setText(this.getHtmlFileData());
		try {
			this.responseNumber = super.extractIntegerAfter("Response", responseName);
		} catch (Exception e) {
			System.out.println("No response name given");
			e.printStackTrace();
		}
		checkbox.setLocation(200 + this.responseNumber, 0);
		checkbox.setPreferredSize(new Dimension(175, 25));
		checkbox.setSize(new Dimension(175, 25));
		this.add(this.checkbox);
	}
}
