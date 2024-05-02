package place;

public class Landmark extends Attraction {

	private String type;

	public Landmark(String name, String city, boolean isAccessible, String type, String priceLevel) {
		super(name, city, isAccessible, priceLevel);
		this.type = type;
	}
	
	public void checkCriteria(String criteria) {
		super.checkCriteria(criteria);
		if(criteria.equals(type)) {
			score = score + (2 * multiplier);
		}
	}
}
