package edu.ntu.scse.entity;

import java.util.ArrayList;

/**
 * Represents a cineplex
 *
 * @author Fangshan
 *
 */
public class Cineplex {
    /**
     * Cineplex ID of a cineplex
     */
    private int cineplexId;

    /**
     * Location of a cineplex
     */
    private String location;

    /**
     * List of cinemas inside a cineplex
     */
    private ArrayList<Cinema> cinemas;

    /**
     * Default constructor of cineplex
     */
    public Cineplex() {

    }

    /**
     * Constructor with all attributes of cineplex
     * @param cineplexId
     * @param location
     * @param cinemas
     */
    public Cineplex(int cineplexId, String location, ArrayList<Cinema> cinemas) {
        this.cineplexId = cineplexId;
        this.location = location;
        this.cinemas = cinemas;
    }

    public int getCineplexId() {
        return cineplexId;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cineplex cineplex = (Cineplex) o;
        return getCineplexId() == cineplex.getCineplexId() &&
                getLocation().equals(cineplex.getLocation()) &&
                getCinemas().equals(cineplex.getCinemas());
    }

    @Override
    public String toString() {
        String cineplexIdString = "Cineplex ID: " + cineplexId;
        String locationString = "Location: " + location;
        String cinemasString = "Cinemas: ";
        for (int i = 0; i < cinemas.size(); i++) {
            cinemasString += cinemas.get(i).getCinemaId();
            if (i < cinemas.size() - 1) {
                cinemasString += ", ";
            }
        }
        return cineplexIdString + "\n" + locationString + "\n" + cinemasString + "\n";
    }
}
