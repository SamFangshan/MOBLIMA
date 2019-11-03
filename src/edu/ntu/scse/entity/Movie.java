package edu.ntu.scse.entity;

import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

/**
 * Represents individual Movie
 * 
 * @author Kailing
 *
 */
public class Movie {
	/**
	 * movieId of Movie
	 */
	private int movieId;

	/**
	 * movie title of Movie
	 */
	private String title;

	/**
	 * movie abstract of Movie
	 */
	private String synopsis;

	/**
	 * director of Movie
	 */
	private String director;

	/**
	 * concatenate cast(s) of Movie, with delimiter = ,
	 */
	private String cast;

	/**
	 * is Movie a Blockbuster<br>
	 * If Movie is a Blockbuster, isBlockbuster = true<br>
	 * If Movie is not a Blockbuster, isBlockbuster = false
	 */
	private Blockbuster isBlockbuster;

	/**
	 * overall reviewer rating (1 – 5 [best]) of Movie
	 */
	private float overallRating;

	/**
	 * movie rating of Movie
	 */
	private MovieRating movieRating;

	/**
	 * movie status of Movie
	 */
	private MovieStatus movieStatus;

	/**
	 * type of Movie
	 */
	private MovieType movieType;

	/**
	 * Default constructor of Movie
	 */
	public Movie() {

	}

	/**
	 * Constructor with all attributes of Movie
	 * 
	 * @param movieId
	 * @param title
	 * @param synopsis
	 * @param director
	 * @param cast
	 * @param isBlockbuster
	 * @param overallRating
	 * @param movieRating
	 * @param movieStatus
	 * @param movieType
	 */
	public Movie(int movieId, String title, String synopsis, String director, String cast, Blockbuster isBlockbuster,
			float overallRating, MovieRating movieRating, MovieStatus movieStatus, MovieType movieType) {
		this.movieId = movieId;
		this.title = title;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.isBlockbuster = isBlockbuster;
		this.overallRating = overallRating;
		this.movieRating = movieRating;
		this.movieStatus = movieStatus;
		this.movieType = movieType;
	}

	@Override
	public String toString() {
		String movieIdString = "Movie ID: " + movieId;
		String titleString = "Title: " + title;
		String synopsisString = "Synopsis: " + synopsis;
		String directorString = "Director: " + director;
		String castString = "Cast: " + cast;
		String isBlockbusterString = "Blockbuster: " + isBlockbuster;
		String overallRatingString = "Overall Rating: " + overallRating;
		String movieRatingString = "Movie Rating: " + movieRating;
		String movieStatusString = "Movie Status: " + movieStatus.toString();
		String movieTypeString = "Movie Type: " + movieType.toString();

		return movieIdString + "\n" + titleString + "\n" + synopsisString + "\n" + directorString
				+ "\n" + castString + "\n" + isBlockbusterString + "\n" + overallRatingString + "\n"
				+ movieRatingString + "\n" + movieStatusString + "\n" + movieTypeString;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Movie movie = (Movie) o;
		return getMovieId() == movie.getMovieId() &&
				isBlockbuster() == movie.isBlockbuster() &&
				Float.compare(movie.getOverallRating(), getOverallRating()) == 0 &&
				getTitle().equals(movie.getTitle()) &&
				getSynopsis().equals(movie.getSynopsis()) &&
				getDirector().equals(movie.getDirector()) &&
				getCast().equals(movie.getCast()) &&
				getMovieRating() == movie.getMovieRating() &&
				getMovieStatus() == movie.getMovieStatus() &&
				getMovieType() == movie.getMovieType();
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public Blockbuster isBlockbuster() {
		return isBlockbuster;
	}

	public void setBlockbuster(Blockbuster isBlockbuster) {
		this.isBlockbuster = isBlockbuster;
	}

	public float getOverallRating() {
		// TODO add getOverallRating logic
		return overallRating;
	}

	public void setOverallRating(float overallRating) {
		// TODO add setOverallRating logic
		this.overallRating = overallRating;
	}

	public MovieRating getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}

	public MovieStatus getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(MovieStatus movieStatus) {
		this.movieStatus = movieStatus;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

}
