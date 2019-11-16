package edu.ntu.scse.entity;

import java.util.*;

import edu.ntu.scse.boundary.HolidayUI;
import edu.ntu.scse.boundary.PriceUI;
import edu.ntu.scse.boundary.ShowtimeUI;
import edu.ntu.scse.boundary.StaffUIMovie;
import edu.ntu.scse.boundary.StaffUIShowtime;
import edu.ntu.scse.config.PriceConfig;
import edu.ntu.scse.control.*;

/**
 * MOvie Booking and LIsting Management Application (MOBLIMA) <br>
 * Act as a central class of MOBLIMA and has all the relevant data
 *
 * @author Kailing
 *
 */
public class MOBLIMA {

	private ReadFileWriteData readFileWriteData;
	private RankingManager rankingManager;
	private ArrayList<Cinema> cinemas;
	private ArrayList<Cineplex> cineplexes;
	private ArrayList<Showtime> showtimes;
	private ArrayList<Movie> movies;
	private ArrayList<MovieGoer> movieGoers;
	private ArrayList<Staff> staffs;
	private ArrayList<Review> reviews;
	private ArrayList<Booking> bookings;
	private ArrayList<Ticket> tickets;
	private ArrayList<Holiday> holidays;

	/**
	 * Constructor of MOBLIMA <br>
	 * Load saved data
	 */
	public MOBLIMA() {
		readFileWriteData = new ReadFileWriteData();
		loadData();
		rankingManager = new RankingManager(movies, showtimes, bookings);
		PriceConfig.init();
	}

	/**
	 * Load MOBLIMA data from previous saved data
	 */
	private void loadData() {
		reviews = new ArrayList<>();
		bookings = new ArrayList<>();

		System.out.println("Loading data...");
		Object[] results = readFileWriteData.readCineplexesAndCinemas("data/cineplexes.txt",
				"data/cinemas.txt");
		cineplexes = (ArrayList<Cineplex>) results[0];
		cinemas = (ArrayList<Cinema>) results[1];
		staffs = readFileWriteData.readStaffs("data/staffs.txt");
		tickets = readFileWriteData.readTickets("data/tickets.txt");
		movieGoers = readFileWriteData.readMovieGoer("data/moviegoer.txt",bookings,reviews);
		reviews = readFileWriteData.readReviews("data/reviews.txt",movieGoers);

		movies = readFileWriteData.readMovies("data/movies.txt",reviews);
		showtimes = readFileWriteData.readShowtimes("data/showtime.txt", movies, cinemas);

		bookings = readFileWriteData.readBookings("data/bookings.txt",movieGoers,showtimes,tickets);
		holidays = readFileWriteData.readHolidays("data/holidays.txt");

		System.out.println("Loading data done.");
	};

	/**
	 * Save MOBLIMA data
	 */
	public void saveData() {
		System.out.println("Saving data...");
		readFileWriteData.writeMovies("data/movies.txt", movies);
		readFileWriteData.writeShowtimes("data/showtime.txt", showtimes);
		readFileWriteData.writeTickets("data/tickets.txt", tickets);
		readFileWriteData.writeBookings("data/bookings.txt", bookings);
		readFileWriteData.writeHolidaysToFile("data/holidays.txt", holidays);
		System.out.println("Saving data done.");
	}

	/**
	 * Let user use the program as Staff or Moviegoer per session
	 */
	public void displayLoginMenu() {
		System.out.println("Starting MOBLIMA System....");

		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {
			System.out.println("\n================================");
			System.out.println("=== [MOBLIMA Login Menu] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Login as Staff");
			System.out.println("[2] Continue as Moviegoer");
			System.out.println("[0] Exit MOBLIMA");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
				case 0:
					System.out.println("Exiting MOBLIMA System...");
					break;
				case 1: // Login as Staff
					Staff loginStaff = null;
					System.out.println("LOGIN AS STAFF");
					System.out.println("Your Staff Id?");
					int staffId = sc.nextInt();
					System.out.println("Your Password?");
					String staffPassoword = sc.next();
					for(Staff staff: staffs){
						if(staff.getCinemaStaffId() == staffId){
							loginStaff = staff;
							if(staff.getPassword().equals(staffPassoword)) {
								System.out.println("Staff login successful!");
								displayAdminModule(staff);
								break;
							}
							else{
								System.out.println("Wrong Password!");
								System.out.println("Returning to Login menu...");
								break;
							}
						}
					}
					if(loginStaff == null) {
						System.out.println("Invalid Staff Id!");
						System.out.println("Returning to Login menu...");
					}
					//System.out.println("");
					break;
				case 2: // Continue as Moviegoer
					displayMoviegoerModule();
					break;
				default:
					System.out.println("No such option.");
					break;
			}

		} while (option != 0);
	}

	/**
	 * User is using MOBLIMA as Staff and has access to admin module
	 */
	private void displayAdminModule(Staff staff) {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {
			System.out.println("\n================================");
			System.out.println("=== [MOBLIMA Admin Main Menu] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Movies");
			System.out.println("[2] Showtimes");
			System.out.println("[3] Holidays");
			System.out.println("[4] Price Manager");
			System.out.println("[5] List Top 5 Movies by Ticket Sales");
			System.out.println("[6] List Top 5 Movies by Overall Rating");
			System.out.println("[0] Logout");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
				case 0:
					System.out.println("Returning to MOBLIMA Login System...");
					break;
				case 1: // Movies
					for(Movie m : movies) {
						m.print();
					}
					StaffUIMovie staffUImovie = new StaffUIMovie(movies,showtimes,cinemas,staff);
					staffUImovie.start();
					readFileWriteData.writeMovies("data/movies.txt",movies);
					break;
				case 2:
					for(Showtime s: showtimes){
						s.print();
					}
					StaffUIShowtime staffUIshowtime = new StaffUIShowtime(movies,showtimes,cinemas,staff);
					staffUIshowtime.start();
					readFileWriteData.writeShowtimes("data/showtime.txt",showtimes);
					break;
				case 3: // Holidays
					HolidayUI holidayUI = new HolidayUI(holidays);
					holidayUI.start();
					break;
				case 4:
					PriceUI priceUI = new PriceUI();
					priceUI.start();
					break;
				case 5:
					System.out.println("Listing Top 5 Movies By ticket sales\n====================================");
					LinkedHashMap<String, Integer> ticketsSold = rankingManager.ticketSales();
					for (String i : ticketsSold.keySet()) {
						System.out.println("Movie: " + i + " Tickets sold: " + ticketsSold.get(i));
					}
					break;
				case 6:
					LinkedHashMap<String, Float> bestRankedMovies = rankingManager.bestRanked();
					System.out.println("Listing Top 5 Movies By Overall Rating\n====================================");
					for (String i : bestRankedMovies.keySet()) {
						System.out.println("Movie: " + i + " Ranking: " + bestRankedMovies.get(i));
					}
					break;
				default:
					System.out.println("No such option.");
					break;
			}

		} while (option != 0);
	}

	/**
	 * User is using MOBLIMA as Moviegoer and has access to moviegoer module
	 */
	private void displayMoviegoerModule() {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		MovieGoer movieGoerObject = null;
		do {
			System.out.print("Please enter your email: ");
			String email = sc.next();
			for (MovieGoer movieGoer : movieGoers) {
				if (movieGoer.getEmail().equalsIgnoreCase(email)) {
					movieGoerObject = movieGoer;
				}
			}
		} while (movieGoerObject == null);

		do {
			System.out.println("\n================================");
			System.out.println("=== [MOBLIMA Moviegoer Main Menu] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Search/List Movie");
			System.out.println("[2] View Booking History");
			System.out.println("[3] List Top 5 Movies by Ranking");
			System.out.println("[4] List Top 5 Movies by Ticket Sales");
			System.out.println("[0] Logout");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
				case 0:
					System.out.println("Returning to MOBLIMA Login System...");
					break;
				case 1:
					new ShowtimeUI(movies, showtimes, new ShowtimeManager(showtimes), new BookingManager(holidays, bookings, tickets), movieGoerObject,reviews).start();
					readFileWriteData.writeMovies("data/movies.txt",movies);
					readFileWriteData.writeReviews("data/reviews.txt",reviews);
					break;
				case 2:
					System.out.println("The following is the booking history of " + movieGoerObject.getEmail());
					ArrayList<Booking> bookings = new BookingManager(holidays).getBookingHistory(movieGoerObject);
					if(bookings != null && bookings.size() != 0) {
						for (Booking booking : bookings) {
							System.out.println(booking.toStringConsole());
						}
					}
					else{
						System.out.println("No booking history for this user yet");
					}
					break;
				case 3:
					System.out.println("================================");
					System.out.println("Displaying Top 5 Movies by Ranking");
					System.out.println("================================");
					for(Movie movie : rankingManager.getTopRankedMovies()) {
						System.out.println(movie.getTitle());
					}
					break;
				case 4:
					System.out.println("================================");
					System.out.println("Displaying Top 5 Movies by Ticket Sales");
					System.out.println("================================");
					for(Movie movie : rankingManager.getTopRankedBySelling()) {
						System.out.println(movie.getTitle());
					}
					break;
				default:
					System.out.println("No such option.");
					break;
			}

		} while (option != 0);
	}
}