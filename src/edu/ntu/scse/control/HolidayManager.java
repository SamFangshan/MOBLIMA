package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.io.*;

/**
 * HolidayManager
 * @author Zilvinas
 */

public class HolidayManager {

    private String filename;

    public HolidayManager(String filename) {
        this.filename = filename;
    }

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

    /**
     * read holidays from file
     * @return holidays
     */
    public ArrayList<Holiday> readHolidays() {
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");

                if(tokens[0].equals("Holiday")) { //unsure about calendar date construction
                    holidays.add(new Holiday(Integer.parseInt(tokens[1]), tokens[2],
                            null)); //Calendar.getInstance().set(Calendar.DATE, Integer.parseInt(tokens[3])
                }
                else {
                    System.out.println("Error reading data.");
                }
            }
            reader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error: Unable to load Movie(s), file " + filename + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holidays;
    }

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
                holidays.remove(holidays.get(i));
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
            if(holidays.get(i).getName() == holidayId) {
                holidays.remove(holidays.get(i));
            }
        }
    }

    /**
     * write holidays to file
     * @param holidays
     */
    public void writeHolidaysToFile(ArrayList<Holiday> holidays) {
        try {
            PrintWriter out = new PrintWriter(filename);

            // save all Movie
            for (int i = 0; i < holidays.size(); i++) {
                String line = holidays.get(i).toString(); // generate line
                out.println(line); // add a line to text file
            }

            out.close(); // close before exit
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}