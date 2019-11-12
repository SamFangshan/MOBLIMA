package edu.ntu.scse.entity;

import edu.ntu.scse.factor.AgeCategory;

/**
 * Ticket entity
 */
public class Ticket {
    private int ticketId;
    private double price;
    private Seat seat;
    private AgeCategory ageCategory;

    /**
     * Constructor
     * @param ticketId
     * @param price
     * @param seat
     * @param ageCategory
     */
    public Ticket(int ticketId, double price, Seat seat, AgeCategory ageCategory) {
        this.ticketId = ticketId;
        this.price = price;
        this.seat = seat;
        this.ageCategory = ageCategory;
    }

    public int getTicketId() {
        return ticketId;
    }

    public double getPrice() {
        return price;
    }

    public Seat getSeat() {
        return seat;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    @Override
    public String toString() {
        return "Ticket" +
                "|" + ticketId +
                "|" + price +
                "|" + seat.getRowId() + seat.getColId() +
                "|" + ageCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.getPrice(), getPrice()) == 0 &&
                getSeat().equals(ticket.getSeat()) &&
                getAgeCategory() == ticket.getAgeCategory();
    }

    public String toStringConsole() {
        return "Ticket ID: " + ticketId + " Price: $" + price + " Seat: " + seat.getRowId() + seat.getColId() + " Age Cat: " + ageCategory;
    }
}
