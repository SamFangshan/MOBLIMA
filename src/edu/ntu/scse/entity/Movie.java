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

	/**
	 * prints all attributes of Movie
	 */
	public void print() {
		System.out.println("Id: " + movieId + " | Movie Title: " + title);
		System.out.println("Movie Rating: " + movieRating.name() + " (" + movieRating.toString() + ")"
				+ " | Movie Status: " + movieStatus.toString() + " | Movie Type: " + movieType.toString());
		System.out.println("Synopsis: " + synopsis);
		System.out.println("Director: " + director);
		System.out.println("Cast: " + cast);
		if (isBlockbuster) {
			System.out.println("Blockbuster: Yes");
		} else {
			System.out.println("Blockbuster: No");
		}
		System.out.println("Overall Rating: " + getOverallRating());
	}

	/**
	 * returns a String of a Movie, includes all of its attributes, separated by
	 * deliminiter ='|'
	 */
	@Override
	public String toString() {
		String movieIdString = "Movie ID: " + movieId;
		String titleString = "Title: " + title;
		String synopsisString = "Synopsis: " + synopsis;
		String directorString = "Director: " + director;
		String castString = "Cast: " + cast;
		String isBlockbusterString = "Blockbuster: " + ((isBlockbuster) ? "Yes" : "No");
		String overallRatingString = "Overall Rating: " + overallRating;
		String movieRatingString = "Movie Rating: " + movieRating;
		String movieStatusString = "Movie Status: " + movieStatus.toString();
		String movieTypeString = "Movie Type: " + movieType.toString() + "\nPrice: " + movieType.getPrice();

		return movieIdString + "\n" + titleString + "\n" + synopsisString + "\n" + directorString + "\n" + castString
				+ "\n" + isBlockbusterString + "\n" + overallRatingString + "\n" + movieRatingString + "\n"
				+ movieStatusString + "\n" + movieTypeString;
	}

	@Override
	public boolean equals(Object o) {
		if (!o.getClass().equals(this.getClass())) {
			return false;
		}
		Movie mv = (Movie) o;
		if (this.movieId == mv.getMovieId() && this.title.equals(mv.getTitle())
				&& this.synopsis.equals(mv.getSynopsis()) && this.director.equals(mv.getDirector())
				&& this.cast.equals(mv.getCast()) && this.isBlockbuster == mv.isBlockbuster()
				&& this.overallRating == mv.overallRating && this.movieRating.equals(mv.getMovieRating())
				&& this.movieStatus.equals(mv.getMovieStatus()) && this.movieType.equals(mv.getMovieType())) {
			return true;
		}
		return false;
	}

	/**
	 * Get movieId of Movie
	 * 
	 * @return movieId of Movie
	 */
	public int getMovieId() {
		return movieId;
	}

	/**
	 * Change movieId of Movie
	 * 
	 * @param movieId of Movie
	 */
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	/**
	 * Get title of Movie
	 * 
	 * @return title of Movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Change title of Movie
	 * 
	 * @param title of Movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get synopsis of Movie
	 * 
	 * @return synopsis of Movie
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Change synopsis of Movie
	 * 
	 * @param synopsis of Movie
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Get director of Movie
	 * 
	 * @return director of Movie
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Change director of Movie
	 * 
	 * @param director of Movie
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get cast of Movie
	 * 
	 * @return cast of Movie
	 */
	public String getCast() {
		return cast;
	}

	/**
	 * Change cast of Movie
	 * 
	 * @param cast of Movie
	 */
	public void setCast(String cast) {
		this.cast = cast;
	}

	/**
	 * Get isBlockbuster of Movie
	 * 
	 * @return true if it is a blockbuster<br>
	 *         false if it is not a blockbuster movie
	 */
	public boolean isBlockbuster() {
		return isBlockbuster;
	}

	/**
	 * Change isBlockbuster of Movie
	 * 
	 * @param isBlockbuster of Movie
	 */
	public void setBlockbuster(boolean isBlockbuster) {
		this.isBlockbuster = isBlockbuster;
	}

	/**
	 * Get overallRating of Movie<br>
	 * Overall reviewer rating will only be displayed if there are more than ONE
	 * individual rating, else “NA” is displayed
	 * 
	 * @return overallRating of Movie<br>
	 *         -1 if there are zero or one review
	 */
	public float getOverallRating() {
		// TODO add getOverallRating logic
		return overallRating;
	}

	/**
	 * Change overallRating of Movie<br>
	 * *
	 * 
	 * @param overallRating of Movie
	 */
	public void setOverallRating(float overallRating) {
		// TODO add setOverallRating logic
		// TODO overallRating = -1 when there are zero or one review
		this.overallRating = overallRating;
	}

	/**
	 * Get movieRating of movie
	 * 
	 * @return movieRating of movie
	 */
	public MovieRating getMovieRating() {
		return movieRating;
	}

	/**
	 * Change movieRating of Movie
	 * 
	 * @param movieRating of Movie
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}

	/**
	 * Get movieStatus of Movie
	 * 
	 * @return movieStatus of Movie
	 */
	public MovieStatus getMovieStatus() {
		return movieStatus;
	}

	/**
	 * Change movieStatus of Movie
	 * 
	 * @param movieStatus of Movie
	 */
	public void setMovieStatus(MovieStatus movieStatus) {
		this.movieStatus = movieStatus;
	}

	/**
	 * Get movieType of Movie
	 * 
	 * @return movieType of Movie
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/**
	 * Change movieType of Movie
	 * 
	 * @param movieType of Movie
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

}
