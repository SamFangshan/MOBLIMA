package edu.ntu.scse.entity;

import java.util.Calendar;

/**
 * Represents a holiday
 *
 * @author Fangshan
 *
 */
public class Holiday {
    /**
     * Holiday ID of a holiday
     */
    private int holidayId;

    /**
     * Holiday name of a holiday
     */
    private String name;

    /**
     * Date of a holiday
     */
    private Calendar date;

    /**
     * Constructor with all attributed of holiday
     * @param holidayId
     * @param name
     * @param date
     */
    public Holiday(int holidayId, String name, Calendar date) {
        this.holidayId = holidayId;
        this.name = name;
        this.date = date;
    }

    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holiday holiday = (Holiday) o;
        return getHolidayId() == holiday.getHolidayId() &&
                getName().equals(holiday.getName()) &&
                getDate().equals(holiday.getDate());
    }

    @Override
    public String toString() {
        String holidayIdString = "Holiday ID: " + holidayId;
        String nameString = "Holiday Name: " + name;
        String dateString = "Holiday Date: " + date.get(Calendar.DATE);
        return holidayIdString + "\n" + nameString + "\n" + dateString + "\n";
    }
}
