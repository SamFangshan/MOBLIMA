package edu.ntu.scse.control;

import edu.ntu.scse.entity.Seat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringJoiner;

public class SeatToStringConverter {
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

    private ArrayList<Seat> seats;
    private char charStart;
    private char charEnd;

    public SeatToStringConverter(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public String convert() {
        char[][] seats2DArray = convertTo2DArray();
        String seatsString = convertToString(seats2DArray);
        return seatsString;
    }

    private char[][] convertTo2DArray() {
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Integer> cols = new HashSet<Integer>();
        for (Seat seat : seats) {
            rows.add(seat.getRowId());
            cols.add(seat.getColId());
        }
        charStart = Collections.min(rows);
        charEnd = Collections.max(rows);
        char[][] seats2DArray = new char[rows.size()][cols.size()];
        for (Seat seat : seats) {
            seats2DArray[rows.size() - 1 - (seat.getRowId() - charStart)][seat.getColId() - 1] = (seat.isBooked()) ? 'O' : ' ';
        }
        return seats2DArray;
    }

    private String convertToString(char[][] seats2DArray) {
        String result = "";

        StringJoiner splitJoiner = new StringJoiner(DELIMITER_COL, PREFIX_COL, SUFFIX_COL);
        for (int i = 0; i < seats2DArray[0].length; i++) {
            splitJoiner.add(String.format("%2s", String.valueOf(i + 1)));
        }
        String lineSplit = splitJoiner.toString();
        String formatString = "%" + ((lineSplit.length() - SCREEN.length())/2 + SCREEN.length()) + "s";
        result += " \t" + String.format(formatString, SCREEN) + "\n";
        result += " \t" + lineSplit + "\n";

        splitJoiner = new StringJoiner(DELIMITER_SEPARATE, PREFIX_SEPARATE, SUFFIX_SEPARATE);
        for (int i = 0; i < seats2DArray[0].length; i++) {
            splitJoiner.add(String.format("%4s", "----"));
        }
        lineSplit = splitJoiner.toString();

        for (int i = 0; i < seats2DArray.length; i++) {
            StringJoiner sj = new StringJoiner(DELIMITER_VALUE, PREFIX_VALUE, SUFFIX_VALUE);
            for (int j = 0; j < seats2DArray[0].length; j++) {
                char ch = seats2DArray[i][j];
                String status = (ch == 0) ? "X" : String.valueOf(ch);
                sj.add(String.format("%2s", status));
            }
            result += " \t" + lineSplit + "\n";
            result += "" + (char)(charEnd - i) + "\t" + sj.toString() + "\n";
        }
        result += " \t" + lineSplit + "\n";

        return result;
    }
}
