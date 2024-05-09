import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import page.Page;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mr. Canada");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1080, 1000));
		panel.setLocation(0, 0);
		Page page = new Page(panel, "French DestinationCatalog");
		page.open();
		frame.setSize(new Dimension(1080, 1000));
		JScrollPane scroll = new JScrollPane(panel);
		frame.add(scroll);
		frame.setVisible(true);
		frame.repaint();
	}
}