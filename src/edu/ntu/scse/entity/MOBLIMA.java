package edu.ntu.scse.entity;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ntu.scse.boundary.ShowtimeUI;
import edu.ntu.scse.boundary.StaffUI;
import edu.ntu.scse.config.PriceConfig;
<<<<<<< HEAD
import edu.ntu.scse.control.HolidayManager;
=======
import edu.ntu.scse.control.BookingManager;
>>>>>>> 88d3c3c1bce0ae59bf33a86a62093a56a1648598
import edu.ntu.scse.control.ReadFileWriteData;
import edu.ntu.scse.control.ShowtimeManager;

/**
 * MOvie Booking and LIsting Management Application (MOBLIMA) <br>
 * Act as a central class of MOBLIMA and has all the relevant data
 * 
 * @author Kailing
 *
 */
public class MOBLIMA {
	
	private ReadFileWriteData readFileWriteData;
	private HolidayManager holidayManager;
	private ArrayList<Cinema> cinemas;
	private ArrayList<Cineplex> cineplexes;
	private ArrayList<Showtime> showtimes;
	private ArrayList<Movie> movies;
	private ArrayList<MovieGoer> movieGoers;
	private ArrayList<Staff> staffs;
	private ArrayList<Review> reviews;
	private ArrayList<Holiday> holidays;

	/**
	 * Constructor of MOBLIMA <br>
	 * Load saved data
	 */
	public MOBLIMA() {
		readFileWriteData = new ReadFileWriteData();
		holidayManager = new HolidayManager("data/holidays.txt");
		loadData();
		PriceConfig.init();
	}

	/**
	 * Load MOBLIMA data from previous saved data
	 */
	private void loadData() {
		System.out.println("Loading data...");
		movies = readFileWriteData.readMovies("data/movies.txt");
		holidays = holidayManager.readHolidays();
		Object[] results = readFileWriteData.readCineplexesAndCinemas("data/cineplexes.txt",
				"data/cinemas.txt");
		cineplexes = (ArrayList<Cineplex>) results[0];
		cinemas = (ArrayList<Cinema>) results[1];
		showtimes = readFileWriteData.readShowtimes("data/showtime.txt", movies, cinemas);
		staffs = readFileWriteData.readStaff("staffs.txt");
		System.out.println("Loading data done.");
	};

	/**
	 * Save MOBLIMA data
	 */
	public void saveData() {		
		System.out.println("Saving data...");
		readFileWriteData.writeMovies("data/movies.txt", movies);
		holidayManager.writeHolidaysToFile(holidays);
		readFileWriteData.writeShowtimes("data/showtime.txt", showtimes);
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
				// TODO add staff login
				System.out.println("LOGIN AS STAFF");
				System.out.println("Your Staff Id?");
				int staffId = sc.nextInt();
				System.out.println("Your Password?");
				String staffPassoword = sc.nextLine();
				for(Staff staff: staffs){
					if(staff.getCinemaStaffId() == staffId){
						if(staff.getPassword() == staffPassoword){
							System.out.println("Staff login successful!");
							displayAdminModule(staff);
							break;
						}
					}
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
			System.out.println("[0] Logout");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to MOBLIMA Login System...");
				break;
			case 1: // Movies

				System.out.println("NOT DONE LUL REPLACE CODE THX");
				for(Movie m : movies) {
					m.print();
				}
				StaffUI staffUI = new StaffUI(movies,showtimes,cinemas,staff);
				staffUI.start();

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
			System.out.println("[0] Logout");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to MOBLIMA Login System...");
				break;
			case 1:
				new ShowtimeUI(movies, showtimes, new ShowtimeManager(showtimes), new BookingManager(holidays), movieGoerObject).start();
				break;
			case 2:
				System.out.println("The following is the booking history of " + movieGoerObject.getFirstName() + " " + movieGoerObject.getLastName());
				ArrayList<Booking> bookings = new BookingManager(holidays).getBookingHistory(movieGoerObject);
				for (Booking booking : bookings) {
					System.out.println(booking);
				}
				break;
			default:
				System.out.println("No such option.");
				break;
			}

		} while (option != 0);
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

}
