package edu.ntu.scse.entity;

import edu.ntu.scse.control.SeatToStringConverter;

import java.util.ArrayList;

/**
 * Represents individual cinema inside of a cineplex
 *
 * @author Fangshan
 *
 */
public class Cinema {
    /**
     * Cinema ID of a cinema
     */
    private int cinemaId;

    /**
     * List of seats in a cinema
     */
    private ArrayList<Seat> seats;

    /**
     * The cineplex that the cinema belong to
     */
    private Cineplex cineplex;

    /**
     * Class of the cinema
     */
    private CinemaClass cinemaClass;

    /**
     * Constructor with all attributes of cinema
     * @param cinemaId
     * @param seats
     * @param cineplex
     * @param cinemaClass
     */
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

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        if (this.getCinemaId() == ((Cinema)obj).getCinemaId() &&
                this.getSeats().equals(((Cinema)obj).getCinemaId()) &&
                this.getCineplex().equals(((Cinema)obj).getCineplex()) &&
                this.getCinemaClass().equals(((Cinema)obj).getCinemaClass())) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String cineplexString = "Cineplex ID: " + cineplex.getCineplexId()
                + "\n" + "Cineplex Location: " + cineplex.getLocation();
        String cinemaIdString = "Cinema ID: " + cinemaId;
        String cinemaClassString = "Cinema Class: " + cinemaClass.toString();
        String seatsString = new SeatToStringConverter(seats).convert();
        return cineplexString + "\n" + cinemaIdString + "\n" + cinemaClassString + "\n" + seatsString + "\n";
    }
}
