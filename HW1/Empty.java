package edu.iastate.cs228.hw1;
/**
 * @author Nedim Hodzic
 *
 * Streamer resident for the grid, this class goes through the conditions that changes the Casual user to different one.
 */
public class Empty extends TownCell{
    /**
     * Creates a new Empty object with the given town
     *
     * @param p: The given town.
     * @param r: The given row.
     * @param c: The give collumn.
     */
    public Empty(Town p, int r, int c) { super(p, r, c); }
    /**
     * Overrides the who function to return what resident it is.
     *
     * @return
     */
    @Override
    public State who() { return State.EMPTY; }
    /**
     * Goes through the conditions to change the Empty resident to different one.
     *
     * @param tNew: town of the next cycle
     * @return
     */
    @Override
    public TownCell next(Town tNew) {
        //Creates an nCensus variable to store the counts for each resident.
        int nCensus[] = new int[5];
        census(nCensus);

        //If statements that go through the rules to change Empty.
        if(nCensus[OUTAGE] + nCensus[EMPTY] <= 1) {
            return new Reseller(tNew, row, col);
        }
        else {
            return new Casual(tNew, row, col);
        }

    }
}
