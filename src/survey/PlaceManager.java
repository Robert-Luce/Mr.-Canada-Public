package survey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import components.HTMLComponent;
import place.*;

public class PlaceManager {
	private ArrayList<Place> places;
	private PlaceLinkedList rLL;
	private PlaceLinkedList aLL;
	private PlaceLinkedList powLL;
	private JPanel viewport;
	private String language;
	
	public PlaceManager(ArrayList<Place> places, JPanel viewport, String language) {
		this.places = places;
		this.viewport = viewport;
		this.language = language;
	}
	
	
	public void assessLocations() {
		for(Place p: places) {
			p.setScore(1);
		}
		ArrayList<String> criteria = new ArrayList<String>();
		try {
			ArrayList<String> cs = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Criteria Test.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			criteria.addAll(cs);
			System.out.println("Criteria:" + criteria);
		} catch (Exception e) {
			System.out.println("Please select a criteria");
		}
		
		rLL = new PlaceLinkedList();
		aLL = new PlaceLinkedList();
		powLL = new PlaceLinkedList();
		for(Place p : places) {
			for(String c : criteria) {
				System.out.println("criteria:" + c);
				p.checkCriteria(c);
			}
			if(p.getName().contains("Restaurant")) {
				rLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				rLL.sort();
			} else if (p.getName().contains("Attraction")) {
				aLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				aLL.sort();
			} else if (p.getName().contains("PlaceOfWorship")) {
				powLL.addAtBeginning(generateHTML(p.getName(), viewport), p.getScore());
				powLL.sort();
			}
			
		}
	}
	
	public void generatePlaces() {
		ArrayList<String> placeNames = new ArrayList<String>();
		try {
			ArrayList<String> ps = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Separate Locations " + language + "\\PlacesList.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			placeNames.addAll(ps);
		} catch (Exception e) {
			System.out.println("Please add text to MrCanadaData\\\\Separate Locations English\\\\PlacesList.txt");
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
				System.out.println("Please add text to MrCanadaData\\\\Separate Locations English\\\\" + placeName + "\\\\" + placeName +".txt" );
			}

			ArrayList<String> truncInfo = new ArrayList<String>();

			for(int i = 1; i < placeInfo.size()-1; i++) {
				truncInfo.add(placeInfo.get(i));
			}
			places.add(new Place(placeName, placeInfo.get(1), truncInfo));
		}
		
		

	}
	
	public HTMLComponent generateHTML(String placeName, JPanel panel) {
		HTMLComponent h = new HTMLComponent(placeName, "Separate Locations " + language + "\\" + placeName, panel);
		return h;
	}
	
	public PlaceLinkedList getResults(){
		PlaceLinkedList resultsList = new PlaceLinkedList();
		aLL.sort();
		resultsList.addAtBeginning(aLL.getHTMLAtIndex(0), 1);
		rLL.sort();
		resultsList.addAtBeginning(rLL.getHTMLAtIndex(0), 1);
		powLL.sort();
		resultsList.addAtBeginning(powLL.getHTMLAtIndex(0), 1);
		System.out.println(resultsList.toString());
		return resultsList;
	}
	
	
}
