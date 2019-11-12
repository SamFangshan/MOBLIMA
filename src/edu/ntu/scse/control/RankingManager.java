package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Zilvinas
 */
public class RankingManager {

    private ArrayList<Movie> movies;
    private ArrayList<Booking> bookings;
    private ArrayList<Showtime> showtimes;

    public RankingManager(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ArrayList<Booking> bookings) {
        this.movies = movies;
        this.bookings = bookings;
        this.showtimes = showtimes;
    }

    /**
     * Return top 5 ranked movies by overall rating score
     * @return topRanked
     */
    public ArrayList<Movie> getTopRankedMovies() {
        ArrayList<Movie> topRanked = new ArrayList<>();
        ArrayList<Movie> tempMovies = new ArrayList<>(movies);

        Collections.sort(tempMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o2.getOverallRating(), o1.getOverallRating());
            }
        });

        for(Movie mov : tempMovies) {
            topRanked.add(mov);
            if(topRanked.size() == 5) {
                break;
            }
        }

        return topRanked;
    }

    /**
     * Get top 5 ranked movies by ticket sellings
     * @return mostPopular
     */
    public ArrayList<Movie> getTopRankedBySelling() {
        ArrayList<Movie> mostPopular = new ArrayList<>(movies);

        int[] popularMovies = new int[movies.size()];

        for(Booking booking : bookings) {
            int ticketsBought = booking.getTickets().size();
            Showtime show = booking.getShowTime();
            popularMovies[show.getMovie().getMovieId()-1] += ticketsBought;
        }

        Collections.sort(mostPopular, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Integer.compare(popularMovies[o2.getMovieId()-1], popularMovies[o1.getMovieId()-1]);
            }
        });

        ArrayList<Movie> top5BestSoldMovies = new ArrayList<>();
        for(Movie mov : mostPopular) {
            top5BestSoldMovies.add(mov);
            if(top5BestSoldMovies.size() == 5) {
                break;
            }
        }

        return mostPopular;
    }
}
