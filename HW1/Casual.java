package edu.iastate.cs228.hw1;
/**
 * @author Nedim Hodzic
 *
 * Casual resident for the grid, this class goes through the conditions that changes the Casual user to different one.
 */
public class Casual extends TownCell{
    /**
     * Creates a new Casual object with the given town.
     *
     * @param p: The given town.
     * @param r: The given row.
     * @param c: The give collumn.
     */
    public Casual(Town p, int r, int c) {
        super(p, r, c);
    }
    /**
     * Overrides the who function to return what resident it is.
     *
     * @return
     */
    @Override
    public State who() {
        return State.CASUAL;
    }
    /**
     * Goes through the conditions to change the Casual resident to different one.
     *
     * @param tNew: town of the next cycle
     * @return
     */
    @Override
    public TownCell next(Town tNew) {
        //Creates an nCensus variable to store the counts for each resident.
        int nCensus[] = new int[5];
        census(nCensus);

        //If statements that go through the rules to change Casual.
        if(nCensus[OUTAGE] + nCensus[EMPTY] <= 1) {
            return new Reseller(tNew, row, col);
        }
        else if(nCensus[RESELLER] > 0) {
            return new Outage(tNew, row, col);
        }
        else if(nCensus[STREAMER] > 0) {
            return new Streamer(tNew, row, col);
        }
        else if(nCensus[CASUAL] >= 5){
            return new Streamer(tNew, row, col);
        }
        return new Casual(tNew, row, col);
    }
}
