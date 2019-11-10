
package edu.ntu.scse.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.ntu.scse.entity.*;
import edu.ntu.scse.factor.AgeCategory;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.CinemaClass;
import edu.ntu.scse.factor.MovieType;

/**
 * Read and write MOBLIMA's data from and into .txt files
 *
 * @author Kailing, Fangshan
 *
 */
public class ReadFileWriteData {
	/**
	 * Initialize MOBLIMA's Movie(s) from a text file
	 */
	public ArrayList<Movie> readMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Movie")) { // Movie
					// TODO add reviews Id
					movies.add(new Movie(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5],
							Blockbuster.valueOf(tokens[6].toUpperCase()), Float.parseFloat(tokens[7]),
							MovieRating.valueOf(tokens[8]), MovieStatus.valueOf(tokens[9]),
							MovieType.valueOf(tokens[10])));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Movie(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}

	/**
	 * Save all Movie into a text file
	 * @param filename
	 * @param movies
	 */
	public void writeMovies(String filename, ArrayList<Movie> movies) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			// save all Movie
			for (int i = 0; i < movies.size(); i++) {
				String line = movies.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize MOBLIMA's Cineplex(es) and Cinema(s) from a text file
	 * @param cineplexFile
	 * @param cinemaFile
	 * @return Cineplexes & Cinemas
	 */
	public Object[] readCineplexesAndCinemas(String cineplexFile, String cinemaFile) {
		Object[] result = new Object[2];

		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		ArrayList<Integer> cineplexIds = new ArrayList<Integer>();
		ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(cinemaFile))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Cinema")) {
					cinemas.add(new Cinema(Integer.parseInt(tokens[1]), stringListToSeats(tokens[2]), CinemaClass.valueOf(tokens[4])));
					cineplexIds.add(Integer.parseInt(tokens[3]));
				} else {
					System.out.println("Error reading data.");
				}
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Cinema(s), file " + cinemaFile + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(cineplexFile))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Cineplex")) {
					cineplexes.add(new Cineplex(Integer.parseInt(tokens[1]), tokens[2], cinemaIdsToObjects(tokens[3], cinemas)));
				} else {
					System.out.println("Error reading data.");
				}
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Cineplex(es), file " + cineplexFile + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < cinemas.size(); i++) {
			cinemas.get(i).setCineplex(cineplexes.get(cineplexIds.get(i) - 1));
		}

		result[0] = cineplexes;
		result[1] = cinemas;
		return result;
	}

	/**
	 * Initialize MOBLIMA's Showtime(s) from a text file
	 * @param filename
	 * @param movies
	 * @param cinemas
	 * @return showtimes
	 */
	public ArrayList<Showtime> readShowtimes(String filename, ArrayList<Movie> movies, ArrayList<Cinema> cinemas) {
		ArrayList<Showtime> showtimes = new ArrayList<Showtime>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Showtime")) {
					showtimes.add(new Showtime(Integer.parseInt(tokens[1]), StringToCalendar(tokens[2]), cinemas.get(Integer.parseInt(tokens[4]) - 1),
							movies.get(Integer.parseInt(tokens[3]) - 1), copySeats(cinemas.get(Integer.parseInt(tokens[4]) - 1))));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Showtime(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return showtimes;
	}

	/**
	 * Save all Showtime(s) into a text file
	 * @param filename
	 * @param showtimes
	 */
	public void writeShowtimes(String filename, ArrayList<Showtime> showtimes) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < showtimes.size(); i++) {
				String line = showtimes.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize MOBLIMA's Ticket(s) from a text file
	 * @param filename
	 * @return tickets
	 */
	public ArrayList<Ticket> readTickets(String filename) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Ticket")) {
					tickets.add(new Ticket(Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]), stringListToSeats(tokens[3]).get(0), AgeCategory.valueOf(tokens[4])));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Ticket(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	/**
	 * Save all Ticket(s) into a text file
	 * @param filename
	 * @param tickets
	 */
	public void writeTickets(String filename, ArrayList<Ticket> tickets) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < tickets.size(); i++) {
				String line = tickets.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private ArrayList<Seat> stringListToSeats(String seatsString) {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		String[] tokens = seatsString.split(",");
		for (String token : tokens) {
			char row = token.charAt(0);
			int col = Integer.parseInt(token.substring(1));
			seats.add(new Seat(row, col, false));
		}
		return seats;
	}

	private ArrayList<Cinema> cinemaIdsToObjects(String ids, ArrayList<Cinema> cinemas) {
		ArrayList<Cinema> cinemaArrayList = new ArrayList<Cinema>();
		String[] tokens = ids.split(",");
		for (String token : tokens) {
			int id = Integer.parseInt(token);
			cinemaArrayList.add(cinemas.get(id - 1));
		}
		return cinemaArrayList;
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

	private ArrayList<Seat> copySeats(Cinema cinema) {
		ArrayList<Seat> seats1 = cinema.getSeats();
		ArrayList<Seat> seats2 = new ArrayList<Seat>();
		for (int i = 0; i < seats1.size(); i++) {
			seats2.add(seats1.get(i).clone());
		}
		return seats2;
	}

	/**
	 * Initialize moviegoers
	 * @param filename
	 * @param bookings
	 * @param reviews
	 * @return
	 */
	public ArrayList<MovieGoer> readMovieGoer(String filename, ArrayList<Booking> bookings, ArrayList<Review> reviews) {
		ArrayList<MovieGoer> movieGoers = new ArrayList<>();

		//read data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) {

				String[] tokens = line.split("\\|");
				if (tokens[0].equals("MovieGoer")) {
					//id
					//age
					//use transaction id to find the Booking
					//use review ids(a list of integers separated by comma) to find the Reviews
					movieGoers.add(new MovieGoer(tokens[1],Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), StringToBooknigs(tokens[4], bookings), StringToReviews(tokens[5], reviews)));
				} else {
					System.out.println("Error reading data.");
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load MovieGoer(s) " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movieGoers;
	}

	public void writeMovieGoers(String filename, ArrayList<MovieGoer> movieGoers) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < movieGoers.size(); i++) {
				String line = movieGoers.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Parse a string of review IDs and return the reviews selected
	 *
	 * @param s:string of review IDs
	 * @param reviews: reviews(read from txt file)
	 * @return
	 */
	private ArrayList<Review> StringToReviews(String s, ArrayList<Review> reviews) {
		String[] tokens = s.split(",");

		ArrayList<Review> reviewsSelected = new ArrayList<>();
		int id;
		for (String token : tokens) {
			reviewsSelected.add(reviews.get(Integer.parseInt(token) - 1));
		}
		return reviewsSelected;
	}

	/**
	 * Parse a string of Booking(transaction) IDs and return the Bookings selected
	 *
	 * @param s
	 * @param bookings: reviews(read from txt file)
	 * @return
	 */
	private ArrayList<Booking> StringToBooknigs(String s, ArrayList<Booking> bookings) {
		String[] tokens = s.split(",");

		ArrayList<Booking> bookingsSelected = new ArrayList<>();
		int id;
		for (String token : tokens) {
			bookingsSelected.add(bookings.get(Integer.parseInt(token) - 1));
		}
		return bookingsSelected;
	}

}