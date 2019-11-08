package edu.ntu.scse.control;

import edu.ntu.scse.entity.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 */
public class HolidayManager {

    private ArrayList<Holiday> holidays;
    private String filename;

    public HolidayManager(String filename) {
        this.filename = filename;
    }

    private ArrayList<Holiday> readHolidays() {
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split(" |\\|");

                if(tokens[0].equals("Holiday")) { //unsure about calendar date construction
                    holidays.add(new Holiday(Integer.parseInt(tokens[2]), tokens[5], new Calendar(Integer.parseInt(tokens[8]))));
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
     * add new holiday
     * @param holiday
     */
    public void addNewHoliday(Holiday holiday) {
        holidays.add(holiday);
    }

    /**
     * remove holiday object
     * @param holiday
     */
    public void removeHoliday(Holiday holiday) {
        holidays.remove(holiday);
    }

    /**
     * remove holiday by holidayId
     * @param holidayId
     */
    public void removeHoliday(int holidayId) {
        for(int i = 0; i < holidays.size(); i++) {
            if(holidays.get(i).getHolidayId() == holidayId) {
                holidays.remove(holidays.get(i));
            }
        }
    }

    /**
     * remove holiday by name
     * @param holidayName
     */
    public void removeHoliday(String holidayName) {
        for(int i = 0; i < holidays.size(); i++) {
            if(holidays.get(i).getName() == holidayId) {
                holidays.remove(holidays.get(i));
            }
        }
    }
}