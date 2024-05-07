package components;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import page.Page;
import survey.SurveyComponent;
import user.User;

public class LoginButtonComponent extends ButtonComponent {
	private static final String LOGIN_BUTTON_FILE_PATH = "Login Button";
	private static final String LOGIN_BUTTON_FILE_NAME = "Login Button";
	private static final String INCORRECT_PASSWORD_FILE_PATH = "Incorrent Password";
	private static final String INCORRECT_PASSWORD_FILE_NAME = "Incorrect Password";
	private JTextField password;
	private JTextField username;
	private User user;

	public LoginButtonComponent(JPanel viewport, JTextField username, JTextField password) {
		super(LOGIN_BUTTON_FILE_NAME, LOGIN_BUTTON_FILE_PATH, viewport);
		this.username = username;
		this.password = password;
	}

	@Override
	public void pressed() {
		try {
			this.user = new User(this.username.getText(), this.password.getText());
			if (this.user.hasCriteria()) {
				
			} else {
				
				SurveyComponent survey = new SurveyComponent(this.getViewport());
				survey.open();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JFrame frame = new JFrame("Mr. Canada");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(1080, 1000));
			panel.setLocation(0, 0);
			HTMLComponent component = new HTMLComponent(INCORRECT_PASSWORD_FILE_NAME, INCORRECT_PASSWORD_FILE_PATH, panel);
			component.open();
			frame.setSize(new Dimension(1080, 1000));
			JScrollPane scroll = new JScrollPane(panel);
			frame.add(scroll);
			frame.setVisible(true);
			frame.repaint();
		}
	}

}
