package edu.ntu.scse.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.ntu.scse.entity.Movie;
import edu.ntu.scse.entity.MovieRating;
import edu.ntu.scse.entity.MovieStatus;
import edu.ntu.scse.entity.MovieType;

/**
 * Read and write MOBLIMA's data from and into .txt files
 * 
 * @author Kailing
 *
 */
public class ReadFileWriteData {
	/**
	 * Initialize MOBLIMA's Movie(s) from movies.txt
	 */
	public ArrayList<Movie> initMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		// read/load data from text file, data.txt
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) { // check and read next line
				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("Movie")) { // Movie
					// TODO add reviews Id
					movies.add(new Movie(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5],
							Boolean.parseBoolean(tokens[6]), Float.parseFloat(tokens[7]),
							MovieRating.valueOf(tokens[8]), MovieStatus.valueOf(tokens[9]),
							MovieType.valueOf(tokens[10])));
				} else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("No previous data found for Movie(s). Creating new txt file...");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}
}
