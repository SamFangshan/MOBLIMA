package edu.ntu.scse.entity;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import edu.ntu.scse.boundary.ShowtimeUI;
import edu.ntu.scse.boundary.StaffUI;
import edu.ntu.scse.config.PriceConfig;
import edu.ntu.scse.control.HolidayManager;
import edu.ntu.scse.control.BookingManager;
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
	private ArrayList<Booking> bookings;
	private ArrayList<Ticket> tickets;
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
		reviews = new ArrayList<>();
		bookings = new ArrayList<>();

		System.out.println("Loading data...");
		movies = readFileWriteData.readMovies("data/movies.txt",null);
		holidays = holidayManager.readHolidays();
		Object[] results = readFileWriteData.readCineplexesAndCinemas("data/cineplexes.txt",
				"data/cinemas.txt");
		cineplexes = (ArrayList<Cineplex>) results[0];
		cinemas = (ArrayList<Cinema>) results[1];
		showtimes = readFileWriteData.readShowtimes("data/showtime.txt", movies, cinemas);
		staffs = readFileWriteData.readStaffs("data/staffs.txt");
		tickets = readFileWriteData.readTickets("data/tickets.txt");
		movieGoers = readFileWriteData.readMovieGoer("data/moviegoer.txt",bookings,reviews);
		reviews = readFileWriteData.readReviews("data/reviews.txt",movieGoers);
		bookings = readFileWriteData.readBookings("data/bookings.txt",movieGoers,showtimes,tickets);

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
					System.out.println("LOGIN AS STAFF");
					System.out.println("Your Staff Id?");
					int staffId = sc.nextInt();
					System.out.println("Your Password?");
					String staffPassoword = sc.next();
					for(Staff staff: staffs){
						System.out.println(staff.getCinemaStaffId() + " " + staff.getPassword());
						if(staff.getCinemaStaffId() == staffId){
							if(staff.getPassword().equals(staffPassoword)) {
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
			System.out.println("[2] Showtimes");
			System.out.println("[3] Holidays");
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
					StaffUI staffUI = new StaffUI(movies,showtimes,cinemas,staff);
					staffUI.start();
					readFileWriteData.writeMovies("data/movies.txt",movies);
					break;
				case 2:
					for(Showtime s: showtimes){
						s.print();
					}
					StaffUI staffUI2 = new StaffUI(movies,showtimes,cinemas,staff);
					staffUI2.start();
					readFileWriteData.writeShowtimes("data/showtime.txt",showtimes);
					break;
				case 3: // Holidays
					holidayAdminModule();
					break;
				default:
					System.out.println("No such option.");
					break;
			}

		} while (option != 0);
	}

	/**
	 * UI interface for handling holiday manager module
	 */
	private void holidayAdminModule() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("\n===============================");
			System.out.println("=== [Holiday manager menu] ===");
			System.out.println("[1] Add new holiday");
			System.out.println("[2] Remove holiday");
			System.out.println("[3] List holidays");
			System.out.println("[4] Save holidays");
			System.out.println("[0] Return back to admin menu");
			opt = sc.nextInt();
			switch(opt) {
				case 0:
					System.out.println("returning to admin menu");
					break;
				case 1:
					System.out.println("Enter holiday name:");
					String name = sc.nextLine();
					System.out.println("Enter holiday date in yyyy-mm-dd HH:mm format");
					String date = sc.next();
					holidayManager.addNewHoliday(holidays, new Holiday(holidays.size()+1, name, StringToCalendar(date)));
					break;
				case 2:
					System.out.println("Choose holiday to be removed by 1) id\n2) name");
					int choice = sc.nextInt();
					switch(choice) {
						case 1:
							System.out.println("Enter holiday id to be removed");
							int id = sc.nextInt();
							holidayManager.removeHoliday(holidays, id);
							break;
						case 2:
							System.out.println("Enter exact holiday name to be removed");
							String holidayName = sc.nextLine();
							holidayManager.removeHoliday(holidays, holidayName);
							break;
					}
					break;
				case 3:
					System.out.println("Listing all holidays\n=============================");
					for(Holiday hol : holidays) {
						System.out.println(hol.toString());
					}
					break;
				case 4:
					System.out.println("Saving holidays to file");
					holidayManager.writeHolidaysToFile(holidays);
					break;
				default:
					System.out.println("Please select valid option");
			}
		} while(opt != 0);
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
					new ShowtimeUI(movies, showtimes, new ShowtimeManager(showtimes), new BookingManager(holidays), movieGoerObject,reviews).start();
					readFileWriteData.writeMovies("data/movies.txt",movies);
					readFileWriteData.writeReviews("data/reviews.txt",reviews);
					break;
				case 2:
					System.out.println("The following is the booking history of " + movieGoerObject.getFirstName() + " " + movieGoerObject.getLastName());
					ArrayList<Booking> bookings = new BookingManager(holidays).getBookingHistory(movieGoerObject);
					if(bookings!=null) {
						for (Booking booking : bookings) {
							System.out.println(booking);
						}
					}
					else{
						System.out.println("No booking history for this user yet");
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
}