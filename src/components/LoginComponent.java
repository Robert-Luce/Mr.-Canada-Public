package components;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginComponent extends JComponent {
	private static final String PASSWORD_LABEL_FILE_PATH = "Password Label";
	private static final String PASSWORD_LABEL_FILE_NAME = "Password Label";
	private static final String USERNAME_LABEL_FILE_PATH = "Username Label";
	private static final String USERNAME_LABEL_FILE_NAME = "Username Label";
	private HTMLComponent passwordLabel;
	private HTMLComponent usernameLabel;
	private JTextField usernameField;
	private JTextField passwordField;
	private LoginButtonComponent button;
	private JPanel viewport;
	public LoginComponent(JPanel viewport) {
		super();
		this.viewport = viewport;
		this.passwordLabel = new HTMLComponent(PASSWORD_LABEL_FILE_NAME, PASSWORD_LABEL_FILE_PATH, viewport);
		this.usernameLabel = new HTMLComponent(USERNAME_LABEL_FILE_NAME, USERNAME_LABEL_FILE_PATH, viewport);
		this.passwordField = new JTextField();
		this.usernameField = new JTextField();
		this.button = new LoginButtonComponent(viewport, this.usernameField, this.passwordField);
		this.add(this.passwordLabel);
		this.add(this.usernameLabel);
		this.add(this.passwordField);
		this.add(this.usernameField);
		this.add(this.button);
	}
	public void open() {
		this.viewport.add(this);
	}
}
