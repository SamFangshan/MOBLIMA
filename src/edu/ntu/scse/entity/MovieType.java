package edu.ntu.scse.entity;

public enum MovieType {
	MovieType_DIGITAL("Dolby Digital", 11), MovieType_3D("3D", 15);

	private final String desc;
	private final double price;

	MovieType(String desc, double price) {
		this.desc = desc;
		this.price = price;
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

	/**
	 * Get price of MovieType
	 * 
	 * @return price of MovieType
	 */
	public double getPrice() {
		return price;
	}

}
