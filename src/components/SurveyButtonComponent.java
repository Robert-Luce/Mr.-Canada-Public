package components;

import java.util.ArrayList;

import javax.swing.JPanel;

import page.Page;
import place.Place;
import place.PlaceLinkedList;
import survey.PlaceManager;

public class SurveyButtonComponent extends ButtonComponent {
	private static final String SURVEY_BUTTON_FILE_PATH = "Survey Button";
	private static final String SURVEY_BUTTON_FILE_NAME = "Survey Button";
	private static final String RESULTS_FILE_NAME = "Results";
	private PlaceLinkedList pLL = new PlaceLinkedList();
	private Page page;
	private PlaceManager pM;

	public SurveyButtonComponent(JPanel viewport, PlaceManager placeManager) {
		super(SURVEY_BUTTON_FILE_NAME, SURVEY_BUTTON_FILE_PATH, viewport);
		this.pM = placeManager;
		this.page = new Page(viewport, RESULTS_FILE_NAME);
	}
	
	public void pressed() {
		this.getViewport().removeAll();
		pM.getResults(pLL);
		this.page.setPLL(pLL);
		this.page.open();
	}

}
