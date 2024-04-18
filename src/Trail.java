
public class Trail extends Attraction {

	private boolean canUseBike;

	public Trail(String name, String city, boolean isAccessible, boolean canUseBike, String priceLevel) {
		super(name, city, isAccessible, priceLevel);
		this.canUseBike = canUseBike;
	}

	public boolean isBikingAllowed() {
		return canUseBike;
	}

}
