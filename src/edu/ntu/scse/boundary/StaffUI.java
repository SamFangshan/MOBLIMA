package edu.ntu.scse.boundary;
import edu.ntu.scse.entity.*;
import edu.ntu.scse.cnotrol.*;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

public class StaffUI {
    ArrayList<Movie> movies;
    ArrayList<Showtime> showtimes;
    ArrayList<Cinema> cinemas;
    Staff staff;

    public StaffUI(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ArrayList<Cinema> cinemas, Staff staff){
        this.showtimes = showtimes;
        this.movies = movies;
        this.cinemas = cinemas;
        this.staff = staff;
    }

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
            System.out.println("[4] Create Showtime");
            System.out.println("[5] Update Showtime");
            System.out.println("[6] Remove Showtime");
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
                    String update = sc.nextLine();
                    movieManager.updateMovie(update);
                    break;
                case 3:
                    System.out.println("What is the title of the movie you want to remove?");
                    String remove = sc.nextLine();
                    movieManager.removeMovie(remove);
                case 4:
                    System.out.println("Creating a new showtime...");
                    showtimeManager.createShowtime();
                case 5:
                    System.out.println("Updating a showtime...");
                    showtimeManager.updateShowtime();
                case 6:
                    System.out.println("Removing a showtime...");
                    showtimeManager.removeShowtime();
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
