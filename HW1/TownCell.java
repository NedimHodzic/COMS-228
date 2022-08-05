package edu.iastate.cs228.hw1;

/**
 * 
 * @author Nedim Hodzic
 *	Class that counts the amount of residents in a neigborhood around a given central cell. Also is parent class for all
 *  the types of residents.
 *
 */
public abstract class TownCell {

	//Protected instance variables that can be used by the child classes.
	protected Town plain;
	protected int row;
	protected int col;
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	/**
	 * Creates a cell with the given town and row and column location.
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		int width = plain.getWidth();
		int length = plain.getLength();

		//Loop that will go through a 3x3 grid based off a given central cell.
		for(int i = row - 1; i < row + 2; i++) {
			for(int j = col - 1; j < col + 2; j++) {
				//If statement that checks if the loop goes out of bounds and if cell is the same as the central one.
				if(i > -1 && i < width && j > -1 && j < length && (i != row || j != col)) {
					//If statements that check which resident the cell is and adds it to its respective count.
					if(plain.grid[i][j].who() == State.RESELLER) {
						nCensus[RESELLER] += 1;
					}
					if(plain.grid[i][j].who() == State.EMPTY) {
						nCensus[EMPTY] += 1;
					}
					if(plain.grid[i][j].who() == State.CASUAL) {
						nCensus[CASUAL] += 1;
					}
					if(plain.grid[i][j].who() == State.OUTAGE) {
						nCensus[OUTAGE] += 1;
					}
					if(plain.grid[i][j].who() == State.STREAMER) {
						nCensus[STREAMER] += 1;
					}
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
