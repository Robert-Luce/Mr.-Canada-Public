package place;

import java.util.ArrayList;

public class Place{
	private String name;
	private String city;
	private boolean isAccessible;

	private ArrayList<Place> walkableFrom = new ArrayList<>();
	private ArrayList<Place> bikeableFrom = new ArrayList<>();
	private ArrayList<Place> driveableFrom = new ArrayList<>();
	protected int score = 0;
	protected int multiplier;

	/**
	 * ensures: constructs the Place with the correct information
	 * 
	 * @param name         - the name of the Place
	 * @param city         - the city that the Place is in
	 * @param address      - the address of the Place
	 * @param isAccessible - whether or not the Place has handicapped accessibility
	 */
	public Place(String name, String city, boolean isAccessible) {
		this.name = name;
		this.city = city;
		this.isAccessible = isAccessible;
	}

	/**
	 * ensures: passes along another Place that is close enough to walk to.
	 * 
	 * @param p - the Place to be added to this Place's lists.
	 */
	public void addWalkablePlace(Place p) {
		walkableFrom.add(p);
		bikeableFrom.add(p);
		driveableFrom.add(p);
	}

	/**
	 * ensures: passes along another Place that is close enough to bike to.
	 * 
	 * @param p - the Place to be added to this Place's lists.
	 */
	public void addBikeablePlace(Place p) {
		bikeableFrom.add(p);
		driveableFrom.add(p);
	}

	/**
	 * ensures: passes along another Place that is close enough to drive to (within
	 * the same city).
	 * 
	 * @param p - the Place to be added to this Place's list.
	 */
	public void addDriveablePlace(Place p) {
		driveableFrom.add(p);
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

	/**
	 * ensures: allows other classes to access this Place's accessibility
	 * information
	 * 
	 * @return - whether or not this Place has mobility accessibility
	 */
	public boolean getAccessibility() {
		return isAccessible;
	}
	
	public int getScore() {
		return score;
	}

	public void checkCriteria(String criteria, boolean isSelected) {
		if(!isSelected) {
			multiplier = -1;
		} else {
			multiplier = 1;
		}
		
		if(criteria.equals(this.getCity())) {
			this.score = this.score + (10 * multiplier);
		} else if(criteria.equals("Mobility (Ramps, Elevators)") && isAccessible) {
			this.score = this.score + (1 * multiplier);
		}
		
	}

}
