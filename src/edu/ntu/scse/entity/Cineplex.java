package edu.ntu.scse.entity;

import java.util.ArrayList;

public class Cineplex {
    private int cineplexId;
    private String location;
    private ArrayList<Cinema> cinemas;

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
}
