package components;

import java.util.ArrayList;

import javax.swing.JPanel;

import page.Page;
import place.Place;
import place.PlaceLinkedList;
import survey.PlaceManager;
import user.User;
/**
 * @author walindqg
 */
public class DestinationCatalogButtonComponent extends ButtonComponent {
	private static final String DESTINATION_CATALOG_BUTTON_FILE_PATH = "DestinationCatalog Button";
	private static final String DESTINATION_CATALOG_BUTTON_FILE_NAME = "DestinationCatalog Button";
	private static final String DESTINATION_CATALOG_NAME = " DestinationCatalog";
	private Page page;

	public DestinationCatalogButtonComponent(JPanel viewport, String language) {
		super(DESTINATION_CATALOG_BUTTON_FILE_NAME, DESTINATION_CATALOG_BUTTON_FILE_PATH, viewport);
		this.page = new Page(viewport, language + DESTINATION_CATALOG_NAME);

	}
	
	@Override
	public void pressed() {
		this.getViewport().removeAll();
		this.page.open();
		
		
	}

}
