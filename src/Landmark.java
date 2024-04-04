
public class Landmark extends Excursion {

	private boolean isManmade;

	public Landmark(String name, String city, boolean isAccessible, boolean isManmade) {
		super(name, city, isAccessible);
		this.isManmade = isManmade;
	}
	
	public boolean isManmade() {
		return isManmade;
	}

}
