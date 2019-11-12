package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Zilvinas
 */
public class RankingManager {

    private ReadFileWriteData rfwd;
    private ArrayList<Movie> movies;

    public RankingManager() {
        rfwd = new ReadFileWriteData();
        movies = rfwd.readMovies("data/movies.txt",null);
    }

    /**
     * Return top 5 ranked movies by overall rating score
     * @return topRanked
     */
    public ArrayList<Movie> getTopRankedMovies() {
        ArrayList<Movie> topRanked = new ArrayList<>();

        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o2.getOverallRating(), o1.getOverallRating());
            }
        });

        for(Movie mov : movies) {
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
        ArrayList<Movie> mostPopular = new ArrayList<>();
        //int occurances = Collections.frequency(ticketSales, movieId);

        int[] ticketsSold = new int[movies.size()];

        return mostPopular;
    }
}
