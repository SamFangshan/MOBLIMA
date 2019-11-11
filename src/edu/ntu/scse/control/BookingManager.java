package edu.ntu.scse.control;

import edu.ntu.scse.config.PriceConfig;
import edu.ntu.scse.entity.*;
import edu.ntu.scse.entity.Booking;
import edu.ntu.scse.factor.AgeCategory;
import edu.ntu.scse.factor.Blockbuster;
import edu.ntu.scse.factor.CinemaClass;
import edu.ntu.scse.factor.MovieType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Performs control logic operations related to booking
 *
 * @author Fangshan
 *
 */
public class BookingManager {
    private static final int CHILD_MIN_AGE = 0;
    private static final int CHILD_MAX_AGE = 6;
    private static final int STUDENT_MIN_AGE = 7;
    private static final int STUDENT_MAX_AGE = 18;
    private static final int ADULT_MIN_AGE = 19;
    private static final int ADULT_MAX_AGE = 54;
    private static final int SENIOR_MIN_AGE = 55;

    private static final int SIX_PM = 18;
    private static final int TID_LENGTH = 12;

    private ArrayList<Booking> bookings;
    private ArrayList<Holiday> holidays;

    public BookingManager(ArrayList<Holiday> holidays) {
        this.holidays = holidays;
        this.bookings = new ArrayList<Booking>();
    }

    /**
     * Calculate ticket price based on movie type, age category, cinema class,
     * blockbuster status, and showtime.
     * @param showtime
     * @param ageCategory
     * @return price
     */
    public double calculateTicketPrice(Showtime showtime, AgeCategory ageCategory) {
        MovieType movieType = showtime.getMovie().getMovieType();
        CinemaClass cinemaClass = showtime.getCinema().getCinemaClass();
        Blockbuster isBlockbuster = showtime.getMovie().isBlockbuster();
        boolean isHolidayOrWeekend = isHolidayOrWeekend(showtime.getScreeningTime());

        double price = PriceConfig.getPrice(movieType)
                            + PriceConfig.getPrice(cinemaClass)
                            + PriceConfig.getPrice(isBlockbuster);

        //Every one charged at normal price
        if (isHolidayOrWeekend) {
            return price;
        }

        //Child is free on a regular day
        if (ageCategory == AgeCategory.CHILD) {
            return 0;
        }

        price += PriceConfig.getPrice(ageCategory);

        return price;
    }

    /**
     * Retrieve booking history of a movie goer
     * @param movieGoer
     * @return
     */
    public ArrayList<Booking> getBookingHistory(MovieGoer movieGoer) {
        return movieGoer.getBookings();
    }

    /**
     * Create a booking for a movie goer
     * @param movieGoer
     * @param showtime
     */
    public void createBooking(MovieGoer movieGoer, Showtime showtime) {
        Scanner input = new Scanner(System.in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        double totalPrice = 0;
        while (true) {
            AgeCategory ageCategory = getAgeCategory();
            double price = calculateTicketPrice(showtime, ageCategory);
            totalPrice += price;
            Seat seat = selectSeat(showtime);
            System.out.println("Current total is: $" + totalPrice);
            tickets.add(new Ticket(tickets.size()+1, price, seat, ageCategory));
            System.out.println();
            System.out.print("Do you want to add another ticket? (Y for Yes, other character for No): ");
            String choice = input.next();
            if (choice.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }
        }
        System.out.println("Please pay $" + totalPrice + " (Y/N)");
        String choice = input.next();
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("Payment successful.");
        } else {
            System.out.println("Booking Canceled...");
            return;
        }
        Calendar currentTime = Calendar.getInstance();
        String TID = generateTID(currentTime, showtime.getCinema());
        Booking booking = new Booking(TID, currentTime, movieGoer, showtime, tickets, totalPrice);
        movieGoer.getBookings().add(booking);
        System.out.println("The following booking is successfully created: ");
        System.out.println(booking.toString());
    }

    /**
     * Generate transaction ID for a booking
     * @return
     */
    private String generateTID(Calendar currentTime, Cinema cinema) {
        String TID = "00" + String.valueOf(cinema.getCinemaId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        String strDate = dateFormat.format(currentTime.getTime());
        TID += strDate;
        return TID;
    }

    /**
     * Select a seat
     * @param showtime
     * @return seat
     */
    private Seat selectSeat(Showtime showtime) {
        Scanner input = new Scanner(System.in);
        new ShowtimeManager().displaySeatsLayout(showtime);

        Seat seat = getSeatObject(showtime);
        seat.setBooked(true);
        System.out.println(new SeatToStringConverter(showtime.getSeats()).convert());
        System.out.println("Your seat is confirmed!");
        return seat;
    }

    /**
     * Return a valid seat object based on user's entry
     * @param showtime
     * @return seat
     */
    private Seat getSeatObject(Showtime showtime) {
        Scanner input = new Scanner(System.in);
        Seat seat = null;
        while (true) {
            while (seat == null) {
                System.out.println("Please choose a seat by entering a seat ID: ");
                System.out.println("(For example, seat at row A column 1 should be A1)");
                String seatID = input.next();

                char row = seatID.charAt(0);
                if (!(('a' <= row && row <= 'z') || ('A' <= row && row <= 'Z'))) {
                    System.out.println("Invalid seat ID! Please try again.");
                    continue;
                }
                String colString = seatID.substring(1);
                int col;
                try {
                    col = Integer.parseInt(colString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid seat ID! Please try again.");
                    continue;
                }
                seat = new Seat(Character.toUpperCase(row), col, false);
            }

            ArrayList<Seat> seats = showtime.getSeats();
            for (Seat seat1 : seats) {
                if (seat.equals(seat1)) {
                    return seat1;
                } else if (seat.getColId() == seat1.getColId() &&
                        seat.getRowId() == seat1.getRowId() &&
                        seat.isBooked() != seat1.isBooked()) {
                    System.out.println("Sorry, this seat is already occupied! Please try again.");
                    continue;
                }
            }
            System.out.println("Sorry, this seat ID does not match any valid seats! Please try again.");
        }

    }

    /**
     * Get age category based on age
     * @return
     */
    private AgeCategory getAgeCategory() {
        Scanner input = new Scanner(System.in);
        AgeCategory ageCategory;
        while (true) {
            System.out.print("Enter the age of the movie goer: ");
            int age = input.nextInt();
            if (CHILD_MIN_AGE <= age && age <=  CHILD_MAX_AGE) {
                ageCategory = AgeCategory.CHILD; break;
            } else if (STUDENT_MIN_AGE <= age && age <=  STUDENT_MAX_AGE) {
                ageCategory = AgeCategory.STUDENT; break;
            } else if (ADULT_MIN_AGE <= age && age <= ADULT_MAX_AGE) {
                ageCategory = AgeCategory.ADULT; break;
            } else if (SENIOR_MIN_AGE <= age) {
                ageCategory = AgeCategory.SENIOR; break;
            } else {
                System.out.println("Invalid age entered! Please try again.");
            }
        }
        return ageCategory;
    }

    /**
     * Check if a showtime is on a holiday / weekend or not
     * @param screeningTime
     * @return isHolidayOrWeekend
     */
    private boolean isHolidayOrWeekend(Calendar screeningTime) {
        for (Holiday holiday : holidays) {
            if (holiday.getDate().get(Calendar.DATE) == screeningTime.get(Calendar.DATE)
                    && holiday.getDate().get(Calendar.MONTH) == screeningTime.get(Calendar.MONTH)
                    && holiday.getDate().get(Calendar.YEAR) == screeningTime.get(Calendar.YEAR)) {
                return true;
            }
        }
        if (screeningTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || screeningTime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }
        if (screeningTime.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            if (screeningTime.get(Calendar.HOUR_OF_DAY) >= SIX_PM) {
                return true;
            }
        }
        return false;
    }
}
