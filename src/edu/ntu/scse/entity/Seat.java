package edu.ntu.scse.entity;

/**
 * Represents a seat in a cinema
 *
 * @author Fangshan
 *
 */
public class Seat {
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
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        if (this.getRowId() == ((Seat)obj).getRowId() &&
                this.getColId() == ((Seat)obj).getColId() &&
                this.isBooked() == ((Seat)obj).isBooked()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String idString = "Seat ID: " + rowId + ": " + colId;
        String isBookedString = (isBooked) ? "Booked" : "Not Booked";
        return idString + "\n" + isBookedString + "\n";
    }
}
