package components;

import java.awt.Dimension;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import page.Page;
/**
 * @author lucerc
 * @author jaraczlo
 */
public class LoginComponent extends JComponent {
	private static final String PASSWORD_LABEL_FILE_PATH = "Password Label";
	private static final String PASSWORD_LABEL_FILE_NAME = "Password Label";
	private static final String USERNAME_LABEL_FILE_PATH = "Username Label";
	private static final String USERNAME_LABEL_FILE_NAME = "Username Label";
	private static final String LANGUAGE_LABEL_FILE_NAME = "Languages Label";
	private static final String LANGUAGES_LABEL_FILE_PATH = "Languages Label";
	private HTMLComponent passwordLabel;
	private HTMLComponent usernameLabel;
	private JTextField usernameField;
	private JTextField passwordField;
	private LoginButtonComponent button;
	private JPanel viewport;
	private String languageAbsolutePath;
	private String[] languages;
	private JComboBox<String> language;
	private HTMLComponent languageLabel;

	/**
	 * ensures:  LoginComponent is constructed 
	 * @param viewport
	 */
	public LoginComponent(JPanel viewport) {
		super();
		this.viewport = viewport;
		this.passwordLabel = new HTMLComponent(PASSWORD_LABEL_FILE_NAME, PASSWORD_LABEL_FILE_PATH, viewport);
		this.usernameLabel = new HTMLComponent(USERNAME_LABEL_FILE_NAME, USERNAME_LABEL_FILE_PATH, viewport);
		this.languageLabel = new HTMLComponent(LANGUAGE_LABEL_FILE_NAME, LANGUAGES_LABEL_FILE_PATH, viewport);
		this.passwordField = new JTextField();
		this.usernameField = new JTextField();
		this.passwordField.setLocation(this.passwordLabel.getHtmlX() + this.passwordLabel.getHtmlWidth(),
				this.passwordLabel.getHtmlY());
		this.passwordField.setSize(this.passwordLabel.getHtmlWidth(), this.passwordLabel.getHtmlHeight());
		this.passwordField
				.setPreferredSize(new Dimension(this.passwordLabel.getHtmlWidth(), this.passwordLabel.getHtmlHeight()));
		this.usernameField.setLocation(this.passwordLabel.getHtmlX() + this.passwordLabel.getHtmlWidth(),
				this.usernameLabel.getHtmlY());
		this.usernameField.setSize(this.usernameLabel.getHtmlWidth(), this.usernameLabel.getHtmlHeight());
		this.usernameField
				.setPreferredSize(new Dimension(this.usernameLabel.getHtmlWidth(), this.usernameLabel.getHtmlHeight()));
		try {
			this.languageAbsolutePath = Path.of("MrCanadaData\\languages.txt").toAbsolutePath().toString();
			this.languages = Files.readString(Path.of(this.languageAbsolutePath)).split("\r\n");
		} catch (Exception e) {
			System.out.println("Error accessing file MrCanadaData\\languages.txt");
		}
		this.language = new JComboBox<String>(this.languages);
		this.language.setLocation(this.languageLabel.getHtmlX() + this.languageLabel.getHtmlWidth(),
				this.languageLabel.getHtmlY());
		this.language.setSize(this.languageLabel.getHtmlWidth(), this.languageLabel.getHtmlHeight());
		this.language
				.setPreferredSize(new Dimension(this.languageLabel.getHtmlWidth(), this.languageLabel.getHtmlHeight()));
		this.button = new LoginButtonComponent(viewport, this.usernameField, this.passwordField, this.language);
		this.add(this.passwordLabel);
		this.add(this.usernameLabel);
		this.add(this.passwordField);
		this.add(this.usernameField);
		this.add(this.button);
		this.add(this.language);
		this.add(this.languageLabel);
		this.setSize(this.viewport.getSize());
		this.setPreferredSize(this.viewport.getPreferredSize());
		this.setLocation(0, 0);
		
//		Page destinationFromLog = new Page(viewport, "DestinationCatalog FromHomepage");
//		destinationFromLog.thumbnailOpen();
		
//		
//		ThumbnailComponent DestinationCatalogThumbnail = new ThumbnailComponent("DestinationCatalog FromHomepage", viewport);
//		this.add(DestinationCatalogThumbnail);
	}

	/**
	 * ensures: the viewport Jpanel is added to the JScrollFrame and adds an associated MouseListener
	 */
	public void open() {
		this.viewport.add(this);
		this.viewport.addMouseListener(this.button.getListener());
	}

	/**
	 * ensures: the viewport Jpanel is removed to the JScrollFrame and removes an associated MouseListener
	 */
	public void close() {
		this.viewport.remove(this);
		this.viewport.removeMouseListener(this.button.getListener());
	}
}
