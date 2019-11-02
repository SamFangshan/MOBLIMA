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
	 * moviegoer(author of the movie)
	 */
	private Moviegoer moviegoer;

	/**
	 * Constructor of a movie
	 * 
	 * @param reviewId
	 * @param reviewText
	 * @param rating
	 * @param moviegoer
	 */
	public Review(int reviewId, String reviewText, int rating, Moviegoer moviegoer) {
		super();
		this.reviewId = reviewId;
		this.reviewText = reviewText;
		this.rating = rating;
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

	public Moviegoer getMovieGoer() {
		return moviegoer;
	}

	public void setMovieGoer(Moviegoer moviegoer) {
		this.moviegoer = moviegoer;
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}

		if (this.getReviewId() == ((Review) obj).getReviewId()
				&& this.getReviewText().equals(((Review) obj).getReviewText())
				&& this.getRating() == ((Review) obj).getRating()
				&& this.getMovieGoer().equals(((Review) obj).getMovieGoer())) {
			return true;
		}

		return false;
	}

}
