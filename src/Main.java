import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.LoginComponent;
import page.Page;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mr. Canada");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1080, 1000)); 
		panel.setSize(1080, 1000);
		panel.setLocation(0, 0);
		LoginComponent login = new LoginComponent(panel);
		login.open();
		frame.setSize(new Dimension(1080, 1000));
		JScrollPane scroll = new JScrollPane(panel);
		frame.add(scroll);
		frame.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.repaint();
	}
}