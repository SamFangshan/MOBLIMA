package edu.ntu.scse.entity;

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
	 * Movie ID this review is for
	 */
	private int movieId;
	/**
	 * moviegoer(author of the review)
	 */
	private MovieGoer moviegoer;

	/**
	 * Constructor of a Review
	 *
	 * @param reviewId
	 * @param reviewText
	 * @param rating
	 * @param movieId
	 * @param moviegoer
	 */
	public Review(int reviewId, String reviewText, int rating,int movieId, MovieGoer moviegoer) {
		super();
		this.reviewId = reviewId;
		this.reviewText = reviewText;
		this.rating = rating;
		this.movieId = movieId;
		this.moviegoer = moviegoer;
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

	public int getMovieId() {return movieId;}
	public void setMovieId(int movieId) {this.movieId = movieId;}

	public MovieGoer getMovieGoer() {
		return moviegoer;
	}

	public void setMovieGoer(MovieGoer moviegoer) {
		this.moviegoer = moviegoer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Review review = (Review) o;
		return getReviewId() == review.getReviewId() &&
				getRating() == review.getRating() &&
				getReviewText().equals(review.getReviewText()) &&
				getMovieId() == review.getMovieId()&&
				getMovieGoer().equals(review.getMovieGoer());
	}
}
