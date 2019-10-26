package edu.ntu.scse.entity;
import java.util.Calendar;

public class Showtime {
    private Calendar screeningTime;
    private Cinema cinema;
    private Movie movie;

    public Showtime(Calendar screeningTime, Cinema cinema, Movie movie) {
        this.screeningTime = screeningTime;
        this.cinema = cinema;
        this.movie = movie;
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

    public void setScreeningTime(Calendar screeningTime) {
        this.screeningTime = screeningTime;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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