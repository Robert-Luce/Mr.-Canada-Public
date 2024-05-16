package components;

import java.util.ArrayList;

import javax.swing.JComboBox;
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
	private JComboBox<String> language;
	private JPanel viewport;

	public DestinationCatalogButtonComponent(JPanel viewport, JComboBox<String> language) {
		super(DESTINATION_CATALOG_BUTTON_FILE_NAME, DESTINATION_CATALOG_BUTTON_FILE_PATH, viewport);
		this.language = language;
		this.viewport = viewport;

	}
	
	@Override
	public void pressed() {
		this.getViewport().removeAll();
		this.page = new Page(viewport, this.language.getSelectedItem().toString() + DESTINATION_CATALOG_NAME);
		this.page.open();
		
		
	}

}
