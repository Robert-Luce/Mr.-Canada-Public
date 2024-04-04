import java.util.ArrayList;

public class Place {
	private String name;
	private String city;
	private boolean isAccessible;
	
	private ArrayList<Place> walkableFrom = new ArrayList<>();
	private ArrayList<Place> bikeableFrom = new ArrayList<>();
	private ArrayList<Place> driveableFrom = new ArrayList<>();

	
	/**
	 * ensures: constructs the Location with the correct information
	 * @param name - the name of the Location
	 * @param city - the city that the Location is in
	 * @param address - the address of the Location
	 * @param isAccessible - whether or not the Location has handicapped accessibility
	 */
	public Place(String name, String city, boolean isAccessible) {
		this.name = name;
		this.city = city;
		this.isAccessible = isAccessible;
	}
	
	/**
	 * ensures: passes along another Location that is close enough to walk to.
	 * @param l - the location to be added to this Location's lists.
	 */
	public void addWalkableLocation(Place l) {
		walkableFrom.add(l);
		bikeableFrom.add(l);
		driveableFrom.add(l);
	}
	
	/**
	 * ensures: passes along another Location that is close enough to bike to.
	 * @param l - the location to be added to this Location's lists.
	 */
	public void addBikeableLocation(Place l) {
		bikeableFrom.add(l);
		driveableFrom.add(l);
	}
	
	/**
	 * ensures: passes along another Location that is close enough to drive to (within the same city).
	 * @param l - the location to be added to this Location's list.
	 */
	public void addDriveableLocation(Place l) {
		driveableFrom.add(l);
	}
	
	/**
	 * ensures: allows other classes to access this Location's name
	 * @return - the name of this Location
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ensures: allows other classes to access the city that this Location is in
	 * @return - the city that this Location resides in
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * ensures: allows other classes to access this Location's accessibility information
	 * @return - whether or not this Location has mobility accessibility
	 */
	public boolean getAccessibility() {
		return isAccessible;
	}

}