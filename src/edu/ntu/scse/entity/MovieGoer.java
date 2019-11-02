package edu.ntu.scse.entity;

import java.util.ArrayList;

/**
 * 
 * @author suhuangyuan
 *
 */
public class MovieGoer extends Person {

	/**
	 * ID of movie goer
	 */
	private int movieGoerId;
	/**
	 * age of movie goer, used in calculating ticket price
	 */
	private int age;
	/**
	 * bookings history of movieGoer
	 */
	private ArrayList<Booking> bookings;
	/**
	 * reviews history of movieGoer
	 */
	private ArrayList<Review> reviews;

	/**
	 * Constructor of MovieGoer with all attributes and default constructor from
	 * super class
	 * 
	 * @param movieGoerId
	 * @param age
	 * @param bookings
	 * @param reviews
	 */
	public MovieGoer(int movieGoerId, int age, ArrayList<Booking> bookings, ArrayList<Review> reviews) {
		super();
		this.movieGoerId = movieGoerId;
		this.age = age;
		this.bookings = bookings;
		this.reviews = reviews;
	}

	public int getMovieGoerId() {
		return movieGoerId;
	}

	public void setMovieGoerId(int movieGoerId) {
		this.movieGoerId = movieGoerId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}

		if (this.getLastName().equals(((Person) obj).getLastName())
				&& this.getFirstName().equals(((Person) obj).getFirstName())
				&& this.getEmail().equals(((Person) obj).getEmail())
				&& this.getPhoneNo().equals(((Person) obj).getPhoneNo())
				&& this.getMovieGoerId() == ((MovieGoer) obj).getMovieGoerId()
				&& this.getAge() == ((MovieGoer) obj).getAge() && this.getBookings() == ((MovieGoer) obj).getBookings()
				&& this.getReviews().equals(((MovieGoer) obj).getReviews())) {
			return true;
		}

		return false;
	}
}
