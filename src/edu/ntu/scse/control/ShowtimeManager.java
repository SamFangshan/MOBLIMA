package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Performs control logic operations related to showtime
 *
 * @author Fangshan
 *
 */
public class ShowtimeManager {
    /**
     * List of showtime that this showtime manager manages
     */
    private ArrayList<Showtime> showtimes;

    /**
     * Constructor with all attributes of showtime manager
     * @param showtimes
     */
    public ShowtimeManager(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Default Constructor
     */
    public ShowtimeManager() {}

    /**
     * Returns the list of movies shown in a specific cineplex
     * @param cineplex
     * @return movies
     */
    public ArrayList<Movie> getMoviesByCineplex(Cineplex cineplex) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Cinema cinema : cineplex.getCinemas()) {
            ArrayList<Movie> moviesByCinema = getMoviesByCinema(cinema);
            movies.addAll(moviesByCinema);
        }
        return movies;
    }

    /**
     * Returns the list of movies shown in a specific cinema
     * @param cinema
     * @return movies
     */
    private ArrayList<Movie> getMoviesByCinema(Cinema cinema) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Showtime showtime: showtimes) {
            if (showtime.getCinema().equals(cinema)) {
                movies.add(showtime.getMovie());
            }
        }
        return movies;
    }

    /**
     * Returns all showtime of a movie
     * @param movie
     * @return showtimes
     */
    public ArrayList<Showtime> getShowtime(Movie movie) {
        ArrayList<Showtime> showtimesResult = new ArrayList<Showtime>();
        for (Showtime showtime: showtimes) {
            if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
                showtimesResult.add(showtime);
            }
        }
        return showtimesResult;
    }

    /**
     * Prints the seating arrangement of a specific showtime
     * @param showtime
     */
    public void displaySeatsLayout(Showtime showtime) {
        System.out.println(showtime.getCinema().getCinemaClass().toString());
        String seatsLayout = new SeatToStringConverter(showtime.getSeats()).convert();
        System.out.println(seatsLayout);
        System.out.println("O indicates that the seat is already booked.");
        System.out.println("X indicates that this seat does not exist.");
        System.out.println("You can choose from any empty seats.");
    }
    /**
     * Create a new showtime entry
     */
    public void createShowtime(ArrayList<Cinema> cinemas, ArrayList<Movie> movies){
        Scanner sc = new Scanner(System.in);

        System.out.println("Screening Date & Time? (Please enter yyyy-mm-dd HH:mm format)");
        String date = sc.nextLine();
        Calendar time = StringToCalendar(date);

        System.out.println("Which Cinema Id?");
        int cinemaId = sc.nextInt();
        Cinema cinema = cinemas.get(cinemaId-1);

        System.out.println("Which Movie Id?");
        int movieId = sc.nextInt();
        Movie movie = movies.get(movieId-1);

        //When Showtime is first created, all the seats should be empty
        ArrayList<Seat> seats = new ArrayList<>();
        seats = copySeats(cinema);

        int id = showtimes.get(showtimes.size()-1).getShowtimeId()+1;
        Showtime showtime = new Showtime(id,time, cinema, movie, seats);

        showtimes.add(showtime);
        System.out.println("Showtime Created!");
        System.out.println("Please quit this menu first for the change to be recorded!");
    }

    /**
     * Update details of a Showtime
     */
    public void updateShowtime(ArrayList<Cinema>cinemas,ArrayList<Movie> movies){
        Scanner sc = new Scanner(System.in);

        System.out.println("Which Showtime Id?");
        int showtimeId = sc.nextInt();

        for(Showtime showtime: showtimes){
            if(showtime.getShowtimeId() == showtimeId){
                System.out.println("Which attribute of the showtime do you want to change?");
                System.out.println("1: Screening Time");
                System.out.println("2: Cinema");
                System.out.println("3: Movie");
                int option = sc.nextInt();
                switch (option){
                    case 1:
                        System.out.println("New Screening Time: (Please enter yyyy-mm-dd HH:mm format)");
                        String foo = sc.nextLine();
                        String time = sc.nextLine();
                        Calendar date = StringToCalendar(time);
                        showtime.setScreeningTime(date);
                        break;
                    case 2:
                        System.out.println("New Cinema ID: ");
                        int newCinemaId = sc.nextInt();
                        showtime.setCinema(cinemas.get(newCinemaId - 1));
                        break;
                    case 3:
                        System.out.println("New Movie ID: ");
                        int newMovieId = sc.nextInt();
                        showtime.setMovie(movies.get(newMovieId - 1));
                        break;
                    default:
                        System.out.println("No such option");
                        break;
                }
                System.out.println("Showtime updated!");
                System.out.println("Please quit this menu first for the change to be recorded!");
                return;
            }
        }
        System.out.println("Showtime not found");
    }

    /**
     * Remove a Showtime
     */
    public void removeShowtime(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Which Showtime Id?");
        int showtimeId = sc.nextInt();

        for(Showtime showtime: showtimes){
            if(showtime.getShowtimeId() == showtimeId){
                showtimes.remove(showtime);
                System.out.println("Showtime removed!");
                System.out.println("Please quit this menu first for the change to be recorded!");
                return;
            }
        }
        System.out.println("Showtime not found");
    }

    // Util functions
    private Calendar StringToCalendar(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    private ArrayList<Seat> copySeats(Cinema cinema) {
        ArrayList<Seat> seats1 = cinema.getSeats();
        ArrayList<Seat> seats2 = new ArrayList<Seat>();
        for (int i = 0; i < seats1.size(); i++) {
            seats2.add(seats1.get(i).clone());
        }
        return seats2;
    }
}