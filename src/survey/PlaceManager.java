package survey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import components.HTMLComponent;
import place.*;

public class PlaceManager {
	private ArrayList<Place> places;
	private PlaceLinkedList placeLL;
	private JPanel panel;
	
	public PlaceManager(ArrayList<Place> places, JPanel panel) {
		this.places = places;
		this.panel = panel;
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
		} catch (Exception e) {
			System.out.println("Please select a criteria");
		}
		
		placeLL = new PlaceLinkedList();
		for(Place p : places) {
			for(String c : criteria) {
				p.checkCriteria(c);
			}
			placeLL.addAtEnd(generateHTML(p.getName(), panel), p.getScore());
		}
		
		
	}
	
	public void generatePlaces() {
		ArrayList<String> placeNames = new ArrayList<String>();
		try {
			ArrayList<String> ps = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\Places English\\PlacesList.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			placeNames.addAll(ps);
		} catch (Exception e) {
			System.out.println("Please add text to MrCanadaData\\\\SurveyQuestions\\\\PlacesList.txt");
		}

		for(String placeName : placeNames) {
			ArrayList<String> placeInfo = new ArrayList<String>();
			try {
				ArrayList<String> info = new ArrayList<String>(Arrays.asList(Files
						.readString(Path
								.of(Path.of("MrCanadaData\\Places English\\" + placeName + ".txt").toAbsolutePath().toString()))
						.split("\r\n")));
				placeInfo.addAll(info);
			} catch (Exception e) {
				System.out.println("Please add text to MrCanadaData\\\\Places English\\\\" + placeName + ".txt");
			}

			ArrayList<String> truncInfo = new ArrayList<String>();

			for(int i = 1; i < placeInfo.size()-1; i++) {
				truncInfo.add(placeInfo.get(i));
			}
			places.add(new Place(placeName, placeInfo.get(0), truncInfo));
		}
		
		

	}
	
	public HTMLComponent generateHTML(String placeName, JPanel panel) {
		HTMLComponent h = new HTMLComponent(placeName, "Separate Locations\\" + placeName, panel);
		return h;
	}
}
