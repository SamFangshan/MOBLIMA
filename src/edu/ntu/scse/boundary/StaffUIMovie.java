package edu.ntu.scse.boundary;
import edu.ntu.scse.entity.*;
import edu.ntu.scse.control.*;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

/**
 * Staff UI to add, update, and delete movies
 */
public class StaffUIMovie {
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
    public StaffUIMovie(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ArrayList<Cinema> cinemas, Staff staff){
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
            System.out.println("[1] Create Movie");
            System.out.println("[2] Update Movie");
            System.out.println("[3] Remove Movie");
            System.out.println("================================");

            option = sc.nextInt();
            int movieIndex = -1;
            switch (option) {
                case 0:
                    System.out.println("Returning to previous menu...");
                    break;
                case 1:
                    movieManager.createMovie();
                    break;
                case 2:
                    System.out.println("What is the title of the movie you want to update?");
                    String foo = sc.nextLine();
                    String update = sc.nextLine();
                    movieManager.updateMovie(update);
                    break;
                case 3:
                    System.out.println("What is the title of the movie you want to remove?");
                    String foo2 = sc.nextLine();
                    String remove = sc.nextLine();
                    movieManager.removeMovie(remove);
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
