package edu.ntu.scse.config;

import edu.ntu.scse.factor.*;

import java.util.HashMap;

/**
 * Provides configuration for ticket prices
 *
 * @author fangshan
 */
public class PriceConfig {
    /**
     * Hash Map to store configuration of ticket prices
     */
    private static HashMap<TicketFactor, Double> factorToPrice;

    /**
     * Initialise pricing configuration
     */
    public static void init() {
        factorToPrice = new HashMap<TicketFactor, Double>();

        factorToPrice.put(AgeCategory.CHILD, 0.0);
        factorToPrice.put(AgeCategory.STUDENT, -2.0);
        factorToPrice.put(AgeCategory.ADULT, 0.0);
        factorToPrice.put(AgeCategory.SENIOR, -7.0);

        factorToPrice.put(CinemaClass.STANDARD, 0.0);
        factorToPrice.put(CinemaClass.PLATINUM, 12.0);

        factorToPrice.put(Blockbuster.TRUE, 1.0);
        factorToPrice.put(Blockbuster.FALSE, 0.0);

        factorToPrice.put(MovieType.MovieType_REGULAR, 11.0);
        factorToPrice.put(MovieType.MovieType_3D, 15.0);
    }

    /**
     * Get the price change based on a specific TicketFatcor
     * @param ticketFactor
     * @return price
     */
    public static double getPrice(TicketFactor ticketFactor) {
        if (factorToPrice == null) {
            System.out.println("Configuration not initialised!");
            return 0.0;
        }

        return factorToPrice.get(ticketFactor);
    }

    /**
     * Modify the pricing config of a specific TicketFactor
     * @param ticketFactor
     * @param price
     */
    public static void setPrice(TicketFactor ticketFactor, double price) {
        if (factorToPrice == null) {
            System.out.println("Configuration not initialised!");
            return;
        }

        factorToPrice.put(ticketFactor, price);
    }
}
