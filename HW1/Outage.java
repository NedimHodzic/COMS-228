package edu.iastate.cs228.hw1;
/**
 * @author Nedim Hodzic
 *
 * Outage resident for the grid, this class goes through the conditions that changes the Outage user to different one.
 */
public class Outage extends TownCell{
    /**
     * Creates a new Outage object with the given town.
     *
     * @param p: The given town.
     * @param r: The given row.
     * @param c: The give collumn.
     */
    public Outage(Town p, int r, int c) {
        super(p, r, c);
    }
    /**
     * Overrides the who function to return what resident it is.
     *
     * @return
     */
    @Override
    public State who() {
        return State.OUTAGE;
    }
    /**
     * Goes through the conditions to change the Outage resident to different one.
     *
     * @param tNew: town of the next cycle
     * @return
     */
    @Override
    //Changes the Outage resident to an Empty one.
    public TownCell next(Town tNew) {
        return new Empty(tNew, row, col);
    }
}
