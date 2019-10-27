package edu.ntu.scse.entity;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a specific showtime of a movie
 *
 * @author Fangshan
 *
 */
public class Showtime {
    /**
     * The timing of the show
     */
    private Calendar screeningTime;

    /**
     * The cinema where the movie is shown
     */
    private Cinema cinema;

    /**
     * The movie to be shown
     */
    private Movie movie;

    /**
     * List of seats in the cinema with booking information filled
     */
    private ArrayList<Seat> seats;

    /**
     * Constructor with all attributes of showtime
     * @param screeningTime
     * @param cinema
     * @param movie
     * @param seats
     */
    public Showtime(Calendar screeningTime, Cinema cinema, Movie movie, ArrayList<Seat> seats) {
        this.screeningTime = screeningTime;
        this.cinema = cinema;
        this.movie = movie;
        this.seats = seats;
    }

    public Calendar getScreeningTime() {
        return screeningTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setScreeningTime(Calendar screeningTime) {
        this.screeningTime = screeningTime;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        if (this.getScreeningTime().equals(((Showtime)obj).getScreeningTime()) &&
                this.getCinema().equals(((Showtime)obj).getCinema()) &&
                this.getMovie().equals(((Showtime)obj).getMovie())) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String movieString = "Movie: " + movie.getTitle();
        String screeningTimeString = "Screening Time: " + screeningTime.get(Calendar.DATE)
                + " " + screeningTime.get(Calendar.HOUR_OF_DAY) + ": " + screeningTime.get(Calendar.MINUTE);
        String cinemaString = "Cinema: " + cinema.getCinemaId() + " (" + cinema.getCinemaClass() + ")";
        return movieString + "\n" + screeningTimeString + "\n" + cinemaString + "\n";
    }
}