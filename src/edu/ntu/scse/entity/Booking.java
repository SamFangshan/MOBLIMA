package edu.ntu.scse.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Booking {
    private String TID;
    private Calendar transactionTime;
    private double totalPrice;
    private MovieGoer movieGoer;
    private Showtime showTime;
    private ArrayList<Ticket> tickets;

    public Booking(String TID, Calendar transactionTime, MovieGoer movieGoer,
                   Showtime showTime, ArrayList<Ticket> tickets, double totalPrice) {
        this.TID = TID;
        this.transactionTime = transactionTime;
        this.totalPrice = totalPrice;
        this.movieGoer = movieGoer;
        this.showTime = showTime;
        this.tickets = tickets;
    }

    public String getTID() {
        return TID;
    }

    public Calendar getTransactionTime() {
        return transactionTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public Showtime getShowTime() {
        return showTime;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public void setTransactionTime(Calendar transactionDate) {
        this.transactionTime = transactionTime;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    public void setShowTime(Showtime showTime) {
        this.showTime = showTime;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Double.compare(booking.getTotalPrice(), getTotalPrice()) == 0 &&
                getTID().equals(booking.getTID()) &&
                getTransactionTime().equals(booking.getTransactionTime()) &&
                getMovieGoer().equals(booking.getMovieGoer()) &&
                getShowTime().equals(booking.getShowTime()) &&
                getTickets().equals(booking.getTickets());
    }

    @Override
    public String toString() {
        String TIDString = "TID: " + TID;
        String transactionTimeString = "Transaction Time: " + transactionTime.toString();
        String totalPriceString = "Total Price: $" + totalPrice;
        String movieGoerString = "Movie Goer: " + movieGoer.getFirstName() + " " + movieGoer.getLastName();
        String showTimeString = showTime.toString();
        String ticketsString = "Tickets: \n";
        for (Ticket ticket : tickets) {
            ticketsString += ticket.toString();
        }

        return TIDString + "\n" + transactionTimeString + "\n" + totalPriceString + "\n"
                + movieGoerString + "\n" + showTimeString + "\n" + ticketsString + "\n";
    }
}
