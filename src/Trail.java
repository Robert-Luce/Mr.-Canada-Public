
public class Trail extends Excursion {

	private boolean canUseBike;

	public Trail(String name, String city, boolean isAccessible, boolean canUseBike) {
		super(name, city, isAccessible);
		this.canUseBike = canUseBike;
	}
	
	public boolean isBikingAllowed() {
		return canUseBike;
	}

}
