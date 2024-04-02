package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PageComponent {
	JPanel panel;
	String fileName;
	JFrame frame;
	/**
	 * @param fileName
	 * @param frame
	 */
	public PageComponent(String fileName, JFrame frame) {
		this.fileName = fileName;
		this.frame = frame;
		
	}
	
}
