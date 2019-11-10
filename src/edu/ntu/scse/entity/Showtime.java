package edu.ntu.scse.entity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

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
     * Default constructor of showtime
     */
    public Showtime() {

    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return getScreeningTime().equals(showtime.getScreeningTime()) &&
                getCinema().equals(showtime.getCinema()) &&
                getMovie().equals(showtime.getMovie()) &&
                getSeats().equals(showtime.getSeats());
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        String strDate = dateFormat.format(screeningTime.getTime());
        return "Showtime" +
                "|" + strDate +
                "|" + movie.getMovieId() +
                "|" + cinema.getCinemaId();
    }

    public void print() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        String strDate = dateFormat.format(screeningTime.getTime());
        System.out.println(strDate + " Cinema: " + cinema.getCinemaId());
    }
}