package edu.ntu.scse.control;

import edu.ntu.scse.entity.Seat;
import edu.ntu.scse.entity.SeatStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringJoiner;

public class SeatToStringConverter {
    private static final String DELIMITER_SEPARATE = "+";
    private static final String PREFIX_SEPARATE = "|";
    private static final String SUFFIX_SEPARATE = "|";

    private static final String DELIMITER_VALUE = " | ";
    private static final String PREFIX_VALUE = "| ";
    private static final String SUFFIX_VALUE = " |";

    private ArrayList<Seat> seats;

    public SeatToStringConverter(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public String convert() {
        SeatStatus[][] seats2DArray = convertTo2DArray();
        String seatsString = convertToString(seats2DArray);
        return seatsString;
    }

    private SeatStatus[][] convertTo2DArray() {
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Integer> cols = new HashSet<Integer>();
        for (Seat seat : seats) {
            rows.add(seat.getRowId());
            cols.add(seat.getColId());
        }
        int min = Collections.min(rows);
        SeatStatus[][] seats2DArray = new SeatStatus[rows.size()][cols.size()];
        for (Seat seat : seats) {
            seats2DArray[seat.getRowId() - min][seat.getColId() - 1] = seat.isBooked();
        }
        return seats2DArray;
    }

    private String convertToString(SeatStatus[][] seats2DArray) {
        String result = "";
        StringJoiner splitJoiner = new StringJoiner(DELIMITER_SEPARATE, PREFIX_SEPARATE, SUFFIX_SEPARATE);
        for (int index = 0; index < seats2DArray[0].length; index++) {
            splitJoiner.add(String.format("%4s", "----"));
        }
        String lineSplit = splitJoiner.toString();
        for (SeatStatus[] row : seats2DArray) {
            StringJoiner sj = new StringJoiner(DELIMITER_VALUE, PREFIX_VALUE, SUFFIX_VALUE);
            for (SeatStatus col : row) {
                String status = (col == SeatStatus.BOOKED) ? "O" : ((col == SeatStatus.NOTBOOKED) ? "" : "X");
                sj.add(String.format("%2s", status));
            }
            result += lineSplit + "\n";
            result += sj.toString() + "\n";
        }
        result += lineSplit + "\n";
        return result;
    }
}
