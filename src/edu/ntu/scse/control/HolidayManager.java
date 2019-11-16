package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

/**
 * Performs control logic operations related to holiday
 * @author Zilvinas
 */

public class HolidayManager {

//    private String filename; //TODO remove

//    public HolidayManager(String filename) { //TODO remove
//        this.filename = filename;
//    }

    /**
     * update find and replace current entry of a holiday
     * @param holidays
     * @param holiday entry to be updated
     */
    public void updateHolidays(ArrayList<Holiday> holidays, Holiday holiday) {
        boolean found = false;
        for(int i = 0; i < holidays.size(); i++) {
            if(holiday.getHolidayId() == holidays.get(i).getHolidayId()) {
                holidays.remove(holidays.get(i));   //remove old entry of the holiday
                holidays.add(holiday);              //add new entry
                found = true;
            }
        }
        if(!found) {
            System.out.println("No holiday was found to be updated.");
        }
        else {
            System.out.println("Data for holiday " + holiday.getName() + " has been updated");
        }
    }

//    /**
//     * read holidays from file
//     * @return holidays
//     */
//    public ArrayList<Holiday> readHolidays() { //TODO remove
//        ArrayList<Holiday> holidays = new ArrayList<Holiday>();
//
//        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line = "";
//            while((line = reader.readLine()) != null) {
//                String[] tokens = line.split("\\|");
//                if(tokens[0].equals("Holiday")) { //unsure about calendar date construction
////                    Calendar date = Calendar.getInstance();
////                    date.set(Calendar.DATE, Integer.parseInt(tokens[3]));
//                    holidays.add(new Holiday(Integer.parseInt(tokens[1]), tokens[2],
//                            StringToCalendar(tokens[3])));
//                }
//                else {
//                    System.out.println("Error reading data.");
//                }
//            }
//            reader.close();
//        }
//        catch (FileNotFoundException ex) {
//            System.out.println("Error: Unable to load Movie(s), file " + filename + " not found.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return holidays;
//    }

    /**
     * add new holiday to the list
     * @param holidays
     * @param holiday
     */
    public void addNewHoliday(ArrayList<Holiday> holidays, Holiday holiday) {
        holidays.add(holiday);
    }

    /**
     * remove holiday object
     * @param holidays
     * @param holiday
     */
    public void removeHoliday(ArrayList<Holiday> holidays, Holiday holiday) {
        holidays.remove(holiday);
    }

    /**
     * remove holiday by holidayId
     * @param holidays
     * @param holidayId
     */
    public void removeHoliday(ArrayList<Holiday> holidays, int holidayId) {
        for(int i = 0; i < holidays.size(); i++) {
            if(holidays.get(i).getHolidayId() == holidayId) {
                Holiday hol = holidays.get(i);
                holidays.remove(hol);
                System.out.println("Successfully removed holiday " + hol.toString());
            }
        }
    }

    /**
     * remove holiday by holiday name
     * @param holidays
     * @param holidayName
     */
    public void removeHoliday(ArrayList<Holiday> holidays, String holidayName) {
        for(int i = 0; i < holidays.size(); i++) {
            if(holidays.get(i).getName().equals(holidayName)) {
                Holiday hol = holidays.get(i);
                holidays.remove(holidays.get(i));
                System.out.println("Successfully removed holiday " + hol.toString());
            }
        }
    }

//    /**
//     * write holidays to file
//     * @param holidays
//     */
//    public void writeHolidaysToFile(ArrayList<Holiday> holidays) { //TODO remove
//        try {
//            PrintWriter out = new PrintWriter(filename);
//
//            // save all Movie
//            for (int i = 0; i < holidays.size(); i++) {
//                String line = holidays.get(i).toString(); // generate line
//                out.println(line); // add a line to text file
//            }
//
//            out.close(); // close before exit
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public Calendar StringToCalendar(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}