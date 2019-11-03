package edu.ntu.scse.factor;

public enum MovieType implements TicketFactor {
	MovieType_REGULAR("Regular"), MovieType_3D("3D");

	private final String desc;

	MovieType(String desc) {
		this.desc = desc;
	}

	/**
	 * Get description of MovieType
	 * 
	 * @return description of MovieType
	 */
	@Override
	public String toString() {
		return desc;
	}
}
