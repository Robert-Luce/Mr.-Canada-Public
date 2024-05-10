package components;

import java.util.ArrayList;

import javax.swing.JPanel;

import page.Page;
import place.Place;
import place.PlaceLinkedList;
import survey.PlaceManager;
import user.User;
/**
 * @author lucerc
 * @author walindqg
 * @author leonemm
 */
public class SurveyButtonComponent extends ButtonComponent {
	private static final String SURVEY_BUTTON_FILE_PATH = "Survey Button";
	private static final String SURVEY_BUTTON_FILE_NAME = "Survey Button";
	private static final String RESULTS_FILE_NAME = "Results";
	private PlaceLinkedList pLL = new PlaceLinkedList();
	private Page page;
	private PlaceManager pM;
	private User user;

	public SurveyButtonComponent(JPanel viewport, PlaceManager placeManager, String language, User user) {
		super(language + SURVEY_BUTTON_FILE_NAME, language + SURVEY_BUTTON_FILE_PATH, viewport);
		this.user = user;
		this.pM = placeManager;
		this.page = new Page(viewport, language + RESULTS_FILE_NAME);
		this.pLL = new PlaceLinkedList();
	}
	
	@Override
	public void pressed() {
		pM.assessLocations(user);
		pLL = pM.getResults();
		this.page.setPLL(pLL);
		this.getViewport().removeAll();
		this.page.open();
		
	}

}
