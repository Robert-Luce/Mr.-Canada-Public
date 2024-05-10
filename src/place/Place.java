package place;

import java.util.ArrayList;
/**
 * @author walindqg, leonemm
 */
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
	
	/**
	 *  ensures: allows other classes to access this Place's score
	 * 
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * ensures: allows other classes to set this Place's score
	 * @param score - the score of the Place, based on how many criteria it meets
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * ensures: checks which criteria this Place meets and adds points accordingly
	 * @param criteria - criteria to check whether the Place meets
	 */
	public void checkCriteria(String criteria) {
		if(criteria.equals(city)) {
			score = score + 10;
		} else {
			for(String c : criteriaList) {
				if(criteria.equals(c)) {
					score = score + 1; 
				}
			}
		}
		
	}

}
