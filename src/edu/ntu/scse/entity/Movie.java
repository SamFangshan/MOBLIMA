package edu.ntu.scse.entity;

/**
 * Represents individual Movie
 * 
 * @author Kailing
 *
 */
public class Movie {
	private int movieId;
	private String title;
	private String synopsis;
	private String cast;
	private float overallRating;

	private MovieRating movieRating;
	private MovieStatus movieStatus;
	private MovieType movieType;

}
