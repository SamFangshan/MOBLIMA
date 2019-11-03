package edu.ntu.scse.entity;

import java.util.ArrayList;

/**
 * 
 * @author suhuangyuan
 *
 */
public class Moviegoer extends Person {

	/**
	 * ID of movie goer
	 */
	private int moviegoerId;
	/**
	 * age of movie goer, used in calculating ticket price
	 */
	private int age;
	/**
	 * bookings history of moviegoer
	 */
	private ArrayList<Booking> bookings;
	/**
	 * reviews history of moviegoer
	 */
	private ArrayList<Review> reviews;

	/**
	 * Constructor of Moviegoer with all attributes and default constructor from
	 * super class
	 * 
	 * @param moviegoerId
	 * @param age
	 * @param bookings
	 * @param reviews
	 */
	public Moviegoer(int moviegoerId, int age, ArrayList<Booking> bookings, ArrayList<Review> reviews) {
		super();
		this.moviegoerId = moviegoerId;
		this.age = age;
		this.bookings = bookings;
		this.reviews = reviews;
	}

	public int getMoviegoerId() {
		return moviegoerId;
	}

	public void setMoviegoerId(int moviegoerId) {
		this.moviegoerId = moviegoerId;
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
				&& this.getMoviegoerId() == ((Moviegoer) obj).getMoviegoerId()
				&& this.getAge() == ((Moviegoer) obj).getAge() && this.getBookings() == ((Moviegoer) obj).getBookings()
				&& this.getReviews().equals(((Moviegoer) obj).getReviews())) {
			return true;
		}

		return false;
	}
}
