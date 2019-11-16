package edu.ntu.scse.boundary;

import edu.ntu.scse.control.BookingManager;
import edu.ntu.scse.control.ShowtimeManager;
import edu.ntu.scse.entity.Booking;
import edu.ntu.scse.entity.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI for displaying movies and their showtimes
 * @author Fangshan
 */
public class ShowtimeUI {
    /**
     * The list of movies the application maintains
     */
    ArrayList<Movie> movies;
    /**
     * The list of showtimes the application maintains
     */
    ArrayList<Showtime> showtimes;
    /**
     * ShowtimeManager to perform control logic related to showtimes
     */
    ShowtimeManager showtimeManager;
    /**
     * BookingManager to perform control logic related to booking a ticket for a specific showtime
     */
    BookingManager bookingManager;
    /**
     * The movieGoer that uses the application
     */
    MovieGoer movieGoer;
    /**
     * The list of reviews the application maintains
     */
    ArrayList<Review> reviews;

    /**
     * Constructor
     * @param movies
     * @param showtimes
     * @param showtimeManager
     * @param bookingManager
     * @param movieGoer
     * @param reviews
     */
    public ShowtimeUI(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ShowtimeManager showtimeManager, BookingManager bookingManager, MovieGoer movieGoer, ArrayList<Review> reviews) {
        this.movies = movies;
        this.showtimes = showtimes;
        this.showtimeManager = showtimeManager;
        this.bookingManager = bookingManager;
        this.movieGoer = movieGoer;
        this.reviews = reviews;
    }

    /**
     * Start of this UI
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n================================");
            System.out.println("=== [MOBLIMA Showtime Menu] ===");
            System.out.println("Choose an option:");
            System.out.println("[0] Quit");
            System.out.println("[1] List Movie");
            System.out.println("[2] Search Movie");
            System.out.println("[3] View More Details about Movie");
            System.out.println("[4] Write Review/Rating for Movie");
            System.out.println("================================");

            option = sc.nextInt();
            int movieIndex = -1;
            switch (option) {
                case 0:
                    System.out.println("Returning to previous menu...");
                    break;
                case 1:
                    movieIndex = printMovies();
                    break;
                case 2:
                    movieIndex = searchMovie();
                    break;
                case 3:
                    printDetailsOfMovie();
                    break;
                case 4:
                    addReviewRatingforMovie(movieGoer);
                    break;
                default:
                    System.out.println("No such option.");
                    break;
            }
            if (movieIndex != -1) {
                Movie movie = movies.get(movieIndex);
                proceedToShowtime(movie);
            }
        } while (option != 0);
    }

    /**
     * Display showtimes after user has selected a movie
     * @param movie
     */
    private void proceedToShowtime(Movie movie) {
        Scanner input = new Scanner(System.in);
        System.out.println("The following are the showtimes of " + movie.getTitle());
        ArrayList<Showtime> showtimes = showtimeManager.getShowtime(movie);
        int i = 0;
        for (Showtime showtime : showtimes) {
            System.out.print(i + " ");
            showtime.print();
            i++;
        }
        int option = 0;
        Showtime showtime = null;
        do {
            System.out.println("Please pick a showtime by entering its index: ");
            option = input.nextInt();
            try {
                showtime = showtimes.get(option);
            } catch (Exception e) {
                System.out.println("Invalid index, try again.");
            }
        } while (option < 0 || option >= showtimes.size());
        if (movie.getMovieStatus().equals(MovieStatus.COMING_SOON)) {
            System.out.println("Not available for booking yet.");
            return;
        }
        bookingManager.createBooking(movieGoer, showtime);
    }

    /**
     * List all movies
     * @return choice
     */
    private int printMovies() {
        for (Movie movie : movies) {
            if (!movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING)) { // will not print movies not showing anymore
                movie.print();
            }
        }
        System.out.print("Select a movie (enter movie ID) or -1 to return: ");
        Scanner input = new Scanner(System.in);
        Movie movie;
        int choice = -1;
        try {
            choice = input.nextInt();
            movie = movies.get(choice - 1);
            if (movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING)) {
                System.out.println("End show movie");
                return -1;
            }
            return choice - 1;
        } catch (Exception e) {
            if (choice == -1) {
                return -1;
            }
            System.out.println("Non existent movie");
            return -1;
        }
    }

    /**
     * Search movies
     * @return choice
     */
    private int searchMovie() {
        System.out.print("Enter the name of the movie to search: ");
        Scanner input = new Scanner(System.in);
        String keyword = input.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().toUpperCase().contains(keyword.toUpperCase())) {
                if (!movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING)) {
                    return movie.getMovieId() - 1;
                } else {
                    break;
                }
            }
        }
        System.out.println("Non existent movie.");
        return -1;
    }

    /**
     * Print reviews and ratings of movies
     */
    private void printDetailsOfMovie(){
        System.out.println("Enter the name of the movie that you want more details about: ");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        for(Movie movie: movies){
            if(movie.getTitle().toUpperCase().contains(title.toUpperCase())) {
                System.out.println("Rating: " + movie.getOverallRating());
                System.out.println("Review: ");
                for (Review review : movie.getReviews()) {
                    System.out.println(review.getReviewText());
                }
                return;
            }
        }
        System.out.println("Movie Not Found");
    }

    /**
     * Add a review and a rating to a movie by a movie goer
     * @param movieGoer
     */
    private void addReviewRatingforMovie(MovieGoer movieGoer){
        System.out.println("Enter the name of the movie that you want to write Review/Rating for: ");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        for(Movie movie:movies){
            if(movie.getTitle().toUpperCase().contains(title.toUpperCase())){
                System.out.println("Do you want to write Review? Y: Yes N: No");
                String reviewOrNot = sc.nextLine();
                String reviewText;
                if(reviewOrNot.equals("Y")){
                    System.out.println("Your Review Text:");
                    reviewText = sc.nextLine();
                }else{
                    reviewText = "";
                }
                System.out.println("Do you want to enter a Rating? Y: Yes N: No");
                String ratingOrNot = sc.nextLine();
                int rating;
                if(ratingOrNot.equalsIgnoreCase("Y")){
                    System.out.println("Your Rating for the movie (1-5):");
                    rating = sc.nextInt();
                }else{
                    rating = -1;
                }

                int reviewId = reviews.size()+1;

                Review review = new Review(reviewId,reviewText,rating,movieGoer);
                reviews.add(review);
                movie.getReviews().add(review);

                System.out.println("New review/rating added!");
                System.out.println("Note that you need to quit Showtime Menu for the new rating/review to be added to the system.");

                // Update the overall rating for this movie
                float sum = 0;
                int total = 0;
                for(Review review1: movie.getReviews()){
                    if(review1.getRating() != -1){
                        sum += review1.getRating();
                        total ++;
                    }
                }
                if(total != 0){
                    sum /= total;
                    movie.setOverallRating(sum);
                }else{
                    movie.setOverallRating(-1);
                }
                return;
            }
        }
        System.out.println("Movie Not Found");
    }
}
