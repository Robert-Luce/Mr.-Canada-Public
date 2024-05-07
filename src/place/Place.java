package place;

import java.util.ArrayList;

public class Place{
	private String name;
	private String city;

	protected int score = 0;
	protected int multiplier;
	private ArrayList<String> placeAttributes;

	/**
	 * ensures: constructs the Place with the correct information
	 * 
	 * @param name         - the name of the Place
	 * @param city         - the city that the Place is in
	 * @param address      - the address of the Place
	 * @param isAccessible - whether or not the Place has handicapped accessibility
	 */
	public Place(String name, String city, ArrayList<String> PlaceAttributes) {
		this.name = name;
		this.city = city;
		this.placeAttributes = PlaceAttributes;
	}

	/**
	 * ensures: allows other classes to access this Place's name
	 * 
	 * @return - the name of this Place
	 */
	public String getName() {
		return name;
	}

	/**
	 * ensures: allows other classes to access the city that this Place is in
	 * 
	 * @return - the city that this Place resides in
	 */
	public String getCity() {
		return city;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void checkCriteria(String criteriaToCheck) {
		if(criteriaToCheck.equals(city)) {
			score = score + 10;
		} else {
			for(String a : placeAttributes) {
				if(criteriaToCheck.equals(a)) {
					score = score + 1; 
				}
			}
		}
		
	}

}
