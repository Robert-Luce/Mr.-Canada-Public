
public class PlaceOfWorship extends Place {

	private String religion;

	/**
	 * ensures: default constructor for the Place of Worship class
	 * 
	 * @param name         - String - the name of the Place of Worship
	 * @param city         - String - the city that the Place of Worship is in
	 * @param isAccessible - boolean - whether or not it is wheel chair accessible
	 * @param religion     - String - the religion that is practiced here
	 */
	public PlaceOfWorship(String name, String city, boolean isAccessible, String religion) {
		super(name, city, isAccessible);
		this.religion = religion;
	}

	/**
	 * ensures: religion of a place of worship can be accessed
	 * @return religion
	 */
	public String getReligion() {
		return religion;
	}

}
