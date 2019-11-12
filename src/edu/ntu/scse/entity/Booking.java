package edu.ntu.scse.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a booking
 *
 * @author Fangshan
 *
 */
public class Booking {
    /**
     * Transaction ID of a booking
     */
    private String TID;

    /**
     * Transaction time of a booking
     */
    private Calendar transactionTime;

    /**
     * Total price of a booking
     */
    private double totalPrice;

    /**
     * Movie goer of a booking
     */
    private MovieGoer movieGoer;

    /**
     * Showtime of a booking
     */
    private Showtime showTime;

    /**
     * Tickets of a booking
     */
    private ArrayList<Ticket> tickets;

    /**
     * Constructor with all attributes of booking
     * @param TID
     * @param transactionTime
     * @param movieGoer
     * @param showTime
     * @param tickets
     * @param totalPrice
     */
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

    public void setTransactionTime(Calendar transactionTime) {
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

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(showTime.getScreeningTime().getTime());
        return "Booking" +
                "|" + TID +
                "|" + strDate +
                "|" + movieGoer.getMovieGoerId() +
                "|" + showTime.getShowtimeId() +
                "|" + ticketsToString(tickets) +
                "|" + totalPrice;
    }

    private String ticketsToString(ArrayList<Ticket> tickets) {
        String s = "";
        for (int i = 0; i < tickets.size(); i++) {
            s += tickets.get(i).getTicketId();
            if (i < tickets.size() - 1) {
                s += ",";
            }
        }
        return s;
    }

    public String toStringConsole() {
        String TIDString = "TID: " + TID;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(transactionTime.getTime());
        String transactionTimeString = "Transaction Time: " + strDate;
        String totalPriceString = "Total Price: $" + totalPrice;
        String movieGoerString = "Movie Goer: " + movieGoer.getEmail();
        String showTimeString = showTime.toStringConsole();
        String ticketsString = "Tickets: \n";
        for (Ticket ticket : tickets) {
            ticketsString += ticket.toStringConsole() + "\n";
        }

        return TIDString + "\n" + transactionTimeString + "\n" + totalPriceString + "\n"
                + movieGoerString + "\n" + showTimeString + "\n" + ticketsString + "\n";
    }
}
