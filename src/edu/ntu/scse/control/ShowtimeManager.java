package edu.ntu.scse.control;

import edu.ntu.scse.entity.Cinema;
import edu.ntu.scse.entity.Cineplex;
import edu.ntu.scse.entity.Showtime;

import java.util.ArrayList;

/**
 * Performs control logic operations related to showtime
 *
 * @author Fangshan
 *
 */
public class ShowtimeManager {
    /**
     * List of showtime that this showtime manager manages
     */
    private ArrayList<Showtime> showtimes;

    /**
     * Constructor with all attributes of showtime manager
     * @param showtimes
     */
    public ShowtimeManager(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Returns the list of movies shown in a specific cineplex
     * @param cineplex
     * @return movies
     */
    public ArrayList<Movie> getMoviesByCineplex(Cineplex cineplex) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Cinema cinema : cineplex.getCinemas()) {
            ArrayList<Movie> moviesByCinema = getMoviesByCinema(cinema);
            movies.addAll(moviesByCinema);
        }
        return movies;
    }

    /**
     * Returns the list of movies shown in a specific cinema
     * @param cinema
     * @return movies
     */
    private ArrayList<Movie> getMoviesByCinema(Cinema cinema) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Showtime showtime: showtimes) {
            if (showtime.getCinema().equals(cinema)) {
                movies.add(showtime.getMovie());
            }
        }
        return movies;
    }

    /**
     * Returns all showtime of a movie
     * @param movie
     * @return showtimes
     */
    public ArrayList<Showtime> getShowtime(Movie movie) {
        ArrayList<Showtime> showtimesResult = new ArrayList<Showtime>();
        for (Showtime showtime: showtimes) {
            if (showtime.getMovie().equals(movie)) {
                showtimesResult.add(showtime);
            }
        }
        return showtimesResult;
    }

    /**
     * Prints the seating arrangement of a specific showtime
     * @param showtime
     */
    public void displaySeatsLayout(Showtime showtime) {
        String seatsLayout = new SeatToStringConverter(showtime.getCinema().getSeats()).convert();
        System.out.println(seatsLayout);
        System.out.println("O indicates that the seat is already booked.");
        System.out.println("X indicates that this seat does not exist.");
        System.out.println("You can choose from any empty seats.");
    }
}