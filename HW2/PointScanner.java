package edu.iastate.cs228.hw2;

/**
 * @author Nedim Hodzic
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y
 * coordinates are respectively the medians of the x and y coordinates of the original points.
 *
 * It records the employed sorting algorithm as well as the sorting time for comparison.
 *
 */
public class PointScanner {
    private Point[] points;

    private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of
    // the x coordinates and y coordinates of those points in the array points[].
    private Algorithm sortingAlgorithm;

    protected long scanTime;           // execution time in nanoseconds.

    /**
     * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy
     * the points into the array points[].
     *
     * @param  pts  input array of points
     * @throws IllegalArgumentException if pts == null or pts.length == 0.
     */
    public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException("Points array is null or the length is 0.");
        } else {
            points = pts;
            sortingAlgorithm = algo;
        }
    }

    /**
     * This constructor reads points from a file.
     *
     * @param  inputFileName
     * @throws FileNotFoundException
     * @throws InputMismatchException   if the input file contains an odd number of integers
     */
    protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
        File file;
        Scanner filescnr;
        int count = 0;
        sortingAlgorithm = algo;

        //Try-Catch to make sure it was given a proper file path.
        try {
            file = new File(inputFileName);
            filescnr = new Scanner(file);
            //while loop to get the total number of ints inside the file.
            while (filescnr.hasNext()) {
                count += 1 + (filescnr.nextInt() * 0);
            }
            filescnr.close();
            //Checks if their is an even number of ints in the file, if not it throws an exception.
            if (count % 2 != 0) {
                throw new InputMismatchException("File contains an odd number of integers.");
            } else {
                points = new Point[count / 2];
                int i = 0;
                Scanner filescnr2 = new Scanner(file);
                //Fills the points array with the numbers in the file.
                while (filescnr2.hasNext()) {
                    int x = filescnr2.nextInt();
                    int y = filescnr2.nextInt();
                    points[i] = new Point(x, y);
                    i++;
                }
                filescnr.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("FILE NOT FOUND, path name was entered incorrectly.");
        }
    }

    /**
     * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:
     *
     *     a) Sort points[] by the x-coordinate to get the median x-coordinate.
     *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
     *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.
     *
     * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
     * or QuickSorter to carry out sorting.
     * @param algo
     * @return
     */
    public void scan() {
        AbstractSorter aSorter = null;
        long startTime = 0;
        long endTime = 0;
        long timeX = 0;
        long timeY = 0;

        //If statements that set aSorter to the given algorithm.
        if (sortingAlgorithm == Algorithm.QuickSort) {
            aSorter = new QuickSorter(points);
        }
        if (sortingAlgorithm == Algorithm.MergeSort) {
            aSorter = new MergeSorter(points);
        }
        if (sortingAlgorithm == Algorithm.InsertionSort) {
            aSorter = new InsertionSorter(points);
        }
        if (sortingAlgorithm == Algorithm.SelectionSort) {
            aSorter = new SelectionSorter(points);
        }

        //Sorts the x values of the array and calculates the time it takes to sort.
        aSorter.setComparator(0);
        startTime = System.nanoTime();
        aSorter.sort();
        endTime = System.nanoTime();
        timeX = endTime - startTime;
        int x = aSorter.getMedian().getX();

        //Sorts the y values of the array and calculates the time it takes to sort.
        aSorter.setComparator(1);
        startTime = System.nanoTime();
        aSorter.sort();
        endTime = System.nanoTime();
        timeY = endTime - startTime;
        int y = aSorter.getMedian().getY();

        medianCoordinatePoint = new Point(x, y);
        scanTime = timeX + timeY;

        // create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two
        // rounds of sorting, have aSorter do the following:
        //
        //     a) call setComparator() with an argument 0 or 1.
        //
        //     b) call sort().
        //
        //     c) use a new Point object to store the coordinates of the medianCoordinatePoint
        //
        //     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
        //
        //     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime.

    }

    /**
     * Outputs performance statistics in the format:
     *
     * <sorting algorithm> <size>  <time>
     *
     * For instance,
     *
     * selection sort   1000	  9200867
     *
     * Use the spacing in the sample run in Section 2 of the project description.
     */
    public String stats() {
        String stats = "";
        //If statements that check the sorting algorithm and format a string with the type of algorithm, length, and time to sort.
        if (sortingAlgorithm == Algorithm.QuickSort) {
            stats = String.format("QuickSort         %d  %d", points.length, scanTime);
        }
        if (sortingAlgorithm == Algorithm.InsertionSort) {
            stats = String.format("InsertionSort     %d  %d", points.length, scanTime);
        }
        if (sortingAlgorithm == Algorithm.MergeSort) {
            stats = String.format("MergeSort         %d  %d", points.length, scanTime);
        }
        if (sortingAlgorithm == Algorithm.SelectionSort) {
            stats = String.format("SelectionSort     %d  %d", points.length, scanTime);
        }
        return stats;
    }

    /**
     * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space
     * in between.
     */
    @Override
    public String toString() {
        return medianCoordinatePoint.toString();
    }

    /**
     *
     * This method, called after scanning, writes point data into a file by outputFileName. The format
     * of data in the file is the same as printed out from toString().  The file can help you verify
     * the full correctness of a sorting result and debug the underlying algorithm.
     *
     * @throws FileNotFoundException
     */
    public void writeMCPToFile() throws FileNotFoundException {
        try {
            FileWriter outputFile = new FileWriter("/Users/nedimhodzic/IdeaProjects/HW2/src/edu/outputFile.txt");
            outputFile.write(toString());
            outputFile.close();
        } catch (Exception e) {
            throw new FileNotFoundException("FILE NOT FOUND, path name was entered incorrectly.");
        }
    }
}
