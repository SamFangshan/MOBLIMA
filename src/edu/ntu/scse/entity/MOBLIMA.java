package edu.ntu.scse.entity;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ntu.scse.control.ReadFileWriteData;

/**
 * MOvie Booking and LIsting Management Application (MOBLIMA) <br>
 * Act as a central class of MOBLIMA and has all the relevant data
 * 
 * @author Kailing
 *
 */
public class MOBLIMA {
	
	private ReadFileWriteData readFileWriteData;

	private ArrayList<Movie> movies;	

	/**
	 * Constructor of MOBLIMA <br>
	 * Load saved data
	 */
	public MOBLIMA() {
		readFileWriteData = new ReadFileWriteData();
		loadData();
	}

	/**
	 * Load MOBLIMA data from previous saved data
	 */
	private void loadData() {
		System.out.println("Loading data...");
		movies = readFileWriteData.initMovies("movies.txt");
		System.out.println("Loading data done.");
	};

	/**
	 * Save MOBLIMA data
	 */
	public void saveData() {		
		System.out.println("Saving data...");
		readFileWriteData.saveMovies("movies.txt", movies);
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
				System.out.println("TODO VERIFY LOGIN AS STAFF, pls add");// TODO remove/replace
				System.out.println("Currently no verification of staff login");// TODO remove/replace
				displayAdminModule();
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
	private void displayAdminModule() {
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
				// MovieUI.showMovieUI(movies); //TODO remove/replace
				System.out.println("NOT DONE LUL REPLACE CODE THX");// TODO remove/replace
				for(Movie m : movies) {// TODO remove/replace
					m.print();// TODO remove/replace
				}// TODO remove/replace
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

		do {
			System.out.println("\n================================");
			System.out.println("=== [MOBLIMA Moviegoer Main Menu] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Search/List Movie NOT DONE"); // TODO remove/replace
			System.out.println("[0] Logout");
			System.out.println("================================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to MOBLIMA Login System...");
				break;
			case 1: // Movies
				// ShowtimeUI.showShowtimeUI(movies); //TODO remove/replace
				System.out.println("NOT DONE LUL REPLACE CODE THX");// TODO remove/replace
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
