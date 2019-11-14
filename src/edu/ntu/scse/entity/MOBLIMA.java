package edu.ntu.scse.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.ntu.scse.boundary.ShowtimeUI;
import edu.ntu.scse.boundary.StaffUIMovie;
import edu.ntu.scse.boundary.StaffUIShowtime;
import edu.ntu.scse.config.PriceConfig;
import edu.ntu.scse.control.*;
import edu.ntu.scse.factor.AgeCategory;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.CinemaClass;
import edu.ntu.scse.factor.MovieType;

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
		holidayManager = new HolidayManager("data/holidays.txt");
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
		readFileWriteData.writeTickets("data/tickets.txt", tickets);
		readFileWriteData.writeBookings("data/bookings.txt", bookings);
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
						if(staff.getCinemaStaffId() == staffId){
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
					holidayAdminModule();
					break;
				case 4:
					priceAdminModule();
					break;
				case 5:
					System.out.println("Listing Top 5 Movies By ticket sales\n====================================");
					LinkedHashMap<String, Integer> ticketsSold = rankingManager.ticketSales();
					for (String i : ticketsSold.keySet()) {
						System.out.println("Movie: " + i + " Tickets sold: " + ticketsSold.get(i));
					}
					break;
				default:
					System.out.println("No such option.");
					break;
			}

		} while (option != 0);
	}

	/**
	 * Control module for price manager
	 */
	private void priceAdminModule() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;
		do {
			System.out.println("=============================");
			System.out.println("=== [Price manager menu] ===");
			System.out.println("Choose category of price type to be updated: ");
			System.out.println("[1] Age category");
			System.out.println("[2] Type of cinema");
			System.out.println("[3] Blockbuster");
			System.out.println("[4] Movie type");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			switch(opt) {
				case 0:
					break;
				case 1:
					setPriceByAgeCategory();
					break;
				case 2:
					//type menu
					setPriceByCinemaType();
					break;
				case 3:
					setPriceForBlockbuster();
					break;
				case 4:
					setPriceByMovieType();
					break;
				default:
					System.out.println("No such option");
					break;
			}
		} while(opt != 0);
	}

	/**
	 * Price controler UI for different movie types
	 */
	private void setPriceByMovieType() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Select movie type to set the price for: ");
			System.out.println("[1] Regular");
			System.out.println("[2] 3D");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch(opt) {
				case 0:
					break;
				case 1:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(MovieType.MovieType_REGULAR));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(MovieType.MovieType_REGULAR, dif);
					break;
				case 2:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(MovieType.MovieType_3D));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(MovieType.MovieType_3D, dif);
					break;
				default:
					System.out.println("No such option");
					break;
			}
		} while(opt != 0);
	}

	/**
	 * UI control for setting price by Age category
	 */
	private void setPriceByAgeCategory() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Choose age category for which you want to change the price");
			System.out.println("[1] Child");
			System.out.println("[2] Student");
			System.out.println("[3] Adult");
			System.out.println("[4] Senior");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch(opt) {
				case 0:
					break;
				case 1:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.CHILD));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(AgeCategory.CHILD, dif);
					break;
				case 2:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.STUDENT));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(AgeCategory.STUDENT, dif);
					break;
				case 3:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.ADULT));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(AgeCategory.ADULT, dif);
					break;
				case 4:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(AgeCategory.SENIOR));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(AgeCategory.SENIOR, dif);
					break;
				default:
					System.out.println("No such option");
					break;
			}
		} while(opt != 0);
	}

	/**
	 * price controler UI for cinema type
	 */
	private void setPriceByCinemaType() {
		Scanner sc = new Scanner(System.in);
		int opt = 0;

		do {
			System.out.println("============================");
			System.out.println("Choose cinema type category for which you want to change the price");
			System.out.println("[1] Standard");
			System.out.println("[2] Platinum");
			System.out.println("[0] Return");

			opt = sc.nextInt();
			double dif;
			Scanner pricer = new Scanner(System.in);
			switch(opt) {
				case 0:
					break;
				case 1:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(CinemaClass.STANDARD));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(CinemaClass.STANDARD, dif);
					break;
				case 2:
					System.out.println("Current price setting is: " + PriceConfig.getPrice(CinemaClass.PLATINUM));
					System.out.println("Enter new price setting: ");
					dif = pricer.nextDouble();
					PriceConfig.setPrice(CinemaClass.PLATINUM, dif);
					break;
				default:
					System.out.println("No such option");
					break;
			}
		} while(opt != 0);
	}

	/**
	 * price controler UI for blockbuster movies
	 */
	private void setPriceForBlockbuster() {
		Scanner sc = new Scanner(System.in);

		System.out.println("============================");
		System.out.println("Set price for blockbuster movies");
		double dif = sc.nextDouble();

		System.out.println("Current price setting is: " + PriceConfig.getPrice(Blockbuster.TRUE));
		System.out.println("Enter new price setting: ");
		PriceConfig.setPrice(Blockbuster.TRUE, dif);
		System.out.println("Price setting for blockbuster set to " + dif);
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
					System.out.println("Enter holiday date in yyyy-MM-dd HH:mm format");
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

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
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