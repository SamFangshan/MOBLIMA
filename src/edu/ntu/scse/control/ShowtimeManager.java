package edu.ntu.scse.control;

import edu.ntu.scse.entity.Cinema;
import edu.ntu.scse.entity.Cineplex;
import edu.ntu.scse.entity.Showtime;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowtimeManager {
    private ArrayList<Showtime> showtimes;

    public ShowtimeManager(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public ArrayList<Movie> getMoviesByCineplex(Cineplex cineplex) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Cinema cinema : cineplex.getCinemas()) {
            ArrayList<Movie> moviesByCinema = getMoviesByCinema(cinema);
            movies.addAll(moviesByCinema);
        }
        return movies;
    }

    private ArrayList<Movie> getMoviesByCinema(Cinema cinema) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Showtime showtime: showtimes) {
            if (showtime.getCinema().equals(cinema)) {
                movies.add(showtime.getMovie());
            }
        }
        return movies;
    }

    public ArrayList<Showtime> getShowtime(Movie movie) {
        ArrayList<Showtime> showtimesResult = new ArrayList<Showtime>();
        for (Showtime showtime: showtimes) {
            if (showtime.getMovie().equals(movie)) {
                showtimesResult.add(showtime);
            }
        }
        return showtimesResult;
    }
}
