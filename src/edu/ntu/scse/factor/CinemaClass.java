package edu.ntu.scse.factor;

/**
 * Represents classes of a cinema
 *
 * @author Fangshan
 *
 */
public enum CinemaClass implements TicketFactor {
    STANDARD("Standard: provides standard view experience."),
    PLATINUM("Platinum: provides more luxurious view experience.");

    private String desc;

    private CinemaClass(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
