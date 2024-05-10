package components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import page.Page;
import place.Place;
import survey.PlaceManager;
import survey.SurveyComponent;
import user.User;
/**
 * @author lucerc
 * @author leonemm
 * @author walindqg
 */
public class LoginButtonComponent extends ButtonComponent {
	private static final String LOGIN_BUTTON_FILE_PATH = "Login Button";
	private static final String LOGIN_BUTTON_FILE_NAME = "Login Button";
	private static final String INCORRECT_PASSWORD_FILE_PATH = "Incorrect Password";
	private static final String INCORRECT_PASSWORD_FILE_NAME = "Incorrect Password";
	private JTextField password;
	private JTextField username;
	private User user;
	private JComboBox<String> language;
	private JPanel viewport;

	/**
	 * ensures: LoginButtonComponent is constructed
	 * @param viewport
	 * @param username
	 * @param password
	 * @param language
	 */
	public LoginButtonComponent(JPanel viewport, JTextField username, JTextField password, JComboBox<String> language) {
		super(LOGIN_BUTTON_FILE_NAME, LOGIN_BUTTON_FILE_PATH, viewport);
		this.viewport = viewport;
		this.language = language;
		this.username = username;
		this.password = password;
	}

	/**
	 * ensures: 
	 */
	@Override
	public void pressed() {
		try {
			this.user = new User(this.username.getText(), this.password.getText(), this.language.getSelectedItem().toString());
			if (this.user.hasCriteria()) { // checks if user has already done the survey
				System.out.println("user exists");
				Page resultsPage = new Page(viewport, language.getSelectedItem() + "Results");
				ArrayList<Place> placeList = new ArrayList<>();
				PlaceManager pM = new PlaceManager(placeList, viewport, this.language.getSelectedItem().toString());
				pM.generatePlaces();
				pM.assessLocations(user);

				this.getViewport().removeAll();
				resultsPage.setPLL(pM.getResults());
				this.getViewport().repaint();
				this.getViewport().revalidate();
				resultsPage.open();
			} else { // if user hasn't done survey, starts the survey
				this.getViewport().removeAll();
				this.getViewport().repaint();
				this.getViewport().revalidate();
				SurveyComponent survey = new SurveyComponent(this.getViewport(), this.language.getSelectedItem().toString(), this.user);
				survey.open();
			}
		} catch (Exception e) { // if any of the above fails (i.e. wrong password), displays error message
			JFrame errorFrame = new JFrame("Mr. Canada");
			errorFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			JPanel panel = new JPanel();
			HTMLComponent component = new HTMLComponent(INCORRECT_PASSWORD_FILE_NAME, INCORRECT_PASSWORD_FILE_PATH, panel);
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(component.getHtmlWidth(), component.getHtmlHeight()));
			panel.setLocation(0, 0);
			component.open();
			errorFrame.setSize(new Dimension(1080, 1000));
			errorFrame.setPreferredSize(new Dimension(1080, 1000));
			JScrollPane scroll = new JScrollPane(panel);
			errorFrame.add(scroll);
			errorFrame.setVisible(true);
			errorFrame.repaint();
		}
	}

}
