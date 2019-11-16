package edu.ntu.scse.entity;

import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	 * overall reviewer rating (1 � 5 [best]) of Movie
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
	 * Reviews of movie
	 */
	private ArrayList<Review> reviews;

	/**
	 * Date of end show
	 */
	private Calendar endOfShowDate;

	/**
	 * Default constructor of Movie
	 */
	public Movie() {

	}

	/**
	 * Constructor with all attributes of Movie
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
	 * @param reviews
	 * @param endOfShowDate
	 */
	public Movie(int movieId, String title, String synopsis, String director, String cast, Blockbuster isBlockbuster,
				 float overallRating, MovieRating movieRating, MovieStatus movieStatus, MovieType movieType,ArrayList<Review> reviews,
				 Calendar endOfShowDate) {
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
		this.reviews = reviews;
		this.endOfShowDate = endOfShowDate;
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
		System.out.println("Blockbuster: " + isBlockbuster);		
		String s = "";
		if(overallRating < 0) {
			s = "NA";
		}else {
			s = Float.toString(overallRating);
		}
		System.out.println("Overall Rating: " + s);
		System.out.println("End of showing Date" + endOfShowDate.getTime());
	}

	/**
	 * returns a String of a Movie, includes all of its attributes, separated by
	 * deliminiter ='|'
	 */
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = dateFormat.format(endOfShowDate.getTime());
		return "Movie|" + movieId + "|" + title + "|" + synopsis + "|" + director + "|" + cast + "|" + isBlockbuster
				+ "|" + overallRating + "|" + movieRating.name() + "|" + movieStatus.name() + "|" + movieType.name()+"|"+reviewsToString(reviews)+"|"+strDate;
	}

	private String reviewsToString(ArrayList<Review> reviews){
		String r = "";
		if(reviews.size() != 0){
			r += reviews.get(0).getReviewId();
			for(int i=1;i<reviews.size();i++){
				r += ",";
				r += reviews.get(i).getReviewId();
			}
		}
		return r;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Movie movie = (Movie) o;
		return getMovieId() == movie.getMovieId() &&
				Float.compare(movie.getOverallRating(), getOverallRating()) == 0 &&
				getTitle().equals(movie.getTitle()) &&
				getSynopsis().equals(movie.getSynopsis()) &&
				getDirector().equals(movie.getDirector()) &&
				getCast().equals(movie.getCast()) &&
				getIsBlockbuster() == movie.getIsBlockbuster() &&
				getMovieRating() == movie.getMovieRating() &&
				getMovieStatus() == movie.getMovieStatus() &&
				getMovieType() == movie.getMovieType() &&
				getReviews().equals(movie.getReviews()) &&
				getEndOfShowDate().equals(movie.getEndOfShowDate());
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
	public Blockbuster isBlockbuster() {
		return isBlockbuster;
	}

	/**
	 * Change isBlockbuster of Movie
	 *
	 * @param isBlockbuster of Movie
	 */
	public void setBlockbuster(Blockbuster isBlockbuster) {
		this.isBlockbuster = isBlockbuster;
	}

	/**
	 * Get overallRating of Movie<br>
	 * Overall reviewer rating will only be displayed if there are more than ONE
	 * individual rating, else �NA� is displayed
	 *
	 * @return overallRating of Movie<br>
	 *         -1 if there are zero or one review
	 */
	public float getOverallRating() {
		return overallRating;
	}

	/**
	 * Change overallRating of Movie<br>
	 * *
	 *
	 * @param overallRating of Movie
	 */
	public void setOverallRating(float overallRating) {
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

	/**
	 * Get BlockBuster of Movie
	 *
	 * @return BlockBuster of Movie
	 */
	public Blockbuster getIsBlockbuster() {
		return isBlockbuster;
	}

	/**
	 * Get endOfShowDate of Movie
	 *
	 * @return endOfShowDate of Movie
	 */
	public Calendar getEndOfShowDate() {
		return endOfShowDate;
	}

	/**
	 * Change BlockBuster of Movie
	 * @param isBlockbuster
	 */
	public void setIsBlockbuster(Blockbuster isBlockbuster) {
		this.isBlockbuster = isBlockbuster;
	}

	/**
	 * Change reviews of Movie
	 * @param reviews
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Change endOfShowDate of Movie
	 * @param endOfShowDate
	 */
	public void setEndOfShowDate(Calendar endOfShowDate) {
		this.endOfShowDate = endOfShowDate;
	}

	/**
	 * Get reviews of Movie
	 *
	 * @return reviews of Movie
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}
}
