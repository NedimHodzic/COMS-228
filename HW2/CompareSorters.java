package edu.iastate.cs228.hw2;

/**
 *
 * @author Nedim Hodzic
 *
 */

/**
 *
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files.
	 * Use them as coordinates to construct points.  Scan these points with respect to their
	 * median coordinate point four times, each time using a different sorting algorithm.
	 *
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		//
		//
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round,
		// set up scanning as follows:
		//
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array
		//       of random points.
		//
		//    b) Reassigns to the array scanners[] (declared below) the references to four new
		//       PointScanner objects, which are created using four different values
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort.
		//
		//
		PointScanner[] scanners = new PointScanner[4];
		Random rand = new Random();
		Scanner scnr = new Scanner(System.in);

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		int choice = 0;
		int count = 1;
		while(choice != 3) {
			System.out.println("Keys: 1 (Random Integers), 2 (File Input), 3 (Exit)");
			choice = scnr.nextInt();
			if(choice == 1) {
				System.out.println("Trial " + count + ": " + choice);
				System.out.println("Enter number of random points: ");
				int numPoints = scnr.nextInt();
				scanners[0] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.QuickSort);
				scanners[1] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.MergeSort);
				scanners[2] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.InsertionSort);
				scanners[3] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.SelectionSort);
				System.out.println("Algorithm   Size  Time (ns) \n----------------------------------");
				for(int i = 0; i < 4; i++) {
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				System.out.println("----------------------------------");
				scanners[0].writeMCPToFile();
				count++;
			}
			if(choice == 2) {
				System.out.println("Trial " + count + ": " + choice);
				System.out.println("Points From a File");
				System.out.println("File name: ");
				String fileName = scnr.next();
				scanners[0] = new PointScanner(fileName, Algorithm.QuickSort);
				scanners[1] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[2] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[3] = new PointScanner(fileName, Algorithm.SelectionSort);
				System.out.println("Algorithm   Size  Time (ns) \n----------------------------------");
				for(int i = 0; i < 4; i++) {
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				System.out.println("----------------------------------");
				scanners[0].writeMCPToFile();
				count++;
			}
		}

		// For each input of points, do the following.
		//
		//     a) Initialize the array scanners[].
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan()
		//        method in the PointScanner class.
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description.
	}

	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 *
	 * Ought to be private. Made public for testing.
	 *
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if(numPts < 1) {
			throw new IllegalArgumentException("Number of points is less than 1.");
		}
		else {
			Point[] pts = new Point[numPts];
			for(int i = 0; i < numPts; i++) {
				int x = rand.nextInt(100) - 50;
				int y = rand.nextInt(100) - 50;
				pts[i] = new Point(x, y);
			}
			return pts;
		}
	}
}
