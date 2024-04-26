package place;
public class Trail extends Attraction {

	private boolean canUseBike;

	/**
	 * ensures: default constructor for the Attraction class
	 * @param name				- String - the name of trail
	 * @param city				- String - the city where trail is located
	 * @param isAccessible		- boolean - whether or not it is wheel chair accessible
	 * @param canUseBike		- boolean - whether or not a bike can be used on the trail
	 * @param priceLevel		- String - how expensive it is to go on this trail
	 */
	public Trail(String name, String city, boolean isAccessible, boolean canUseBike, String priceLevel) {
		super(name, city, isAccessible, priceLevel);
		this.canUseBike = canUseBike;
	}

	/**
	 * ensures: information of if a bike can be used on a trail
	 * @return canUseBike
	 */
	public boolean isBikingAllowed() {
		return canUseBike;
	}

}
