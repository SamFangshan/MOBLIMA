package edu.ntu.scse.boundary;

import edu.ntu.scse.control.BookingManager;
import edu.ntu.scse.control.ShowtimeManager;
import edu.ntu.scse.entity.Booking;
import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowtimeUI {
    ArrayList<Movie> movies;
    ArrayList<Showtime> showtimes;
    ShowtimeManager showtimeManager;
    BookingManager bookingManager;
    MovieGoer movieGoer;
    ArrayList<Review> reviews;

    public ShowtimeUI(ArrayList<Movie> movies, ArrayList<Showtime> showtimes, ShowtimeManager showtimeManager, BookingManager bookingManager, MovieGoer movieGoer) {
        this.movies = movies;
        this.showtimes = showtimes;
        this.showtimeManager = showtimeManager;
        this.bookingManager = bookingManager;
        this.movieGoer = movieGoer;
    }

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
                case 4:
                    addReviewRatingforMovie();
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

    private void proceedToShowtime(Movie movie) {
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
            try {
                showtime = showtimes.get(option);
            } catch (Exception e) {
                System.out.println("Invalid index, try again.");
            }
        } while (option < 0 || option >= showtimes.size());
        bookingManager.createBooking(movieGoer, showtime);
    }

    private int printMovies() {
        for (Movie movie : movies) {
            movie.print();
        }
        System.out.print("Select a movie (enter movie ID) or -1 to return: ");
        Scanner input = new Scanner(System.in);
        Movie movie;
        int choice = -1;
        try {
            choice = input.nextInt();
            movie = movies.get(choice - 1);
            return choice - 1;
        } catch (Exception e) {
            if (choice == -1) {
                return -1;
            }
            System.out.println("Non existent movie");
            return -1;
        }
    }

    private int searchMovie() {
        System.out.print("Enter the name of the movie to search: ");
        Scanner input = new Scanner(System.in);
        String keyword = input.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().toUpperCase().contains(keyword.toUpperCase())) {
                return movie.getMovieId() - 1;
            }
        }
        System.out.println("Non existent movie.");
        return -1;
    }

    private void printDetailsOfMovie(){
        System.out.println("Enter the name of the movie that you want more details about: ");
        Scanner input = new Scanner(System.in);
        String title = input.nextLine();
        for(Movie movie: movies){
            if(movie.getTitle().toUpperCase().contains(title.toUpperCase())){
                int movieId = movie.getMovieId();
                for(Review review: reviews){
                    if(review.getMovieId() == movieId){
                        System.out.println("Review:");
                        System.out.println(review.getReviewText());
                        System.out.println("Rating:"+review.getRating());
                    }
                }
                return;
            }
        }
        System.out.println("Movie Not Found");
    }

    private void addReviewRatingforMovie(){
        System.out.println("Enter the name of the movie that you want to write Review/Rating for: ");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        for(Movie movie:movies){
            if(movie.getTitle().toUpperCase().contains(title.toUpperCase())){
                System.out.println("Your Review Text:");
                String reviewText = sc.nextLine();
                System.out.println("Your Rating for the movie (1-5):");
                int rating = sc.nextInt();

                int reviewId = reviews.size();

                Review review = new Review(reviewId,reviewText,rating,movie.getMovieId(),movieGoer);
                reviews.add(review);
                System.out.println("New review added!");

                // Update the overall rating for this movie
                float sum = 0;
                int total = 0;
                for(Review review1: reviews){
                    if(review1.getMovieId() == movie.getMovieId()){
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
