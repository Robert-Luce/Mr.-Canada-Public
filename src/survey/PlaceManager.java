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


			if(placeInfo.get(0).equals("Restaurant")) {

				handleRestaurant(placeInfo, placeName);

			}



		}

	}

	private void handleRestaurant(ArrayList<String> placeInfo, String placeName) {
		boolean isAccessible = false;
		boolean isVegetarian = false;
		boolean isHalal = false;
		boolean isVegan = false;

		if(placeInfo.get(2).equals("Accessible")) {
			isAccessible = true;
		}
		if(placeInfo.get(4).equals("Vegetarian")) {
			isVegetarian = true;
		}
		if(placeInfo.get(5).equals("Halal")) {
			isHalal = true;
		}
		if(placeInfo.get(6).equals("Vegan")) {
			isVegan = true;
		}

		Restaurant r = new Restaurant(placeName, placeInfo.get(1), isAccessible, placeInfo.get(3), isVegetarian, isHalal, isVegan);

		places.add(r);
	}
}
