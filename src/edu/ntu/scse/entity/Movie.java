package edu.ntu.scse.entity;

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
	private boolean isBlockbuster;

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
	public Movie(int movieId, String title, String synopsis, String director, String cast, boolean isBlockbuster,
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

	public boolean isBlockbuster() {
		return isBlockbuster;
	}

	public void setBlockbuster(boolean isBlockbuster) {
		this.isBlockbuster = isBlockbuster;
	}

	public float getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(float overallRating) {
		//TODO add setOverallRating logic
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
