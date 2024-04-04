
public class Restaurant extends Place {

	String cuisineType;
	boolean vegetarian;
	boolean halal;
	
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
