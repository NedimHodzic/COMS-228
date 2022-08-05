package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import edu.iastate.cs228.hw1.Casual;

/**
 * @author Nedim Hodzic
 *
 * Class that makes the town depending on it the user wants to make it from a file or randomly.
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length; //setting the length variable.
		this.width = width; //setting the width variable.
		grid = new TownCell[width][length]; //setting the new grid.
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		//getting file and scanner
		File file = new File(inputFileName);
		Scanner fileScnr = new Scanner(file);

		//getting width and length from file to create the grid.
		width = fileScnr.nextInt();
		length = fileScnr.nextInt();
		grid = new TownCell[width][length];

		//loop that goes through the file that checks which state the cell should be in.
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < length; j++) {
				char testChar = fileScnr.next().charAt(0);
				//if statements that sets the cell to the proper state.
				if(testChar == 'C') {
					grid[i][j] = new Casual(this, i, j);
				}
				if(testChar == 'S') {
					grid[i][j] = new Streamer(this, i, j);
				}
				if(testChar == 'R') {
					grid[i][j] = new Reseller(this, i, j);
				}
				if(testChar == 'E') {
					grid[i][j] = new Empty(this, i, j);
				}
				if(testChar == 'O') {
					grid[i][j] = new Outage(this, i, j);
				}
			}
		}

		//closes the file
		fileScnr.close();
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		//for loop that goes through the grid, making a new random number for each cell.
		for (int i = 0; i < width; i++) {
			for(int j = 0; j < length; j++) {
				int newRandomValue = rand.nextInt(5);
				//if statements that set the cell based on the random number generated.
				if(newRandomValue == TownCell.CASUAL) {
					grid[i][j] = new Casual(this, i, j);
				}
				if(newRandomValue == TownCell.STREAMER) {
					grid[i][j] = new Streamer(this, i, j);
				}
				if(newRandomValue == TownCell.RESELLER) {
					grid[i][j] = new Reseller(this, i, j);
				}
				if(newRandomValue == TownCell.EMPTY) {
					grid[i][j] = new Empty(this, i, j);
				}
				if(newRandomValue == TownCell.OUTAGE){
					grid[i][j] = new Outage(this, i, j);
				}

			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		//for loop that runs through the grid.
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < length; j++) {
				//if statements that check which state the cell is in, adding the first letter to the String s.
				if(grid[i][j].who() == State.CASUAL) {
					s += "C ";
				}
				if(grid[i][j].who() == State.STREAMER) {
					s += "S ";
				}
				if(grid[i][j].who() == State.RESELLER) {
					s += "R ";
				}
				if(grid[i][j].who() == State.EMPTY) {
					s += "E ";
				}
				if(grid[i][j].who() == State.OUTAGE) {
					s += "O ";
				}
			}
			//adds a new line to the string that separates rows.
			s += "\n";
		}
		return s;
	}
}
