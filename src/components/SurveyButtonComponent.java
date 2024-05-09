package components;

import java.util.ArrayList;

import javax.swing.JPanel;

import page.Page;
import place.Place;
import place.PlaceLinkedList;

public class SurveyButtonComponent extends ButtonComponent {
	private static final String SURVEY_BUTTON_FILE_PATH = "Survey Button";
	private static final String SURVEY_BUTTON_FILE_NAME = "Survey Button";
	private static final String RESULTS_FILE_NAME = "Results";
	private PlaceLinkedList pLL;
	private Page page;

	public SurveyButtonComponent(JPanel viewport, PlaceLinkedList places) {
		super(SURVEY_BUTTON_FILE_NAME, SURVEY_BUTTON_FILE_PATH, viewport);
		this.pLL = places;
		this.page = new Page(viewport, RESULTS_FILE_NAME);
		this.page.setPLL(this.pLL);
	}
	
	public void pressed() {
		System.out.println("hi");
		this.getViewport().removeAll();
		this.page.open();
	}

}
