package place;

public abstract class Attraction extends Place {
	protected String priceLevel;

	public Attraction(String name, String city, boolean isAccessible, String priceLevel) {
		super(name, city, isAccessible);
		this.priceLevel = priceLevel;
	}
	
	public void checkCriteria(String criteria, boolean isChecked) {
		super.checkCriteria(criteria, isChecked);
		if(criteria.equals(priceLevel)) {
		  score = score + (1*multiplier);
		}
	}

}
