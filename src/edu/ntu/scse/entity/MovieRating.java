package edu.ntu.scse.entity;

/**
 * Represents different types of movie rating
 * 
 * @author Kailing
 *
 */
public enum MovieRating {
	G("General"), PG("Parental Guidance"), PG13("Parental Guidance 13"), NC16("No Children Under 16"),
	M18("Mature 18"), R21("Restricted 21");

	private final String desc;

	MovieRating(){
		this.desc = null;
	}
	MovieRating(String desc) {
		this.desc = desc;
	}

	/**
	 * Get description of MovieStatus
	 * 
	 * @return description of MovieStatus
	 */
	@Override
	public String toString() {
		return desc;
	}
}
