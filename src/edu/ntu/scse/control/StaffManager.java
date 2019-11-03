package edu.ntu.scse.control;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieRating;
import edu.ntu.scse.entity.MovieStatus;
import edu.ntu.scse.entity.Staff;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

import java.util.ArrayList;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieRating;
import edu.ntu.scse.entity.MovieStatus;
import edu.ntu.scse.factor.MovieType;
import edu.ntu.scse.entity.Staff;
/**
 * 
 * @author suhuangyuan
 *
 */
public class StaffManager {

	/**
	 * Login a staff
	 * @param inputId
	 * @param inputPassword
	 * @param listStaffs
	 * @return
	 */
	public boolean login(int inputId,String inputPassword,ArrayList<Staff> listStaffs) {
		for(Staff staff:listStaffs) {
			if(staff.getCinemaStaffId() == inputId) {
				if(staff.getPassword() == inputPassword) {
					// If both ID and password match
					System.out.println("Welcome!");
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Add a new movie to the list
	 * @param listMovies
	 * @param movieId
	 * @param title
	 * @param synopsis
	 * @param director
	 * @param cast
	 * @param showingStatus
	 * @param movieType
	 * @param isBlockbuster
	 * @return
	 */

	public ArrayList<Movie> addMovieToList(ArrayList<Movie> listMovies, int movieId, String title, String synopsis, String director, String cast, MovieStatus mvStatus, MovieType movieType, Blockbuster isBlockbuster, MovieRating movieRating){
		//When a movie is first added, it has no review
		float overRating = 0;
		Movie newMovie = new Movie(movieId,title,synopsis,director,cast,isBlockbuster,overRating,movieRating,mvStatus,movieType);
		listMovies.add(newMovie);
		return listMovies;
	}
	
	/**
	 * Update some attribute of an existent movie that is already in the list
	 * @param listMovies
	 * @param movieId
	 * @param title
	 * @param synopsis
	 * @param director
	 * @param cast
	 * @param showingStatus
	 * @param movieType
	 * @param isBlockbuster
	 * @return
	 */
	public ArrayList<Movie> UpdateMovieListing(ArrayList<Movie> listMovies,int movieId, String title, String synopsis,String director,String cast,MovieStatus movieStatus,MovieType movieType,Blockbuster isBlockbuster,MovieRating movieRating){
		for(Movie movie:listMovies) {
			if(movie.getMovieId() == movieId) {
				movie.setBlockbuster(isBlockbuster);
				movie.setTitle(title);
				movie.setSynopsis(synopsis);
				movie.setDirector(director);
				movie.setCast(cast);
				movie.setMovieStatus(movieStatus);
				movie.setMovieType(movieType);
			}
		}
		return listMovies;
	}
	
	/**
	 * Remove a movie by Id
	 * @param listMovies
	 * @param movieId
	 * @return
	 */
	public ArrayList<Movie> removeMovieFromList(ArrayList<Movie> listMovies,int movieId){
		for(Movie movie:listMovies) {
			if(movie.getMovieId() == movieId) {
				listMovies.remove(movie);
			}
		}
		return listMovies;
	}
	
	
}
