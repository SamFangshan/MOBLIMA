package edu.ntu.scse.entity;

import java.util.Date;

public class Showtime {
    private Date screeningTime;
    private Cinema cinema;
    private Movie movie;

    public Showtime(Date screeningTime, Cinema cinema, Movie movie) {
        this.screeningTime = screeningTime;
        this.cinema = cinema;
        this.movie = movie;
    }

    public Date getScreeningTime() {
        return screeningTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setScreeningTime(Date screeningTime) {
        this.screeningTime = screeningTime;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}