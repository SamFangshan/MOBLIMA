package edu.ntu.scse.control;

import edu.ntu.scse.entity.Seat;
import edu.ntu.scse.entity.SeatStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SeatToStringConverter {
    private ArrayList<Seat> seats;

    public SeatToStringConverter(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public String convert() {
        SeatStatus[][] seats2DArray = convertTo2DArray();
        String seatsString = convertToString();
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
}
