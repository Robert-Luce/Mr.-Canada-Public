package components;

import javax.swing.JPanel;

import page.Page;
import place.PlaceLinkedList;

public class SurveyButtonComponent extends ButtonComponent {
	private static final String SURVEY_BUTTON_FILE_PATH = "Survey Button";
	private static final String SURVEY_BUTTON_FILE_NAME = "Survey Button";
	private static final String RESULTS_FILE_NAME = "Results";
	private PlaceLinkedList pLL;
	private Page page;

	public SurveyButtonComponent(JPanel viewport, PlaceLinkedList pLL) {
		super(SURVEY_BUTTON_FILE_NAME, SURVEY_BUTTON_FILE_PATH, viewport);
		this.pLL = pLL;
		this.page = new Page(viewport, RESULTS_FILE_NAME);
		this.page.setPLL(this.pLL);
	}
	
	public void pressed() {
		this.getViewport().removeAll();
		this.page.open();
	}

}
