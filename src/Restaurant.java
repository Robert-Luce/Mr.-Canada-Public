
public class Restaurant extends Place {

	private String cuisineType;
	private boolean vegetarian;
	private boolean halal;
	
	/**
	 * ensures: default constructor for the Restaurant class that extends its superclass constructor
	 * @param name - String - the name of the Restaurant
	 * @param city - String - the city that the Restaurant is in
	 * @param isAccessible - boolean - whether or not it is wheelchair accessible
	 * @param cuisine - String - the type of food that this Restaurant makes
	 * @param vegetarian - boolean - whether or not vegetarian food options are offered
	 * @param halal - boolean - whether the food is halal or haram
	 */
	public Restaurant(String name, String city, boolean isAccessible, String cuisine, boolean vegetarian, boolean halal) {
		super(name, city, isAccessible);
		this.cuisineType = cuisine;
		this.vegetarian = vegetarian;
		this.halal = halal;
	}
	
	
	public String getCuisine() {
		return cuisineType;
	}
	
	public boolean isVegetarian() {
		return vegetarian;
	}
	
	public boolean isHalal() {
		return halal;
	}

}
