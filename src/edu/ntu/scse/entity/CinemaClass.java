package edu.ntu.scse.entity;

public enum CinemaClass {
    STANDARD("provides standard view experience.", 0), PLATINUM("provides more luxurious view experience.", 12);

    private String desc;
    private double price;

    private CinemaClass(String desc, double price) {
        this.desc = this.toString();
        this.price = price;
    }

    private String getDescription() {
        return this.toString() + ": " + this.desc;
    }
}
