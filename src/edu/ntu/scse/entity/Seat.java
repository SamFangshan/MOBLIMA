package edu.ntu.scse.entity;

import java.util.Objects;

/**
 * Represents a seat in a cinema
 *
 * @author Fangshan
 *
 */
public class Seat implements Cloneable {
    /**
     * Row ID of a seat
     */
    private char rowId;

    /**
     * Column ID of a seat
     */
    private int colId;

    /**
     * Whether or not the seat is booked.
     * Its value is true when it is booked.
     */
    private boolean isBooked;

    /**
     * Default constructor of seat
     */
    public Seat() {

    }

    /**
     * Constructor with all attributes of seat
     * @param rowId
     * @param colId
     * @param isBooked
     */
    public Seat(char rowId, int colId, boolean isBooked) {
        this.rowId = rowId;
        this.colId = colId;
        this.isBooked = isBooked;
    }

    public char getRowId() {
        return rowId;
    }

    public int getColId() {
        return colId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setRowId(char rowId) {
        this.rowId = rowId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return getRowId() == seat.getRowId() &&
                getColId() == seat.getColId() &&
                isBooked() == seat.isBooked();
    }

    @Override
    public String toString() {
        return "Seat|" + rowId + colId +
                "|" + isBooked;
    }

    @Override
    public Seat clone() {
        return new Seat(rowId, colId, isBooked);
    }
}
