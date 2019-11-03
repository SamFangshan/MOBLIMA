package edu.ntu.scse.entity;

/**
 * Represents status of Movie
 * 
 * @author Kailing
 *
 */
public enum MovieStatus {
	COMING_SOON("Coming Soon"), PREVIEW("Preview"), NOW_SHOWING("Now Showing"), END_OF_SHOWING("End of Showing");

	private final String desc;

	MovieStatus(String desc) {
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
