package survey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import components.HTMLComponent;
import place.*;
import user.User;

/**
 * @author walindqg
 * @author jaraczlo
 * 
 */

public class PlaceManager {
	private ArrayList<Place> places;
	private PlaceLinkedList restaurantLL;
	private PlaceLinkedList attractionLL;
	private PlaceLinkedList placeofworshipLL;
	private JPanel viewport;
	private String language;
	
	public PlaceManager(ArrayList<Place> places, JPanel viewport, String language) {
		this.places = places;
		this.viewport = viewport;
		this.language = language;
	}
	
	
	public void assessLocations(User user) {
		for(Place p: places) {
			p.setScore(1);
		}
		ArrayList<String> criteria = new ArrayList<String>();
		try {
			ArrayList<String> cs = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Users\\"+user.getUsername()+"\\Criteria.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			criteria.addAll(cs);
		} catch (Exception e) {
			System.out.println("Please select a criteria");
		}
		
		restaurantLL = new PlaceLinkedList();
		attractionLL = new PlaceLinkedList();
		placeofworshipLL = new PlaceLinkedList();
		for(Place p : places) {
			for(String c : criteria) {
				p.checkCriteria(c);
			}
			if(p.getName().contains("Restaurant")) {
				restaurantLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				restaurantLL.sort();
			} else if (p.getName().contains("Attraction")) {
				attractionLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				attractionLL.sort();
			} else if (p.getName().contains("PlaceOfWorship")) {
				placeofworshipLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				placeofworshipLL.sort();
			}
			
		}
	}
	
	/**
	 * ensures: manages the creation of Place classes based on information built into the file system.
	 */
	public void generatePlaces() {
		ArrayList<String> placeNames = new ArrayList<String>();
		try {
			ArrayList<String> ps = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Separate Locations " + language + "\\PlacesList.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			placeNames.addAll(ps);
		} catch (Exception e) {
			System.out.println("Please add MrCanadaData\\Separate Locations English\\PlacesList.txt");
		}

		for(String placeName : placeNames) {
			ArrayList<String> placeInfo = new ArrayList<String>();
			try {
				ArrayList<String> info = new ArrayList<String>(Arrays.asList(Files
						.readString(Path
								.of(Path.of("MrCanadaData\\Separate Locations " + language + "\\" + placeName + "\\" + placeName +".txt").toAbsolutePath().toString()))
						.split("\r\n")));
				placeInfo.addAll(info);
			} catch (Exception e) {
				System.out.println("Please add MrCanadaData\\Separate Locations English\\" + placeName + "\\" + placeName +".txt" );
			}
			places.add(new Place(placeName, placeInfo.get(1), placeInfo));
		}
		
		

	}
	
	/**
	 * ensures: generates an HTMLComponent object for a given place name
	 * @param placeName - the name of the place for which the HTMLComponent should be generated
	 * @param panel - the panel on which the HTMLComponent will be placed
	 * @return returns the constructed HTMLComponent
	 */
	public HTMLComponent generateHTML(String placeName, JPanel panel) {
		HTMLComponent h = new HTMLComponent(placeName, "Separate Locations " + language + "\\" + placeName, panel);
		return h;
	}
	
	/**
	 * ensures: creates a PlaceLinkedList of the top 3 results. It will have HTMLComponents for one attraction, one restaurant, and one place of worship.
	 * @return returns a PlaceLinkedList containing the HTMLComponents to display.
	 */
	public PlaceLinkedList getResults(){
		PlaceLinkedList resultsList = new PlaceLinkedList();
		attractionLL.sort();
		resultsList.addAtBeginning(attractionLL.getHTMLAtIndex(0), 1);
		restaurantLL.sort();
		resultsList.addAtBeginning(restaurantLL.getHTMLAtIndex(0), 1);
		placeofworshipLL.sort();
		resultsList.addAtBeginning(placeofworshipLL.getHTMLAtIndex(0), 1);
		
		// second row of results
		

		return resultsList;
	}
	
	
}
