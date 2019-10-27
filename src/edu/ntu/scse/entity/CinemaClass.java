package edu.ntu.scse.entity;

/**
 * Represents classes of a cinema
 *
 * @author Fangshan
 *
 */
public enum CinemaClass {
    STANDARD("Standard: provides standard view experience.", 0), PLATINUM("Platinum: provides more luxurious view experience.", 12);

    private String desc;
    private double price;

    private CinemaClass(String desc, double price) {
        this.desc = desc;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public double getPrice() {
        return price;
    }
}
