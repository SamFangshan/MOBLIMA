package edu.ntu.scse.control;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieGoer;
import edu.ntu.scse.entity.Review;

import java.util.ArrayList;

/**
 *
 * @author suhuangyuan
 *
 */
public class ReviewManager {

	private ArrayList<Review> reviews;

	/**
	 * Create a new reivew and add it to the list
	 * 
	 * @param movie
	 * @param moviegoer
	 * @param reviewText
	 * @param rating
	 * @return
	 */
	public Review createReview(Movie movie, MovieGoer moviegoer, String reviewText, int rating) {
		// Simply increment the id by 1 each time a new ID is added.
		int id = reviews.size() + 1;
		int movieId = movie.getMovieId();

		Review newReview = new Review(id, reviewText, rating, movieId, moviegoer);
		//Add new review to the list
		reviews.add(newReview);
		return newReview;
	}
}
