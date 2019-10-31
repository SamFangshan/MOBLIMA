package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class BookingManager {
    private static final double BLOCK_BUSTER_ADDITIONAL_CHARGE = 1;
    private static final int SIX_PM = 18;
    private static final int TID_LENGTH = 12;
    private ArrayList<Booking> bookings;
    private ArrayList<Holiday> holidays;

    public BookingManager(ArrayList<Holiday> holidays) {
        this.holidays = holidays;
        this.bookings = new ArrayList<Booking>();
    }

    public double calculateTicketPrice(Showtime showtime, AgeCategory ageCategory) {
        MovieType movieType = showtime.getMovie().getMovieType();
        CinemaClass cinemaClass = showtime.getCinema().getCinemaClass();
        boolean isHolidayOrWeekend = isHolidayOrWeekend(showtime.getScreeningTime());

        double price = movieType.getPrice() + cinemaClass.getPrice();
        if (isHolidayOrWeekend) {
            return price;
        }

        if (ageCategory == AgeCategory.CHILD) {
            return 0;
        }

        if (movieType == MovieType.MovieType_3D) {
            price += ageCategory.getPrice3D();
        } else {
            price += ageCategory.getPriceRegular();
        }

        return price;
    }

    public ArrayList<Booking> getBookingHistory(MovieGoer movieGoer) {
        return movieGoer.getBookings();
    }

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
            tickets.add(new Ticket(price, seat, ageCategory));
            System.out.println();
            System.out.print("Do you want to add another ticket? (Y for Yes, other character for No): ");
            String choice = input.next();
            if (choice.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }
        }
        String TID = generateTID();
        Calendar currentTime = Calendar.getInstance();
        Booking booking = new Booking(TID, currentTime, movieGoer, showtime, tickets, totalPrice);
        movieGoer.getBookings().add(booking);
        System.out.println("The following booking is successfully created: ");
        System.out.println(booking.toString());
    }

    private String generateTID() {
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String TID = "";
        for (int i = 0; i < TID_LENGTH; i++) {
            int index = (int)(characterSet.length() * Math.random());
            TID += characterSet.charAt(index);
        }
        return TID;
    }

    private Seat selectSeat(Showtime showtime) {
        Scanner input = new Scanner(System.in);
        System.out.println(new SeatToStringConverter(showtime.getSeats()).convert());
        System.out.println("O indicates that the seat is already booked.");
        System.out.println("X indicates that this seat does not exist.");
        System.out.println("You can choose from any empty seats.\n");

        Seat seat = getSeatObject(showtime);
        seat.setBooked(true);
        System.out.println(new SeatToStringConverter(showtime.getSeats()).convert());
        System.out.println("Your seat is confirmed!");
        return seat;
    }

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

    private AgeCategory getAgeCategory() {
        Scanner input = new Scanner(System.in);
        AgeCategory ageCategory;
        while (true) {
            System.out.print("Enter the age of the movie goer: ");
            int age = input.nextInt();
            if (AgeCategory.CHILD.getMinAge() <= age && age <=  AgeCategory.CHILD.getMaxAge()) {
                ageCategory = AgeCategory.CHILD; break;
            } else if (AgeCategory.STUDENT.getMinAge() <= age && age <=  AgeCategory.STUDENT.getMaxAge()) {
                ageCategory = AgeCategory.STUDENT; break;
            } else if (AgeCategory.ADULT.getMinAge() <= age && age <=  AgeCategory.ADULT.getMaxAge()) {
                ageCategory = AgeCategory.ADULT; break;
            } else if (AgeCategory.SENIOR.getMinAge() <= age && age <=  AgeCategory.SENIOR.getMaxAge()) {
                ageCategory = AgeCategory.SENIOR; break;
            } else {
                System.out.println("Invalid age entered! Please try again.");
            }
        }
        return ageCategory;
    }

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
