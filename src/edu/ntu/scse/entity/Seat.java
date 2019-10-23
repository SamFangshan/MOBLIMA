package edu.ntu.scse.entity;

public class Seat {
    private char rowId;
    private int colId;
    private boolean isBooked;

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
}
