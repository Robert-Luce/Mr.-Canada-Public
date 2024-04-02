import java.util.ArrayList;

public class Location {
	private String name;
	private String city;
	private String address;
	
	private ArrayList<Location> walkablePlaces = new ArrayList<>();
	private ArrayList<Location> bikeablePlaces = new ArrayList<>();
	private ArrayList<Location> driveablePlaces = new ArrayList<>();
	
	/**
	 * ensures: constructs the Location with the correct information
	 * @param name - the name of the location
	 * @param city - the city that the location is in
	 * @param address - the address of the location
	 */
	public Location(String name, String city, String address) {
		this.name = name;
		this.city = city;
		this.address = address;
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
	
	

}
