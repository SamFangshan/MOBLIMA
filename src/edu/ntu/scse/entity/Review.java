package edu.ntu.scse.entity;

import java.util.Objects;

/**
 * 
 * @author suhuangyuan
 *
 */
public class Review {

	/**
	 * ID of a review
	 */
	private int reviewId;
	/**
	 * review text
	 */
	private String reviewText;
	/**
	 * rating 
	 */
	private int rating;
	/**
	 * movieGoer(author of the movie)
	 */
	private MovieGoer movieGoer;
	
	/**
	 * Constructor of a movie
	 * @param reviewId
	 * @param reviewText
	 * @param rating
	 * @param movieGoer
	 */
	public Review(int reviewId, String reviewText, int rating, MovieGoer movieGoer) {
		super();
		this.reviewId = reviewId;
		this.reviewText = reviewText;
		this.rating = rating;
		this.movieGoer = movieGoer;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public MovieGoer getMovieGoer() {
		return movieGoer;
	}
	public void setMovieGoer(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Review review = (Review) o;
		return getReviewId() == review.getReviewId() &&
				getRating() == review.getRating() &&
				getReviewText().equals(review.getReviewText()) &&
				getMovieGoer().equals(review.getMovieGoer());
	}
}
