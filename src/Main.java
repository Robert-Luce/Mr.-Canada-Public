import java.awt.Dimension;

import javax.swing.JFrame;

import Page.Page;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		Page page = new Page(frame, "DestinationCatalog");
		page.open();
		frame.setSize(new Dimension(1450, 1000));
		frame.setVisible(true);
		frame.repaint();
	}
}