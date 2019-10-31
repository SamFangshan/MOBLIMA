package edu.ntu.scse.entity;

import java.util.Objects;

public class Ticket {
    private double price;
    private Seat seat;
    private AgeCategory ageCategory;

    public Ticket(double price, Seat seat, AgeCategory ageCategory) {
        this.price = price;
        this.seat = seat;
        this.ageCategory = ageCategory;
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
        String priceString = "Price: " + price;
        String seatString = "Seat: " + seat.getRowId() + ": " + seat.getColId();
        String ageCategoryString = "Age Category: " + ageCategory.toString();
        return priceString + "\n" + seatString + "\n" + ageCategoryString + "\n";
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
}
