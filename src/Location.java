import java.util.ArrayList;

public class Location {
	private String name;
	private String city;
	private String address;
	private boolean isAccessible;
	
	private ArrayList<Location> walkablePlaces = new ArrayList<>();
	private ArrayList<Location> bikeablePlaces = new ArrayList<>();
	private ArrayList<Location> driveablePlaces = new ArrayList<>();

	
	/**
	 * ensures: constructs the Location with the correct information
	 * @param name - the name of the Location
	 * @param city - the city that the Location is in
	 * @param address - the address of the Location
	 * @param isAccessible - whether or not the Location has handicapped accessibility
	 */
	public Location(String name, String city, String address, boolean isAccessible) {
		this.name = name;
		this.city = city;
		this.address = address;
		this.isAccessible = isAccessible;
	}
	
	/**
	 * ensures: passes along another Location that is close enough to walk to.
	 * @param l - the location to be added to this Location's lists.
	 */
	public void addWalkableLocation(Location l) {
		walkablePlaces.add(l);
		bikeablePlaces.add(l);
		driveablePlaces.add(l);
	}
	
	/**
	 * ensures: passes along another Location that is close enough to bike to.
	 * @param l - the location to be added to this Location's lists.
	 */
	public void addBikeableLocation(Location l) {
		bikeablePlaces.add(l);
		driveablePlaces.add(l);
	}
	
	/**
	 * ensures: passes along another Location that is close enough to drive to (within the same city).
	 * @param l - the location to be added to this Location's list.
	 */
	public void addDriveableLocation(Location l) {
		driveablePlaces.add(l);
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
	 * ensures: allows other classes to access this Location's address
	 * @return - the address of this Location
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * ensures: allows other classes to access this Location's accessibility information
	 * @return - whether or not this Location has mobility accessibility
	 */
	public boolean getAccessibility() {
		return isAccessible;
	}

}
