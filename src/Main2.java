import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.LoginComponent;
import page.Page;

public class Main2 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mr. Canada");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1080, 1000)); // sets size of scroll
		panel.setSize(1080, 1000); // sets size of window
		panel.setLocation(0, 0);
		LoginComponent login = new LoginComponent(panel);
		login.open();
		frame.setSize(new Dimension(1080, 1000));
		JScrollPane scroll = new JScrollPane(panel);
		frame.add(scroll);
		frame.setVisible(true);
		frame.repaint();
	}
}