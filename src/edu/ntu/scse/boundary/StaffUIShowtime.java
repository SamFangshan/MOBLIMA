package edu.ntu.scse.boundary;

import edu.ntu.scse.control.MovieManager;
import edu.ntu.scse.control.ShowtimeManager;
import edu.ntu.scse.entity.Cinema;
import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.Showtime;
import edu.ntu.scse.entity.Staff;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Staff UI to add, update, and delete showtimes
 */
public class StaffUIShowtime {
    /**
     * List of movies the application maintains
     */
    ArrayList<Movie> movies;
    /**
     * List of showtimes the application maintains
     */
    ArrayList<Showtime> showtimes;
    /**
     * List of cinemas the application maintains
     */
    ArrayList<Cinema> cinemas;
    /**
     * The staff to update system configs
     */
    Staff staff;

    /**
     * Constructor
     * @param movies
     * @param showtimes
     * @param cinemas
     * @param staff
     */
    public StaffUIShowtime(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ArrayList<Cinema> cinemas, Staff staff){
        this.showtimes = showtimes;
        this.movies = movies;
        this.cinemas = cinemas;
        this.staff = staff;
    }

    /**
     * Start of this UI
     */
    public void start() {
        MovieManager movieManager = new MovieManager(movies);
        ShowtimeManager showtimeManager = new ShowtimeManager(showtimes);
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n================================");
            System.out.println("=== [MOBLIMA Staff Menu] ===");
            System.out.println("Choose an option:");
            System.out.println("[0] Quit");
            System.out.println("[1] Create Showtime");
            System.out.println("[2] Update Showtime");
            System.out.println("[3] Remove Showtime");
            System.out.println("================================");

            option = sc.nextInt();
            int movieIndex = -1;
            switch (option) {
                case 0:
                    System.out.println("Returning to previous menu...");
                    break;
                case 1:
                    System.out.println("Creating a new showtime...");
                    showtimeManager.createShowtime(cinemas,movies);
                    break;
                case 2:
                    System.out.println("Updating a showtime...");
                    showtimeManager.updateShowtime(cinemas,movies);
                    break;
                case 3:
                    System.out.println("Removing a showtime...");
                    showtimeManager.removeShowtime();
                    break;
                default:
                    System.out.println("No such option.");
                    break;
            }
            if (movieIndex != -1) {
                Movie movie = movies.get(movieIndex);
                //proceedToShowtime(movie);
            }
        } while (option != 0);
    }
}
