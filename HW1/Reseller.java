package edu.iastate.cs228.hw1;
/**
 * @author Nedim Hodzic
 *
 * Reseller resident for the grid, this class goes through the conditions that changes the Reseller user to different one.
 */
public class Reseller extends TownCell{
    /**
     * Creates a new Reseller object with the given town.
     *
     * @param p: The given town.
     * @param r: The given row.
     * @param c: The give collumn.
     */
    public Reseller(Town p, int r, int c) {
        super(p, r, c);
    }
    /**
     * Overrides the who function to return what resident it is.
     *
     * @return
     */
    @Override
    public State who() {
        return State.RESELLER;
    }
    /**
     * Goes through the conditions to change the Reseller resident to different one.
     *
     * @param tNew: town of the next cycle
     * @return
     */
    @Override
    public TownCell next(Town tNew) {
        //Creates an nCensus variable to store the counts for each resident.
        int nCensus[] = new int[5];
        census(nCensus);

        //If statements that go through the rules to change Reseller.
        if(nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
            return new Empty(tNew, row, col);
        }
        else if(nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        }

        return new Casual(tNew, row, col);
    }
}
