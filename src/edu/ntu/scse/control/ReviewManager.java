package edu.ntu.scse.control;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieGoer;
import edu.ntu.scse.entity.Review;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.Moviegoer;
import edu.ntu.scse.entity.Review;

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
	public Review createReview(Movie movie, Moviegoer moviegoer, String reviewText, int rating) {
		// Simply increment the id by 1 each time a new ID is added.
		int id = reviews.size() + 1;

		Review newReview = new Review(id, reviewText, rating, moviegoer);
		// Add new review to the list
	public Review createReview(Movie movie, MovieGoer movieGoer, String reviewText, int rating) {
		// Simply increment the id by 1 each time a new ID is added.
		int id = reviews.size()+1;

		Review newReview = new Review(id,reviewText,rating,movieGoer);
		//Add new review to the list
		reviews.add(newReview);
		return newReview;
	}
}
