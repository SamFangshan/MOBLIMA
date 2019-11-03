package edu.ntu.scse.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieRating;
import edu.ntu.scse.entity.MovieStatus;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.MovieType;

/**
 * Read and write MOBLIMA's data from and into .txt files
 * 
 * @author Kailing
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
}
