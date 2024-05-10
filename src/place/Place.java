package place;

import java.util.ArrayList;

public class Place{
	private String name;
	private String city;

	protected int score;
	protected int multiplier;
	private ArrayList<String> criteriaList;

	/**
	 * ensures: constructs the Place with the correct information
	 * 
	 * @param name         - the name of the Place
	 * @param city         - the city that the Place is in
	 * @param address      - the address of the Place
	 * @param isAccessible - whether or not the Place has handicapped accessibility
	 */
	public Place(String name, String city, ArrayList<String> criteriaList) {
		this.name = name;
		this.city = city;
		this.criteriaList = criteriaList;
		this.score = 0;
	}

	/**
	 * ensures: allows other classes to access this Place's name
	 * 
	 * @return - the name of this Place
	 */
	public String getName() {
		return name;
	}

	/**
	 * ensures: allows other classes to access the city that this Place is in
	 * 
	 * @return - the city that this Place resides in
	 */
	public String getCity() {
		return city;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void checkCriteria(String criteria) {
		System.out.println();
		System.out.println("score for " + name + " before checkCriteria:" + score);
		System.out.println(city);
		System.out.println(criteria);
		if(criteria.equals(city)) {
			score = score + 10;
			System.out.println("added 10 to " + name + "'s score for " + city);
		} else {
			for(String c : criteriaList) {
				if(criteria.equals(c)) {
					score = score + 1; 
					System.out.println("added 1 to " + name + "'s score for " + c);
				}
			}
		}
		System.out.println("score for " + name + "after checkCriteria:" + score);
		
	}

}
