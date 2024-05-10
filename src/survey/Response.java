package survey;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import components.HTMLReaderComponent;
import listeners.ResponseCheckBoxListener;
import place.Place;

public class Response extends HTMLReaderComponent {

	private JCheckBox checkbox;
	private int responseNumber;
	private ArrayList<String> criteria;

	/**
	 * @author lucerc
	 * ensures: default constructor for the Response class.
	 * @param viewport - the JPanel for which the Response should be displayed on.
	 * @param questionNumber - the number of the associated question in the file system
	 * @param responseName - the name of the response file in the file system
	 * @param language - the language that the response should be displayed in
	 */
	public Response(JPanel viewport, int questionNumber, String responseName, String language) {
		super(responseName, language + "Question" + questionNumber + "\\" + responseName, viewport);
		this.checkbox = new JCheckBox();
		this.checkbox.setText(this.getHtmlFileData());
		try {
			this.responseNumber = super.extractIntegerAfter("Response", responseName);
		} catch (Exception e) {
			System.out.println("No response name given");
			e.printStackTrace();
		}
		checkbox.setLocation(0, 0);
		checkbox.setPreferredSize(new Dimension(175, 25));
		checkbox.setSize(new Dimension(175, 25));
		this.add(this.checkbox);
		this.criteria = new ArrayList<String>(Arrays.asList(this.getTxtFileData().split("\r\n")));
		for (int i = 0; i < criteria.size(); i++) {
			if(this.criteria.get(i).contains("Criteria=")){
				
				this.criteria.set(i, this.criteria.get(i).replace("Criteria=", ""));
			} else {
				this.criteria.remove(i);
				i--;
			}
			
		}
		this.checkbox.addItemListener(new ResponseCheckBoxListener(this.criteria, "Criteria Test.txt"));
	}
}
