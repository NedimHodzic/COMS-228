package edu.iastate.cs228.hw1;
/**
 * @author Nedim Hodzic
 *
 * Streamer resident for the grid, this class goes through the conditions that changes the Casual user to different one.
 */
public class Streamer extends TownCell{
    /**
     * Creates a new Streamer object with the given town.
     *
     * @param p: The given town.
     * @param r: The given row.
     * @param c: The give collumn.
     */
    public Streamer(Town p, int r, int c) { super(p, r, c); }
    /**
     * Overrides the who function to return what resident it is.
     *
     * @return
     */
    @Override
    public State who() { return State.STREAMER; }
    /**
     * Goes through the conditions to change the Streamer resident to different one.
     *
     * @param tNew: town of the next cycle
     * @return
     */
    @Override
    public TownCell next(Town tNew) {
        //Creates an nCensus variable to store the counts for each resident.
        int nCensus[] = new int[5];
        census(nCensus);

        //If statements that go through the rules to change Streamer.
        if(nCensus[OUTAGE] + nCensus[EMPTY] <= 1) {
            return new Reseller(tNew, row, col);
        }
        else if(nCensus[RESELLER] > 0) {
            return new Outage(tNew, row, col);
        }
        else if(nCensus[OUTAGE] > 0) {
            return new Empty(tNew, row, col);
        }
        else if(nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        }
        return new Streamer(tNew, row, col);
    }
}
