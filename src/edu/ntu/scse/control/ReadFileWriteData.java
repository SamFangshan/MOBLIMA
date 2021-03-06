
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
	public ArrayList<Movie> readMovies(String filename,ArrayList<Review> reviews) {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Movie")) { // Movie
					if(reviews == null || tokens[11].equals("")){
					movies.add(new Movie(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5],
							Blockbuster.valueOf(tokens[6].toUpperCase()), Float.parseFloat(tokens[7]),
							MovieRating.valueOf(tokens[8]), MovieStatus.valueOf(tokens[9]),
							MovieType.valueOf(tokens[10]),new ArrayList<Review>(),StringToCalendar(tokens[12])));}
					else{
					movies.add(new Movie(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5],
							Blockbuster.valueOf(tokens[6].toUpperCase()), Float.parseFloat(tokens[7]),
							MovieRating.valueOf(tokens[8]), MovieStatus.valueOf(tokens[9]),
							MovieType.valueOf(tokens[10]),StringToReviews(tokens[11],reviews),StringToCalendar(tokens[12])));}
					if (movies.get(movies.size()-1).getEndOfShowDate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
						movies.get(movies.size()-1).setMovieStatus(MovieStatus.END_OF_SHOWING);
					}
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
	 * @return Cineplexes and Cinemas
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

	/**
	 * Initialize MOBLIMA's Booking(s) from a text file
	 * @param filename
	 * @return bookings
	 */
	public ArrayList<Booking> readBookings(String filename, ArrayList<MovieGoer> movieGoers, ArrayList<Showtime> showtimes, ArrayList<Ticket> tickets) {
		ArrayList<Booking> bookings = new ArrayList<Booking>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Booking")) {
					// id
					// transaction time
					// movie goer(Id)
					// showtime(Id)
					// tickets
					// total price

					bookings.add(new Booking(tokens[1], StringToCalendar(tokens[2]), movieGoers.get(Integer.parseInt(tokens[3])-1), showtimes.get(Integer.parseInt(tokens[4])-1), StringToTickets(tokens[5],tickets), Double.parseDouble(tokens[6])));
					movieGoers.get(Integer.parseInt(tokens[3])-1).getBookings().add(bookings.get(bookings.size()-1));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Booking(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		initialiseBookedSeats(bookings);

		return bookings;
	}

	/**
	 * Save all Booking(s) into a text file
	 * @param filename
	 * @param bookings
	 */
	public void writeBookings(String filename, ArrayList<Booking> bookings) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < bookings.size(); i++) {
				String line = bookings.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialisation for booked seats
	 * @param bookings
	 */
	private void initialiseBookedSeats(ArrayList<Booking> bookings) {
		for (Booking booking : bookings) {
			ArrayList<Seat> seatArrayList = new ArrayList<Seat>();
			for (Ticket ticket : booking.getTickets()) {
				Seat seat = ticket.getSeat();
				for (Seat showtimeSeat : booking.getShowTime().getSeats()) {
					if (seat.equals(showtimeSeat)) {
						seat.setBooked(true);
						showtimeSeat.setBooked(true);
					}
				}
			}
		}
	}
	
	/**
     * read holidays from file
     * @return holidays
     */
    public ArrayList<Holiday> readHolidays(String filename) { 
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if(tokens[0].equals("Holiday")) { //unsure about calendar date construction
//                    Calendar date = Calendar.getInstance();
//                    date.set(Calendar.DATE, Integer.parseInt(tokens[3]));
                    holidays.add(new Holiday(Integer.parseInt(tokens[1]), tokens[2],
                            StringToCalendar(tokens[3])));
                }
                else {
                    System.out.println("Error reading data.");
                }
            }
            reader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error: Unable to load Movie(s), file " + filename + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holidays;
    }
    
    /**
     * write holidays to file
     * @param holidays
     */
    public void writeHolidaysToFile(String filename, ArrayList<Holiday> holidays) {
        try {
            PrintWriter out = new PrintWriter(filename);

            // save all Movie
            for (int i = 0; i < holidays.size(); i++) {
                String line = holidays.get(i).toString(); // generate line
                out.println(line); // add a line to text file
            }

            out.close(); // close before exit
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Initialize MOBLIMA's Staff(s) from a text file
	 * @param filename
	 * @return staffs
	 */
	public ArrayList<Staff> readStaffs(String filename) {
		ArrayList<Staff> staffs = new ArrayList<Staff>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Staff")) {
					// Staff ID
					// Password
					staffs.add(new Staff(Integer.parseInt(tokens[1]), tokens[2]));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Staff(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return staffs;
	}

	/**
	 * Save all Staff(s) into a text file
	 * @param filename
	 * @param staffs
	 */
	public void writeStaffs(String filename, ArrayList<Ticket> staffs) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < staffs.size(); i++) {
				String line = staffs.get(i).toString(); // generate line
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
					if(bookings.size() == 0 || reviews.size() == 0){
						movieGoers.add(new MovieGoer(tokens[1],Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), new ArrayList<Booking>(), null));
					}
					else {
						movieGoers.add(new MovieGoer(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), StringToBooknigs(tokens[4], bookings), StringToReviews(tokens[5], reviews)));
					}
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


	public ArrayList<Review> readReviews(String filename, ArrayList<MovieGoer> movieGoers){
		ArrayList<Review> reviews = new ArrayList<Review>();

		// read/load data from text file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Review")) {
					// Staff ID
					// Password
					reviews.add(new Review(Integer.parseInt(tokens[1]), tokens[2],Integer.parseInt(tokens[3]),movieGoers.get(Integer.parseInt(tokens[4])-1)));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to load Review(s), file " + filename + " not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reviews;
	}

	public void writeReviews(String filename, ArrayList<Review> reviews) {
		// output to text
		try {
			PrintWriter out = new PrintWriter(filename);

			for (int i = 0; i < reviews.size(); i++) {
				String line = reviews.get(i).toString(); // generate line
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

		for (String token : tokens) {
			bookingsSelected.add(bookings.get(Integer.parseInt(token) - 1));
		}
		return bookingsSelected;
	}

	/**
	 * Parse a string of Ticket IDs and return the Tickets selected
	 * @param s
	 * @param tickets
	 * @return
	 */
	private ArrayList<Ticket> StringToTickets(String s, ArrayList<Ticket> tickets){
		String[] tokens = s.split(",");

		ArrayList<Ticket> ticketsSelected = new ArrayList<>();

		for (String token : tokens) {
			ticketsSelected.add(tickets.get(Integer.parseInt(token) - 1));
		}
		return ticketsSelected;
	}

}