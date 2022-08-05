package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Nedim Hodzic
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		//Create the new town.
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//Run through the old town and changing each cell, then copying that change to the new town.
		for(int i = 0; i < tOld.getWidth(); i++) {
			for(int j = 0; j < tOld.getLength(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int numOfCasual = 0;
		//Run through the town, checking if each cell is a Casual and then adding to the count if it is.
		for (int i = 0; i < town.getWidth(); i++) {
			for (int j = 0; j < town.getLength(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					numOfCasual += 1;
				}
			}
		}
		return numOfCasual;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Scanner scnr = new Scanner(System.in);
		int choice;
		//Print statement that gets the choice from a user.
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		choice = scnr.nextInt();

		//Initializes the town
		Town town = null;

		//Uses the file constructor in town to make a town with the given file path.
		if(choice == 1) {
			String filePath;
			System.out.println("Please enter file path: ");
			filePath = scnr.next();
			try {
				town = new Town(filePath);
			}
			catch (FileNotFoundException e) {
				System.out.println("File was not found: " + e.getMessage());
			}
		}
		//Uses the random constructor to make a random town with the given length, width, and seed.
		else if (choice == 2) {
			int length;
			int width;
			int seed;
			System.out.println("Provide rows, cols and seed integer separated by spaces: ");
			width = scnr.nextInt();
			length = scnr.nextInt();
			seed = scnr.nextInt();

			town = new Town(length, width);

			town.randomInit(seed);
		}
		//Else statement that stops user from entering a different number than 1 or 2.
		else {
			System.out.println("Please enter 1 or 2");
		}

		double profits = 0.0;
		double row = town.getWidth();
		double collumns = town.getLength();

		//Loop that cycles through 12 months, getting a total profit and then updating the town.
		for(int i = 0; i < 12; i++) {
			profits += ((getProfit(town) / (row * collumns) * 100));
			town = updatePlain(town);
		}

		profits /= 12;

		System.out.printf("%.2f%c", profits, '%');

		scnr.close();
	}
}
