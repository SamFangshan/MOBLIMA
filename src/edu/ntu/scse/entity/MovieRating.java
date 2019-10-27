package edu.ntu.scse.entity;

public enum MovieRating {
	G("General", 0), PG("Parental Guidance", 0), PG13("Parental Guidance 13", 13), NC16("No Children Under 16", 16),
	M18("Mature 18", 18), R21("Restricted 21", 21);

	private final String desc;
	private final int minAge;

	MovieRating(String desc, int minAge) {
		this.desc = desc;
		this.minAge = minAge;
	}

	/**
	 * Get description of MovieStatus
	 * 
	 * @return description of MovieStatus
	 */
	public String toString() {
		return desc;
	}

	/**
	 * Get minAge of MovieStatus
	 * 
	 * @return
	 */
	public int getMinAge() {
		return minAge;
	}
}
