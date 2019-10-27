package edu.ntu.scse.control;

import edu.ntu.scse.entity.Seat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringJoiner;

/**
 * Converts an ArrayList of seats to its visual arrangement represented by a String
 *
 * @author Fangshan
 *
 */
public class SeatToStringConverter {
    /**
     * Constant declarations
     */
    private static final String DELIMITER_COL = "   ";
    private static final String PREFIX_COL = "  ";
    private static final String SUFFIX_COL = "  ";

    private static final String DELIMITER_SEPARATE = "+";
    private static final String PREFIX_SEPARATE = "|";
    private static final String SUFFIX_SEPARATE = "|";

    private static final String DELIMITER_VALUE = " | ";
    private static final String PREFIX_VALUE = "| ";
    private static final String SUFFIX_VALUE = " |";

    private static final String SCREEN = "SCREEN";

    /**
     * The list of seats that this converter keeps track of
     */
    private ArrayList<Seat> seats;

    /**
     * The smallest character of all row IDs by alphabetical order
     */
    private char charStart;

    /**
     * The biggest character of all row IDs by alphabetical order
     */
    private char charEnd;

    /**
     * Constructor with attributes seats
     * @param seats
     */
    public SeatToStringConverter(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    /**
     * Converts an ArrayList of seats to a String according to their visual arrangements
     * @return seatsString
     */
    public String convert() {
        char[][] seats2DArray = convertTo2DArray();
        String seatsString = convertToString(seats2DArray);
        return seatsString;
    }

    /**
     * Converts an ArrayList of seats to a 2D array according to their visual arrangements
     * @return seats2DArray
     */
    private char[][] convertTo2DArray() {
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Integer> cols = new HashSet<Integer>();
        //Get sets of all row IDs and column IDs used
        for (Seat seat : seats) {
            rows.add(seat.getRowId());
            cols.add(seat.getColId());
        }
        charStart = Collections.min(rows);
        charEnd = Collections.max(rows);

        char[][] seats2DArray = new char[rows.size()][cols.size()];
        //Assumption: column IDs always start from 1 and are continuous. (e.g. 1, 2, 3, 4, 5)
        for (Seat seat : seats) {
            //If a seat is already booked, it is represented by an O; otherwise it is represented by an empty space.
            seats2DArray[rows.size() - 1 - (seat.getRowId() - charStart)][seat.getColId() - 1] = (seat.isBooked()) ? 'O' : ' ';
        }
        return seats2DArray;
    }

    /**
     * Converts a 2D array of seats to a String according to their visual arrangements
     * @param seats2DArray
     * @return String
     */
    private String convertToString(char[][] seats2DArray) {
        String result = "";

        //Prepare the column IDs to be printed
        StringJoiner splitJoiner = new StringJoiner(DELIMITER_COL, PREFIX_COL, SUFFIX_COL);
        for (int i = 0; i < seats2DArray[0].length; i++) {
            splitJoiner.add(String.format("%2s", String.valueOf(i + 1)));
        }
        String lineSplit = splitJoiner.toString();
        //Prepares the position that the screen should be printed
        String formatString = "%" + ((lineSplit.length() - SCREEN.length())/2 + SCREEN.length()) + "s";

        result += " \t" + String.format(formatString, SCREEN) + "\n";
        result += " \t" + lineSplit + "\n";

        //Prepares the separation lines between rows of seats
        splitJoiner = new StringJoiner(DELIMITER_SEPARATE, PREFIX_SEPARATE, SUFFIX_SEPARATE);
        for (int i = 0; i < seats2DArray[0].length; i++) {
            splitJoiner.add(String.format("%4s", "----"));
        }
        lineSplit = splitJoiner.toString();

        //Converts 2D array of seats to a String
        for (int i = 0; i < seats2DArray.length; i++) {
            StringJoiner sj = new StringJoiner(DELIMITER_VALUE, PREFIX_VALUE, SUFFIX_VALUE);
            for (int j = 0; j < seats2DArray[0].length; j++) {
                char ch = seats2DArray[i][j];
                String status = (ch == 0) ? "X" : String.valueOf(ch); //X means this seat does not exist
                sj.add(String.format("%2s", status));
            }
            result += " \t" + lineSplit + "\n";
            result += "" + (char)(charEnd - i) + "\t" + sj.toString() + "\n";
        }
        result += " \t" + lineSplit + "\n";

        return result;
    }
}
