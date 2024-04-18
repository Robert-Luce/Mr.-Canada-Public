
public class Landmark extends Attraction {

	private boolean isManmade;

	public Landmark(String name, String city, boolean isAccessible, boolean isManmade, String priceLevel) {
		super(name, city, isAccessible, priceLevel);
		this.isManmade = isManmade;
	}

	public boolean isManmade() {
		return isManmade;
	}

}
