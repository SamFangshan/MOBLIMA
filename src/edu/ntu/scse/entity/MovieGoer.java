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
	 * Constructor of MovieGoer with all attributes and default constructor from super class
	 *
	 * @param movieGoerId
	 * @param age
	 * @param bookings
	 * @param reviews
	 */
	public MovieGoer(String email, int movieGoerId, int age, ArrayList<Booking> bookings, ArrayList<Review> reviews) {
		super();
		this.setEmail(email);
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		MovieGoer movieGoer = (MovieGoer) o;
		return getMovieGoerId() == movieGoer.getMovieGoerId() &&
				getAge() == movieGoer.getAge() &&
				getBookings().equals(movieGoer.getBookings()) &&
				getReviews().equals(movieGoer.getReviews());
	}

	@Override
	public String toString() {
		return "MovieGoer|" + email + "|" + movieGoerId + "|" + age + "|" + bookingsToString(bookings) + "|" + reviewsToString(reviews);
	}

	private String bookingsToString(ArrayList<Booking> bookings){
		String r = "";
		if(bookings.size() != 0){
			r += bookings.get(0).getTID();
			for(int i=1; i<bookings.size(); i++){
				r += ", ";
				r += bookings.get(i).getTID();
			}
		}
		return r;
	}
	private String reviewsToString(ArrayList<Review> reviews){
		String r = "";
		if(reviews.size() != 0){
			r += reviews.get(0).getReviewId();
			for(int i=1;i<reviews.size();i++){
				r += ", ";
				r += reviews.get(i).getReviewId();
			}
		}
		return r;
	}
}
