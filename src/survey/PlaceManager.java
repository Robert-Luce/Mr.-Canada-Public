package survey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import place.*;

public class PlaceManager {
	private ArrayList<Place> places;
	
	public PlaceManager(ArrayList<Place> places) {
		this.places = places;
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
		
		for(Place p : places) {
			for(String c : criteria) {
				p.checkCriteria(c);
			}
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
}
