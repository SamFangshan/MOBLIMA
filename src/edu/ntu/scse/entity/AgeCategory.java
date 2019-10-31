package edu.ntu.scse.entity;

public enum AgeCategory {
    CHILD(0, 6, 0, 0), STUDENT(7, 18, -4, -6), ADULT(19, 54, 0, 0), SENIOR(55, 200, -7, 0);

    private int minAge;
    private int maxAge;
    private double priceRegular;
    private double price3D;

    AgeCategory(int minAge, int maxAge, double priceRegular, double price3D) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.priceRegular = priceRegular;
        this.price3D = price3D;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public double getPriceRegular() {
        return priceRegular;
    }

    public double getPrice3D() {
        return price3D;
    }
}
