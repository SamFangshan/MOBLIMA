package edu.ntu.scse.entity;

/**
 * 
 * @author suhuangyuan
 *
 */
public class Staff extends Person {
	/**
	 * ID of staff
	 */
	private int cinemaStaffId;
	/**
	 * password of staff
	 */
	private String password;

	/**
	 * Constructor for Staff with all attributes and default constructor from super
	 * class
	 * 
	 * @param cinemaStaffId
	 * @param password
	 */
	public Staff(int cinemaStaffId, String password) {
		super();
		this.cinemaStaffId = cinemaStaffId;
		this.password = password;
	}

	public int getCinemaStaffId() {
		return cinemaStaffId;
	}

	public void setCinemaStaffId(int cinemaStaffId) {
		this.cinemaStaffId = cinemaStaffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Staff() {
//		super();
//	}
//	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Staff staff = (Staff) o;
		return getCinemaStaffId() == staff.getCinemaStaffId() &&
				getPassword().equals(staff.getPassword());
	}
}
