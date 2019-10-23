package edu.ntu.scse.entity;

import java.util.ArrayList;

public class Cinema {
    private int cinemaId;
    private ArrayList<Seat> seats;
    private Cineplex cineplex;
    private CinemaClass cinemaClass;

    public Cinema(int cinemaId, ArrayList<Seat> seats, Cineplex cineplex, CinemaClass cinemaClass) {
        this.cinemaId = cinemaId;
        this.seats = seats;
        this.cineplex = cineplex;
        this.cinemaClass = cinemaClass;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }
}
